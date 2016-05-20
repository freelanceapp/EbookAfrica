package com.apporio.ebookafrica.specificbook;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.AppOrioLoginScreen;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CustomVolleyRequestQueue;
import com.apporio.ebookafrica.constants.SessionManager;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.database.PurchasedProductManager;
import com.apporio.ebookafrica.epubsamir.FileaName;
import com.apporio.ebookafrica.epubsamir.MainActivityEPUBSamir;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.order.ConfirmOrder;
import com.apporio.ebookafrica.pojo.RelatedPRoducts;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.apporio.ebookafrica.pojo.SpecificBookSuccess;
import com.apporio.ebookafrica.pojo.paypalpojo.PayPalPojo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import views.HorizontalListView;

/**
 * Created by spinnosolutions on 4/25/16.
 */
@SuppressLint("ValidFragment")
public class FragmentSpecificBook extends Fragment {
    HorizontalListView horizintal_list ;
  //  CustomRatingBarGreen  rating_bar_top;
    LinearLayout buy_now  , loadingbar , mainlayout  ,  related_loader  ,already_purchased  ,download_if_purchased , preview  , no_previe_available;
    SessionManager   sm  ;

    NetworkImageView imagebook ,button_iimage ;
    TextView bookname_txt, authorname_txt, pages_txt, hours_txt, summary_txt;
    ImageLoader mImageLoader;


    private static RequestQueue queue ;
    private static StringRequest sr;
     String product_id ;

    View relatedproductlayout ;
    ArrayList<String> book_name = new ArrayList<>();
    ArrayList<String> book_id = new ArrayList<>();
    ArrayList<String> book_image = new ArrayList<>();

    TextView price ;
    String BOOKIMAGE , SAMPLE_FILE_URL , BOOKNAME = "" , BOOKID , ISBN  ,PAGES , HOURS ,PRICE , AUTHOR , MANUFACTURE ;
    PurchasedProductManager psm ;



    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;
    private static final String CONFIG_CLIENT_ID = "AFcWxV21C7fd0v3bYYYRCpSSRl31AW.nrY8UUmkTDBx-TSEQlHYBvptc";
    Double totaldoubleprice = 150.0;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID);





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
        Toast.makeText(getActivity() , "" , Toast.LENGTH_SHORT).show();

        View rootView = inflater.inflate(R.layout.fragment_specific_book, container, false);
        horizintal_list = (HorizontalListView) rootView.findViewById(R.id.horizintal_list);
      //  rating_bar_top = (CustomRatingBarGreen) rootView.findViewById(R.id.rating_bar_top);
        button_iimage = (NetworkImageView) rootView.findViewById(R.id.button_iimage);
        imagebook = (NetworkImageView) rootView.findViewById(R.id.image_book);
        buy_now = (LinearLayout) rootView.findViewById(R.id.buy_now);
        loadingbar = (LinearLayout) rootView.findViewById(R.id.loadingbar);
        mainlayout = (LinearLayout) rootView.findViewById(R.id.mainlayout);
        bookname_txt = (TextView) rootView.findViewById(R.id.book_name);
        authorname_txt = (TextView) rootView.findViewById(R.id.author_name);
        pages_txt = (TextView) rootView.findViewById(R.id.pages);
        hours_txt = (TextView) rootView.findViewById(R.id.hours);
        summary_txt = (TextView) rootView.findViewById(R.id.summary);
        relatedproductlayout  = rootView.findViewById(R.id.relatedproductlayout);
        related_loader  = (LinearLayout) rootView.findViewById(R.id.related_loader);
        already_purchased = (LinearLayout) rootView.findViewById(R.id.already_purchased);
        price  = (TextView) rootView.findViewById(R.id.price);
        download_if_purchased = (LinearLayout) rootView.findViewById(R.id.download_if_purchased);
        preview = (LinearLayout) rootView.findViewById(R.id.preview);
        no_previe_available  = (LinearLayout) rootView.findViewById(R.id.no_previe_available);

        psm = new PurchasedProductManager(getActivity());

     //   rating_bar_top.setScore(3);


        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFileFromURL().execute("" + SAMPLE_FILE_URL);
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




        rootView.findViewById(R.id.download_if_purchased).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), ConfirmOrder.class);
                in.putExtra("name_key", BOOKNAME);
                in.putExtra("product_id", BOOKID);
                in.putExtra("isbn", ISBN);
                in.putExtra("image_key", BOOKIMAGE);
                in.putExtra("pages_txt", PAGES);
                in.putExtra("hours_txt", HOURS);
                in.putExtra("price", PRICE);
                in.putExtra("author", AUTHOR);
                in.putExtra("manufacturer", MANUFACTURE);
                in.putExtra("payment_id", "already purchased");
                startActivity(in);
            }
        });





        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onResume() {
        super.onResume();
        SpecificProductExecution();
    }

    private void DownloadBook() throws JSONException {

        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(totaldoubleprice)), "SGD", "Pay",
                PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(intent, 0);

    }





    public void SpecificProductExecution(){
        String url = UrlsEbookAfrics.GetSpecificProduct+product_id+"&customer_id="+sm.getUserDetails().get(SessionManager.CUSTOMER_ID );
        url=url.replace(" ","%20").replace("null" , "");
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
                    bookname_txt.setText("" + sbs.getSpecificBookSuccessProduct().getName());
                    authorname_txt.setText("" + sbs.getSpecificBookSuccessProduct().getAuthor());
                    hours_txt.setText("   hours "+sbs.getSpecificBookSuccessProduct().getHours());
                    pages_txt.setText("Pages "+sbs.getSpecificBookSuccessProduct().getPages());
                    summary_txt.setText("" + sbs.getSpecificBookSuccessProduct().getDescription());
                    price.setText(""+sbs.getSpecificBookSuccessProduct().getPrice());
                    totaldoubleprice = Double.parseDouble(sbs.getSpecificBookSuccessProduct().getPrice().replace("$",""));
                    SAMPLE_FILE_URL = sbs.getSpecificBookSuccessProduct().getSampleBooks() ;


                   if (sbs.getSpecificBookSuccessProduct().getPurchaseStatus().equals("0")){
                        buy_now.setVisibility(View.VISIBLE);
                        already_purchased.setVisibility(View.GONE);
                        download_if_purchased.setVisibility(View.GONE);
                       if(SAMPLE_FILE_URL.equals("http://modha.me.uk/admin/")){

                           no_previe_available.setVisibility(View.VISIBLE);
                           preview.setVisibility(View.GONE);
                       }else {
                           no_previe_available.setVisibility(View.GONE);
                           preview.setVisibility(View.VISIBLE);
                       }

                    }else{
                        buy_now.setVisibility(View.GONE);
                        already_purchased.setVisibility(View.VISIBLE);
                        download_if_purchased.setVisibility(View.VISIBLE);
                       no_previe_available.setVisibility(View.GONE);
                       preview.setVisibility(View.GONE);
                    }


                    BOOKIMAGE = ""+sbs.getSpecificBookSuccessProduct().getImage() ;
                    BOOKNAME = sbs.getSpecificBookSuccessProduct().getName();
                    BOOKID = sbs.getSpecificBookSuccessProduct().getProductId() ;
                    ISBN = sbs.getSpecificBookSuccessProduct().getIsbn() ;
                    PAGES = sbs.getSpecificBookSuccessProduct().getPages();
                    HOURS = sbs.getSpecificBookSuccessProduct().getHours();
                    PRICE = sbs.getSpecificBookSuccessProduct().getPrice() ;
                    AUTHOR = sbs.getSpecificBookSuccessProduct().getAuthor() ;
                    MANUFACTURE = sbs.getSpecificBookSuccessProduct().getManufacturer() ;

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






    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {

                String response = null;
                try {
                    response = confirm.toJSONObject().toString(4);
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    PayPalPojo payPalPojo = new PayPalPojo();
                    payPalPojo = gson.fromJson(response, PayPalPojo.class);

                    Logger.d("ID-" ,""+payPalPojo.getPayPalResponse().getId());


                    Intent in = new Intent(getActivity(), ConfirmOrder.class);
                    in.putExtra("name_key", BOOKNAME);
                    in.putExtra("product_id", BOOKID);
                    in.putExtra("isbn", ISBN);
                    in.putExtra("image_key", BOOKIMAGE);
                    in.putExtra("pages_txt", PAGES);
                    in.putExtra("hours_txt", HOURS);
                    in.putExtra("price", PRICE);
                    in.putExtra("author", AUTHOR);
                    in.putExtra("manufacturer", MANUFACTURE);
                    in.putExtra("payment_id", payPalPojo.getPayPalResponse().getId());
                    startActivity(in);



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Logger.d("paymentExample"+ "The user canceled.");
            Toast.makeText(getActivity(), "CANCELED ", Toast.LENGTH_SHORT).show();
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {

            Toast.makeText(getActivity(), "Invalid Payment ", Toast.LENGTH_SHORT).show();
            Logger.d("paymentExample" + "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
    }
















//////////////////////// code for downloading sample epub




    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                String newbookname = "sample_book.epub";
                File cacheFile = new File(cacheDir, newbookname+".epub");
                Logger.d("file path " + cacheFile);
                FileOutputStream output = new FileOutputStream(cacheFile);

                FileaName.FileNAME = newbookname ;
                FileaName.FilePath = ""+cacheFile ;


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
        }


        @Override
        protected void onPostExecute(String file_url) {


            startActivity(new Intent(getActivity(), MainActivityEPUBSamir.class));

            Toast.makeText(getActivity(),"File Downloaded Successfully  , now available in offline section " ,Toast.LENGTH_LONG).show();
        }

    }











//////////// clas  for downloading full file
    class DownloadFullFileFromURL extends AsyncTask<String, String, String> {




        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                String newbookname = BOOKNAME.replace(" " , "_");
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
        }


        @Override
        protected void onPostExecute(String file_url) {

            Toast.makeText(getActivity() ,"File Downloaded Successfully  , now available in offline section " ,Toast.LENGTH_LONG).show();
            savaBookLocaly();
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
