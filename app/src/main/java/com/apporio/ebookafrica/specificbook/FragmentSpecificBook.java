package com.apporio.ebookafrica.specificbook;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.AppOrioLoginScreen;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CustomVolleyRequestQueue;
import com.apporio.ebookafrica.constants.SessionManager;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.epubsamir.FileaName;
import com.apporio.ebookafrica.epubsamir.MainActivityEPUBSamir;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.order.ConfirmOrder;
import com.apporio.ebookafrica.pojo.PlaceOrder;
import com.apporio.ebookafrica.pojo.RelatedPRoducts;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.apporio.ebookafrica.pojo.SpecificBookSuccess;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import views.HorizontalListView;

/**
 * Created by spinnosolutions on 4/25/16.
 */
@SuppressLint("ValidFragment")
public class FragmentSpecificBook extends Fragment {


    HorizontalListView horizintal_list ;
  //  CustomRatingBarGreen  rating_bar_top;
    LinearLayout buy_now  , loadingbar , mainlayout  ,  related_loader   ;
    SessionManager   sm  ;

    NetworkImageView imagebook ,button_iimage ;
    TextView bookname , authorname, pages, hours  , summary ;
    ImageLoader mImageLoader;


    private static RequestQueue queue ;
    private static StringRequest sr;
     String product_id ;

    View relatedproductlayout ;
    ArrayList<String> book_name = new ArrayList<>();
    ArrayList<String> book_id = new ArrayList<>();
    ArrayList<String> book_image = new ArrayList<>();

    TextView price ;
    String bookimage  ;
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    String file_url , BookNAME = "";

    @SuppressLint("ValidFragment")
    public FragmentSpecificBook(String productid){
        this.product_id = productid ;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sm = new SessionManager(getActivity());
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        mImageLoader = CustomVolleyRequestQueue.getInstance(getActivity()).getImageLoader();


        View rootView = inflater.inflate(R.layout.fragment_specific_book, container, false);
        horizintal_list = (HorizontalListView) rootView.findViewById(R.id.horizintal_list);
      //  rating_bar_top = (CustomRatingBarGreen) rootView.findViewById(R.id.rating_bar_top);
        button_iimage = (NetworkImageView) rootView.findViewById(R.id.button_iimage);
        imagebook = (NetworkImageView) rootView.findViewById(R.id.image_book);
        buy_now = (LinearLayout) rootView.findViewById(R.id.buy_now);
        loadingbar = (LinearLayout) rootView.findViewById(R.id.loadingbar);
        mainlayout = (LinearLayout) rootView.findViewById(R.id.mainlayout);
        bookname = (TextView) rootView.findViewById(R.id.book_name);
        authorname = (TextView) rootView.findViewById(R.id.author_name);
        pages = (TextView) rootView.findViewById(R.id.pages);
        hours = (TextView) rootView.findViewById(R.id.hours);
        summary = (TextView) rootView.findViewById(R.id.summary);
        relatedproductlayout  = rootView.findViewById(R.id.relatedproductlayout);
        related_loader  = (LinearLayout) rootView.findViewById(R.id.related_loader);
        price  = (TextView) rootView.findViewById(R.id.price);


     //   rating_bar_top.setScore(3);


        rootView.findViewById(R.id.preview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), LalitActivity.class));
            }
        });



        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sm.isLoggedIn()) {
                    try {
                        DownloadBook();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Intent in = new Intent(getActivity(), AppOrioLoginScreen.class);
                    in.putExtra("apporio_login_url", UrlsEbookAfrics.Login);
                    in.putExtra("apporio_sign_url", UrlsEbookAfrics.SighUp);
                    startActivity(in);
                }
            }
        });


        horizintal_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().finish();
                Intent in = new Intent(getActivity(), SpecificBookActivity.class);
                in.putExtra("product_id", "" + book_id.get(position));
                getActivity().startActivity(in);
            }
        });





        SpecificProductExecution();


        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }







    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }




    private void DownloadBook() throws JSONException {
        Intent in = new Intent(getActivity(), ConfirmOrder.class);
        in.putExtra("product_id", product_id);
        in.putExtra("image_key", bookimage);
        startActivity(in);
//        confirmOrderApiExecution(product_id);
    }





    public void SpecificProductExecution(){
        String url = UrlsEbookAfrics.GetSpecificProduct+product_id;
        url=url.replace(" ","%20");
        Logger.d("Executing Specific Product API   " + url);


        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResponseChecker rcheck = new ResponseChecker();
                rcheck = gson.fromJson(response, ResponseChecker.class);

                if(rcheck.getStatus().equals("success")){
                    SpecificBookSuccess sbs = new SpecificBookSuccess();
                    sbs = gson.fromJson(response, SpecificBookSuccess.class);



                    mImageLoader.get(sbs.getSpecificBookSuccessProduct().getImage(), ImageLoader.getImageListener(imagebook, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
                    imagebook.setImageUrl(sbs.getSpecificBookSuccessProduct().getImage(), mImageLoader);
                    mImageLoader.get(sbs.getSpecificBookSuccessProduct().getImage(), ImageLoader.getImageListener(button_iimage, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
                    button_iimage.setImageUrl(sbs.getSpecificBookSuccessProduct().getImage(), mImageLoader);
                    bookname.setText("" + sbs.getSpecificBookSuccessProduct().getName());
                    authorname.setText(""+sbs.getSpecificBookSuccessProduct().getAuthor());
                    summary.setText(""+sbs.getSpecificBookSuccessProduct().getDescription());
                    price.setText(""+sbs.getSpecificBookSuccessProduct().getPrice());


                    bookimage = sbs.getSpecificBookSuccessProduct().getImage() ;

                    relatedproducts();

                } else {
                    Toast.makeText(getActivity(), "Failurre", Toast.LENGTH_SHORT).show();
                }

                loader(1);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.d(""+error);
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
        loader(0);
    }



    public void relatedproducts(){
        String url = UrlsEbookAfrics.relatedproducts +product_id;
        url=url.replace(" ","%20");
        Logger.d("Executing Related Product API   " + url);


        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResponseChecker rcheck = new ResponseChecker();
                rcheck = gson.fromJson(response, ResponseChecker.class);

                if(rcheck.getStatus().equals("success")){

                    RelatedPRoducts relatedproducts = new RelatedPRoducts();
                    relatedproducts = gson.fromJson(response, RelatedPRoducts.class);

                    book_name.clear();
                    book_id.clear();
                    book_image.clear();

                    for(int i = 0 ; i< relatedproducts.getProductcore().getRelatedProduct().size() ; i++){
                        book_name.add("" + relatedproducts.getProductcore().getRelatedProduct().get(i).getName());
                        book_id.add(""+relatedproducts.getProductcore().getRelatedProduct().get(i).getProductId());
                        book_image.add(""+relatedproducts.getProductcore().getRelatedProduct().get(i).getImage());


                    }
                    loaderrelated(1);
                    horizintal_list.setAdapter(new AdapterRelatedList(getActivity(), book_name, book_id, book_image));

                } else {
                    Toast.makeText(getActivity(), "Failurre", Toast.LENGTH_SHORT).show();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.d(""+error);
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
        loaderrelated(0);
    }





    private void loader(int i) {
        if(i == 0 ){
            loadingbar.setVisibility(View.VISIBLE);
            mainlayout.setVisibility(View.GONE);
        }else if (i == 1){
            loadingbar.setVisibility(View.GONE);
            mainlayout.setVisibility(View.VISIBLE);
        }
    }



    private void loaderrelated (int i ){
        if(i == 0 ){
            related_loader.setVisibility(View.VISIBLE);
            relatedproductlayout.setVisibility(View.GONE);
        }else if (i == 1){
            related_loader.setVisibility(View.GONE);
            relatedproductlayout.setVisibility(View.VISIBLE);
        }
    }

















///////////////////////////////////////////////////////////////




    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("By Pass Payment Gateway , Downloading file. Please wait...");
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

                            Toast.makeText(getActivity(), "Invalid json format " , Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity() , "" +error, Toast.LENGTH_SHORT).show();
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
                            file_url  =  po.getPlaceOrderOrderDetail().getPlaceOrderProducts().get(0).getDownloadLink() ;
                            new DownloadFileFromURL().execute(file_url);
                        }else {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity() , "" +error, Toast.LENGTH_SHORT).show();
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

        File cacheFile ;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // showDialog(progress_bar_type);
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




                File cacheDir = getDataFolder(getActivity());
                cacheFile = new File(cacheDir, BookNAME+".epub");
                Logger.d("file path -----" + cacheFile);
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
           // dismissDialog(progress_bar_type);
            Toast.makeText(getActivity() ,"File Downloaded Successfully" ,Toast.LENGTH_SHORT).show();
            FileaName.FileNAME = BookNAME ;
            FileaName.FilePath = ""+cacheFile ;
            Logger.d("Setting book name and path after download " +BookNAME);
            Intent in  = new Intent(getActivity()  , MainActivityEPUBSamir.class);
            startActivity(in);
        }

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
