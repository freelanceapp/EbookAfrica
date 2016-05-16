package com.apporio.ebookafrica.order;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CustomVolleyRequestQueue;
import com.apporio.ebookafrica.constants.SessionManager;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.database.PurchasedProductManager;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.pojo.PlaceOrder;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class ConfirmOrder extends Activity {


    private static RequestQueue queue ;
    SessionManager sm  ;
    Button download   , openbook;


    TextView capturedownloadlink ;
    NetworkImageView imagebook ;
    ImageLoader mImageLoader;
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    String file_url , BookNAME;

    String BOOKIMAGE  , BOOKNAME = "" , BOOKID , ISBN  ,PAGES , HOURS ,PRICE , AUTHOR , MANUFACTURE ;
    PurchasedProductManager psm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        sm = new SessionManager(ConfirmOrder.this);
        psm = new PurchasedProductManager(ConfirmOrder.this);



        mImageLoader = CustomVolleyRequestQueue.getInstance(ConfirmOrder.this).getImageLoader();

        capturedownloadlink = (TextView) findViewById(R.id.capturedownloadlink);
        openbook = (Button) findViewById(R.id.openbook);

        download = (Button) findViewById(R.id.download);
        imagebook = (NetworkImageView)findViewById(R.id.image_book);
        queue = VolleySingleton.getInstance(ConfirmOrder.this).getRequestQueue();


        mImageLoader.get(getIntent().getExtras().getString("image_key"), ImageLoader.getImageListener(imagebook, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
        imagebook.setImageUrl(getIntent().getExtras().getString("image_key"), mImageLoader);





        BOOKNAME = getIntent().getExtras().getString("name_key");
        BOOKID = getIntent().getExtras().getString("product_id");
        ISBN = getIntent().getExtras().getString("isbn");
        BOOKIMAGE = getIntent().getExtras().getString("image_key");
        PAGES = getIntent().getExtras().getString("pages_txt");
        HOURS = getIntent().getExtras().getString("hours_txt");
        PRICE = getIntent().getExtras().getString("price");
        AUTHOR = getIntent().getExtras().getString("author");
        MANUFACTURE = getIntent().getExtras().getString("manufacturer");






        try {
            confirmOrderApiExecution(getIntent().getExtras().getString("product_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFileFromURL().execute(file_url);
            }
        });

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }






    private void confirmOrderApiExecution( String product_idd) throws JSONException {

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, UrlsEbookAfrics.ConfirmOrder,

                new JSONObject("{\"language_id\":1,\"coupon\":\"\",\"voucher\":\"\",\"customer_id\":\"1\",\"products\":[{\"product_id\":"+product_idd+","+ "\"quantity\":2}],\"language_id\":1,\"payment_address\":{\"address_id\":14,\"payment_firstname\":\"pooja\",\"payment_lastname\":\"kailiwal\",\"payment_company\":\"onjection\",\"payment_address_1\":\"Gurgaon\",\"payment_address_2\":\"\",\"payment_city\":\"Gurgaon\",\"payment_postcode\":\"122001\",\"payment_country\":\"India\",\"payment_country_id\":\"99\",\"payment_zone\":\"Haryana\",\"payment_zone_id\":\"1486\",\"payment_telephone\":\"9999722105\",\"payment_email\":\"18793pooja@gmail.com\"},\"payment_method\":{\"title\":\"Cash on Delivery\",\"code\": \"cod\",\"terms\": \"\",\"sort_order\": \"5\"},\"shipping_method\":{\"title\":\"Flat Rate Shipping\",\"code\": \"flat\",\"cost\": \"5.00\",\"tax_class_id\": \"9\",\"sort_order\": \"5\"}}"),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        ResponseChecker rcheck = new ResponseChecker();
                        rcheck = gson.fromJson("" + response, ResponseChecker.class);

                        if(rcheck.getStatus().equals("success")){
                            com.apporio.ebookafrica.pojo.ConfirmOrder co = new com.apporio.ebookafrica.pojo.ConfirmOrder();
                            co = gson.fromJson("" + response, com.apporio.ebookafrica.pojo.ConfirmOrder.class);
                            BookNAME = co.getOrderInfo().getConfirmOrderProducts().get(0).getName();
                            placeorderApiExecution("" + co.getOrderInfo().getOrderId());


                        }else {

                            Toast.makeText(ConfirmOrder.this , "Invalid json format " , Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConfirmOrder.this , "" +error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };
        queue.add(postRequest);
        download.setEnabled(false);
        download.setText("Loading");
        openbook.setVisibility(View.GONE);
    }





    private void placeorderApiExecution( String order_id) {
        Map<String, String> jsonParams = new HashMap<String, String>();

        jsonParams.put("order_id", ""+order_id);
        jsonParams.put("order_status_id", "1");

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, UrlsEbookAfrics.PlaceOrder,

                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        ResponseChecker rcheck = new ResponseChecker();
                        rcheck = gson.fromJson("" + response, ResponseChecker.class);

                        if(rcheck.getStatus().equals("success")){
                            PlaceOrder po = new PlaceOrder();
                            po = gson.fromJson("" + response, PlaceOrder.class);
                            capturedownloadlink.setText("" + po.getPlaceOrderOrderDetail().getPlaceOrderProducts().get(0).getDownloadLink());
                            file_url  =  po.getPlaceOrderOrderDetail().getPlaceOrderProducts().get(0).getDownloadLink() ;
                            download.setEnabled(true);
                            download.setText("Start Downloading");
                            openbook.setVisibility(View.VISIBLE);
                            new DownloadFileFromURL().execute(file_url);
                        }else {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConfirmOrder.this , "" +error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };
        queue.add(postRequest);
    }






    class DownloadFileFromURL extends AsyncTask<String, String, String> {




        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);




                File cacheDir = getDataFolder(ConfirmOrder.this);
                String newbookname = BookNAME.replace(" " , "_");
                File cacheFile = new File(cacheDir, newbookname+".epub");
                Logger.d("file path " + cacheFile);
                FileOutputStream output = new FileOutputStream(cacheFile);



                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }





        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String file_url) {
            dismissDialog(progress_bar_type);

//            FileaName.FilePath = ""+getDataFolder(ConfirmOrder.this)+"/"+BookNAME.replace(" " , "_")+".epub";
//            FileaName.FileNAME = ""+BookNAME.replace(" " , "_"+".epub");
//            startActivity(new Intent(ConfirmOrder.this, MainActivityEPUBSamir.class));
            Toast.makeText(ConfirmOrder.this ,"File Downloaded Successfully  , now available in offline section " ,Toast.LENGTH_LONG).show();
            savaBookLocaly();
            finish();
        }

    }

    private void savaBookLocaly() {

        psm.addtoPurchasedProductTable(BOOKNAME , BOOKID , ISBN , BOOKIMAGE , PAGES , HOURS , PRICE , AUTHOR , MANUFACTURE);

    }


    public File getDataFolder(Context context) {
        File dataDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dataDir = new File(Environment.getExternalStorageDirectory(), "ebbok_data");
            if(!dataDir.isDirectory()) {
                dataDir.mkdirs();
            }
        }

        if(!dataDir.isDirectory()) {
            dataDir = context.getFilesDir();
        }

        return dataDir;
    }



}
