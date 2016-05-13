package com.apporio.ebookafrica.homefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by admin on 2/4/2016.
 */
public class FragmentHome extends Fragment {


    public  SliderLayout image_slider;
    public ListView list ;
    TextView loader ;


    public FragmentHome(){}





    private static RequestQueue queue ;
    private static StringRequest sr;

    public static  Context context ;

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
        loader = (TextView) rootView.findViewById(R.id.loader);



        BannerApiExecution();
        AllCategoriesIncludingProductsExecution();



        return rootView;
    }

    private void setImageSlider(ArrayList<String> images) {

        HashMap<String,String> file_maps = new HashMap<String, String>();
        for(int i = 0 ; i < images.size() ; i++){
            file_maps.put(""+i, ""+images.get(i));
        }
        for(String name : file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);

            image_slider.addSlider(textSliderView);
        }


        image_slider.setPresetTransformer(SliderLayout.Transformer.Default);
        image_slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        image_slider.setCustomAnimation(new DescriptionAnimation());
        image_slider.setDuration(4000);

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
                    for(int i = 0 ; i< bsp.getSlider().size() ; i++){
                        bannerimages.add(bsp.getSlider().get(i).getImage());
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
