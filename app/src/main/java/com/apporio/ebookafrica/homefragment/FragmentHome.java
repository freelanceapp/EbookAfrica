package com.apporio.ebookafrica.homefragment;


import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.FragmentStatus;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.pojo.AllCategories;
import com.apporio.ebookafrica.pojo.BannerSliderPojo;
import com.apporio.ebookafrica.pojo.Product;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2/4/2016.
 */
public class FragmentHome extends Fragment {


    public  SliderLayout image_slider;
    public ListView list ;
    LinearLayout loader ;


    public FragmentHome(){}




    private static RequestQueue queue ;
    private static StringRequest sr;

    public static  Context context ;
    ArrayList<List<String>> categories_id_Array  = new ArrayList<>();
    ArrayList<String> banner_names = new ArrayList<>();




    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentStatus.GetOpenfragment = "FragmentHome";
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        context  = getActivity() ;
        image_slider = (SliderLayout)rootView.findViewById(R.id.slider);
        list  = (ListView) rootView.findViewById(R.id.list);
        loader = (LinearLayout) rootView.findViewById(R.id.loader);



        BannerApiExecution();
        AllCategoriesIncludingProductsExecution();


        image_slider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ActivityBannerAndSpecialCategory.class));
            }
        });



        return rootView;
    }

    private void setImageSlider(final ArrayList<String> images) {


        for(int i = 0; i<images.size();i ++) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
            final int finalI = i;
            final int finalI1 = i;
            defaultSliderView.image(images.get(i))
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {

                            ArrayList<String> categories =new ArrayList<String>();
                            for(int j = 0 ; j< categories_id_Array.get(finalI).size() ; j++){
                                categories.add(categories_id_Array.get(finalI).get(j));
                            }
                            Intent in  = new Intent(getActivity() ,ActivityBannerAndSpecialCategory.class);
                            in.putStringArrayListExtra("array" ,categories);

                            in.putExtra("fragment_name" , ""+banner_names.get(finalI1));
                            startActivity(in);
                        }
                    });

            image_slider.addSlider(defaultSliderView);


        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }














    public void BannerApiExecution(){

        String url = UrlsEbookAfrics.BannerHome;
        url=url.replace(" ","%20");
        Logger.d("Executing home banner API   " + url);


        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResponseChecker rcheck = new ResponseChecker();
                rcheck = gson.fromJson(response, ResponseChecker.class);
                if(rcheck.getStatus().equals("success")){
                    BannerSliderPojo bsp = new BannerSliderPojo();
                    bsp = gson.fromJson(response, BannerSliderPojo.class);
                    ArrayList<String> bannerimages  = new ArrayList<>();
                    bannerimages.clear();
                    banner_names.clear();
                    for(int i = 0 ; i< bsp.getSlider().size() ; i++){
                        bannerimages.add(bsp.getSlider().get(i).getImage());
                        categories_id_Array.add(bsp.getSlider().get(i).getCategoryId());
                        banner_names.add(""+bsp.getSlider().get(i).getTitle());


                    }
                    setImageSlider(bannerimages);
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

    }







    public void AllCategoriesIncludingProductsExecution(){
        String url = UrlsEbookAfrics.AllCategories;
        url=url.replace(" ","%20");
        Logger.d("Executing All Categories API   " + url);


        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResponseChecker rcheck = new ResponseChecker();
                rcheck = gson.fromJson(response, ResponseChecker.class);

                if(rcheck.getStatus().equals("success")){

                    AllCategories allcategories = new AllCategories();
                    allcategories = gson.fromJson(response, AllCategories.class);

                    ArrayList<String> Category_name = new ArrayList<>();
                    ArrayList<String> Category_id = new ArrayList<>();
                    ArrayList<List<Product>> Category_products  = new ArrayList<>();
                    for(int i = 0 ; i< allcategories.getCategories().size() ; i++){
                        Category_name.add(""+allcategories.getCategories().get(i).getName());
                        Category_id.add(""+allcategories.getCategories().get(i).getCategoryId());
                        Category_products.add(allcategories.getCategories().get(i).getProduct());


                    }

                    if(FragmentStatus.GetOpenfragment.equals("FragmentHome")){
                        horizontalListLoader(1);
                        list.setAdapter(new AdapterHomePageList(getActivity(), Category_name, Category_id, Category_products));
                    }



                    setListViewHeightBasedOnChildren(list);
                }else {
                    Toast.makeText(getActivity(), "No categories available", Toast.LENGTH_SHORT).show();
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
        horizontalListLoader(0);
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




     public void horizontalListLoader(int load){

         if(load == 0 ){
             loader.setVisibility(View.VISIBLE);
             list.setVisibility(View.GONE);
         }else  if (load == 1 ){
             loader.setVisibility(View.GONE);
             list.setVisibility(View.VISIBLE);
         }


     }


    @Override
    public void onDestroy() {
        super.onDestroy();
        queue.cancelAll(sr);
    }









}
