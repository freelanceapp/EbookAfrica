package com.apporio.ebookafrica.categoryfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.constants.FragmentStatus;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CheckNetwork;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.fragmentspecificcategory.SpecificCategoryActivity;
import com.apporio.ebookafrica.homefragment.ActivityBannerAndSpecialCategory;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.pojo.AllCategories;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.apporio.ebookafrica.pojo.SpecialCategory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spinnosolutions on 4/21/16.
 */
public class FragmentCategory extends Fragment {


    ImageView  imageone , imagetwo ;
    ListView  list ;
    private static RequestQueue queue ;
    private static StringRequest sr;
    TextView specialcategory_one , specialcategory_two ;
    ArrayList<List<String>> categories_id_Array  = new ArrayList<>();
    ArrayList<String> banner_names = new ArrayList<>();
    LinearLayout loader;
    View no_internet_view ;
    ParallaxScrollView mainview ;





    public FragmentCategory(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentStatus.GetOpenfragment = "FragmentCategory";
        View rootView = inflater.inflate(R.layout.fragment_books_categories, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        imageone = (ImageView) rootView.findViewById(R.id.im1);
        imagetwo = (ImageView) rootView.findViewById(R.id.im2);
        list  = (ListView) rootView.findViewById(R.id.list);
        specialcategory_one = (TextView) rootView.findViewById(R.id.specialcategory_one);
        specialcategory_two = (TextView) rootView.findViewById(R.id.specialcategory_two);
        loader = (LinearLayout) rootView.findViewById(R.id.loader);
        no_internet_view = rootView.findViewById(R.id.no_internet_view);
        mainview = (ParallaxScrollView) rootView.findViewById(R.id.mainview);


        imageone.setImageResource(R.drawable.editor_choise_banner);
        imagetwo.setImageResource(R.drawable.top_books_banner);




        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent in = new Intent(getActivity(), SpecificCategoryActivity.class);
                in.putExtra("category_id", "" + CategoryfragmentConstant.Category_id.get(position));
                getActivity().startActivity(in);
            }
        });




        rootView.findViewById(R.id.special_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> categories = new ArrayList<String>();
                for (int j = 0; j < categories_id_Array.get(0).size(); j++) {
                    categories.add(categories_id_Array.get(0).get(j));
                }


                Intent in = new Intent(getActivity(), ActivityBannerAndSpecialCategory.class);
                in.putStringArrayListExtra("array", categories);
                in.putExtra("fragment_name", "" + banner_names.get(0));
                startActivity(in);
            }
        });




        rootView.findViewById(R.id.special_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> categories =new ArrayList<String>();
                for(int j = 0 ; j< categories_id_Array.get(1).size() ; j++){
                    categories.add(categories_id_Array.get(1).get(j));
                }
                Intent in  = new Intent(getActivity() ,ActivityBannerAndSpecialCategory.class);
                in.putStringArrayListExtra("array" ,categories);
                in.putExtra("fragment_name" , ""+banner_names.get(1));
                startActivity(in);
            }
        });




        rootView.findViewById(R.id.got_to_offline_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Go to the offline section", Toast.LENGTH_SHORT).show();
            }
        });


        rootView.findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doExecutionAccordingToNetworkState();
            }
        });



        doExecutionAccordingToNetworkState();



        return rootView;
    }




    public void doExecutionAccordingToNetworkState(){
        if(new CheckNetwork(getActivity()).isNetworkOnline()){
            mainview.setVisibility(View.VISIBLE);
            no_internet_view.setVisibility(View.GONE);
            SpecialCategoryExecution();
        }else {

            Toast.makeText(getActivity() , "No Internet Connection available" , Toast.LENGTH_SHORT).show();
            mainview.setVisibility(View.GONE);
            no_internet_view.setVisibility(View.VISIBLE);
        }
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public void setListView( ArrayList<String> categoryname ,  ArrayList<String> categoryid){

        list.setAdapter(new AdapterCategoryList(getActivity(), categoryname, categoryid));
        setListViewHeightBasedOnChildren(list);
    }



    public void CategoryApiExecution(){

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
                    loader.setVisibility(View.GONE);
                    AllCategories allcategories = new AllCategories();
                    allcategories = gson.fromJson(response, AllCategories.class);

                        CategoryfragmentConstant.Category_name.clear();
                        CategoryfragmentConstant. Category_id.clear();

                        for(int i = 0 ; i< allcategories.getCategories().size() ; i++){
                            CategoryfragmentConstant.Category_name.add(""+allcategories.getCategories().get(i).getName());
                            CategoryfragmentConstant.Category_id.add(""+allcategories.getCategories().get(i).getCategoryId());
                        }


                    if(FragmentStatus.GetOpenfragment.equals("FragmentCategory")){
                        setListView(CategoryfragmentConstant.Category_name ,CategoryfragmentConstant. Category_id);
                    }


                }else {
                    Toast.makeText(getActivity() , "No categories available" , Toast.LENGTH_SHORT).show();
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




    public void SpecialCategoryExecution(){

        String url = UrlsEbookAfrics.specialcategory;
        url=url.replace(" ","%20");
        Logger.d("Executing Special Categories API   " + url);


        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResponseChecker rcheck = new ResponseChecker();
                rcheck = gson.fromJson(response, ResponseChecker.class);



                if (rcheck.getStatus().equals("success")){
                    CategoryApiExecution();
                    SpecialCategory sc = new SpecialCategory();
                    sc = gson.fromJson(response, SpecialCategory.class);


                    banner_names.clear();



                    for(int i = 0 ; i< sc.getSpecialCategorySlider().size() ; i++){
                        categories_id_Array.add(sc.getSpecialCategorySlider().get(i).getCategoryId());
                        banner_names.add("" + sc.getSpecialCategorySlider().get(i).getTitle());
                    }

                    specialcategory_one.setText(""+sc.getSpecialCategorySlider().get(0).getTitle());
                    specialcategory_two.setText("" + sc.getSpecialCategorySlider().get(1).getTitle());

                }else {
                    Toast.makeText(getActivity() , "No categories available" , Toast.LENGTH_SHORT).show();
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
        loader.setVisibility(View.VISIBLE);

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


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sr != null){
            queue.cancelAll(sr);
        }

    }
}
