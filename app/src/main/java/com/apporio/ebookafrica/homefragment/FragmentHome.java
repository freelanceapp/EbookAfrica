package com.apporio.ebookafrica.homefragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.fragmentspecificcategory.SpecificCategoryActivity;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.pojo.BannerSliderPojo;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.apporio.ebookafrica.specificbook.SpecificBookActivity;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

import views.HorizontalListView;


/**
 * Created by admin on 2/4/2016.
 */
public class FragmentHome extends Fragment {


    public  SliderLayout image_slider;

    ImageView top_banner ;

    public FragmentHome(){}



    int [] comic_images = {R.drawable.cover_one , R.drawable.cover_two , R.drawable.cover_three , R.drawable.cover_four , R.drawable.cover_five , R.drawable.cover_six , R.drawable.cover_seven , R.drawable.cover_eight , R.drawable.cover_nine , R.drawable.cover_ten  };
    int [] nobel_images = {R.drawable.cover_1 , R.drawable.cover_2 , R.drawable.cover_3 , R.drawable.cover_4 , R.drawable.cover_5 , R.drawable.cover_6 , R.drawable.cover_7 , R.drawable.cover_8 , R.drawable.cover_9 , R.drawable.cover_10  };

    HorizontalListView  horizintal_list_one , horizintal_list_two ;

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

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        context  = getActivity() ;
        top_banner = (ImageView) rootView.findViewById(R.id.top_banner);
        image_slider = (SliderLayout)rootView.findViewById(R.id.slider);
        horizintal_list_one = (HorizontalListView) rootView.findViewById(R.id.horizontal_list_one);
        horizintal_list_two = (HorizontalListView) rootView.findViewById(R.id.horizontal_list_two);


        top_banner.setImageResource(R.drawable.vintagebooks);
        horizintal_list_one.setAdapter(new AdapterHorizontalList(getActivity(), comic_images));
        horizintal_list_two.setAdapter(new AdapterHorizontalList(getActivity(), nobel_images));



        horizintal_list_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity(), SpecificBookActivity.class);
                getActivity().startActivity(in);
            }
        });


        horizintal_list_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity(), SpecificBookActivity.class);
                getActivity().startActivity(in);
            }
        });


        rootView.findViewById(R.id.morebs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), SpecificCategoryActivity.class);
                getActivity().startActivity(in);
            }
        });



        rootView.findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), SpecificCategoryActivity.class);
                getActivity().startActivity(in);
            }
        });

        BannerApiExecution();



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











}
