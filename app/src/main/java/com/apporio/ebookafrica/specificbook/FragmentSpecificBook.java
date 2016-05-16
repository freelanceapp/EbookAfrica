package com.apporio.ebookafrica.specificbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.order.ConfirmOrder;
import com.apporio.ebookafrica.pojo.RelatedPRoducts;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.apporio.ebookafrica.pojo.SpecificBookSuccess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.util.ArrayList;

import views.HorizontalListView;

/**
 * Created by spinnosolutions on 4/25/16.
 */
@SuppressLint("ValidFragment")
public class FragmentSpecificBook extends Fragment {

    HorizontalListView horizintal_list ;
  //  CustomRatingBarGreen  rating_bar_top;
    LinearLayout buy_now  , loadingbar , mainlayout  ,  related_loader  ,already_purchased  ;
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
    String BOOKIMAGE , FILE_URL , BOOKNAME = "" , BOOKID , ISBN  ,PAGES , HOURS ,PRICE , AUTHOR , MANUFACTURE ;








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
        bookname_txt = (TextView) rootView.findViewById(R.id.book_name);
        authorname_txt = (TextView) rootView.findViewById(R.id.author_name);
        pages_txt = (TextView) rootView.findViewById(R.id.pages);
        hours_txt = (TextView) rootView.findViewById(R.id.hours);
        summary_txt = (TextView) rootView.findViewById(R.id.summary);
        relatedproductlayout  = rootView.findViewById(R.id.relatedproductlayout);
        related_loader  = (LinearLayout) rootView.findViewById(R.id.related_loader);
        already_purchased = (LinearLayout) rootView.findViewById(R.id.already_purchased);
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
        in.putExtra("name_key", BOOKNAME);
        in.putExtra("product_id", BOOKID);
        in.putExtra("isbn", ISBN);
        in.putExtra("image_key", BOOKIMAGE);
        in.putExtra("pages_txt", PAGES);
        in.putExtra("hours_txt", HOURS);
        in.putExtra("price", PRICE);
        in.putExtra("author", AUTHOR);
        in.putExtra("manufacturer", MANUFACTURE);
        startActivity(in);
    }





    public void SpecificProductExecution(){
        String url = UrlsEbookAfrics.GetSpecificProduct+product_id+"&customer_id="+sm.getUserDetails().get(SessionManager.CUSTOMER_ID);
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
                    bookname_txt.setText("" + sbs.getSpecificBookSuccessProduct().getName());
                    authorname_txt.setText("" + sbs.getSpecificBookSuccessProduct().getAuthor());
                    hours_txt.setText("   hours "+sbs.getSpecificBookSuccessProduct().getHours());
                    pages_txt.setText("Pages "+sbs.getSpecificBookSuccessProduct().getPages());
                    summary_txt.setText("" + sbs.getSpecificBookSuccessProduct().getDescription());
                    price.setText(""+sbs.getSpecificBookSuccessProduct().getPrice());


                    if(sbs.getSpecificBookSuccessProduct().getPurchaseStatus().equals("1")){
                        buy_now.setVisibility(View.GONE);
                        already_purchased.setVisibility(View.VISIBLE);
                    }else if (sbs.getSpecificBookSuccessProduct().getPurchaseStatus().equals("0")){
                        buy_now.setVisibility(View.VISIBLE);
                        already_purchased.setVisibility(View.GONE);
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











}
