package com.apporio.ebookafrica.specificbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
import com.apporio.ebookafrica.lalit.LalitActivity;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.apporio.ebookafrica.pojo.SpecificBookSuccess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import views.CustomRatingBarGreen;

/**
 * Created by spinnosolutions on 4/25/16.
 */
@SuppressLint("ValidFragment")
public class FragmentSpecificBook extends Fragment {


    ListView bottomrelated_listview ;
    CustomRatingBarGreen  rating_bar_top;
    LinearLayout buy_now  , loadingbar , mainlayout  ;
    SessionManager   sm  ;

    NetworkImageView imagebook ,button_iimage ;
    TextView bookname , authorname, pages, hours  , summary ;
    ImageLoader mImageLoader;


    private static RequestQueue queue ;
    private static StringRequest sr;
     String product_id ;

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
        bottomrelated_listview = (ListView) rootView.findViewById(R.id.related_list);
        rating_bar_top = (CustomRatingBarGreen) rootView.findViewById(R.id.rating_bar_top);
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


        button_iimage.setImageResource(R.drawable.cover_2);

        bottomrelated_listview.setAdapter(new AdapterRelatedList(getActivity()));
        setListViewHeightBasedOnChildren(bottomrelated_listview);
        rating_bar_top.setScore(3);


        rootView.findViewById(R.id.preview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LalitActivity.class));
            }
        });



        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sm.isLoggedIn()) {
                    DownloadBook();
                } else {
                    Intent in = new Intent(getActivity(), AppOrioLoginScreen.class);
                    in.putExtra("apporio_login_url", UrlsEbookAfrics.Login);
                    in.putExtra("apporio_sign_url", UrlsEbookAfrics.SighUp);
                    startActivity(in);
                }
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




    private void DownloadBook() {
        Toast.makeText(getActivity() , "Starts Downloading" , Toast.LENGTH_SHORT).show();
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
                    bookname.setText(""+sbs.getSpecificBookSuccessProduct().getName());
                    authorname.setText("author id "+sbs.getSpecificBookSuccessProduct().getAuthorId());
                    summary.setText(""+sbs.getSpecificBookSuccessProduct().getDescription());



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

    private void loader(int i) {
        if(i == 0 ){
            loadingbar.setVisibility(View.VISIBLE);
            mainlayout.setVisibility(View.GONE);
        }else if (i == 1){
            loadingbar.setVisibility(View.GONE);
            mainlayout.setVisibility(View.VISIBLE);
        }
    }


}
