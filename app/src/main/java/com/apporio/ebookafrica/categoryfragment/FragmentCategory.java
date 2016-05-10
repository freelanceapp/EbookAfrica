package com.apporio.ebookafrica.categoryfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
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
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.pojo.AllCategories;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by spinnosolutions on 4/21/16.
 */
public class FragmentCategory extends Fragment {


    ImageView  imageone , imagetwo ;
    ListView  list ;
    private static RequestQueue queue ;
    private static StringRequest sr;

    public FragmentCategory(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_books_categories, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        imageone = (ImageView) rootView.findViewById(R.id.im1);
        imagetwo = (ImageView) rootView.findViewById(R.id.im2);
        list  = (ListView) rootView.findViewById(R.id.list);



        imageone.setImageResource(R.drawable.editor_choise_banner);
        imagetwo.setImageResource(R.drawable.top_books_banner);


        CategoryApiExecution();

        return rootView;
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
                    AllCategories allcategories = new AllCategories();
                    allcategories = gson.fromJson(response, AllCategories.class);

                    ArrayList<String> Category_name = new ArrayList<>();
                    ArrayList<String> Category_id = new ArrayList<>();
                    for(int i = 0 ; i< allcategories.getCategories().size() ; i++){
                        Category_name.add(""+allcategories.getCategories().get(i).getName());
                        Category_id.add(""+allcategories.getCategories().get(i).getCategoryId());
                    }
                    setListView(Category_name , Category_id);
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




}
