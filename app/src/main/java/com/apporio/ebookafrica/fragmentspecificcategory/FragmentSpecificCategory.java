package com.apporio.ebookafrica.fragmentspecificcategory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
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
import com.apporio.ebookafrica.specificbook.SpecificBookActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by spinnosolutions on 4/25/16.
 */
@SuppressLint("ValidFragment")
public class FragmentSpecificCategory  extends Fragment {


    GridView gridView ;
    LinearLayout loader ;
    private static RequestQueue queue ;
    private static StringRequest sr;
    String categoryid ;


    ArrayList<String> product_image = new ArrayList<>();
    ArrayList<String> product_id = new ArrayList<>();
    ArrayList<String> product_name = new ArrayList<>();


    @SuppressLint("ValidFragment")
    public FragmentSpecificCategory(String categoryid){
        this.categoryid = categoryid ;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_specific_category, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        gridView = (GridView) rootView.findViewById(R.id.grid);
        loader = (LinearLayout) rootView.findViewById(R.id.loader);

        SpecificCategoryExecution();



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Intent in = new Intent(getActivity(), SpecificBookActivity.class);
                           in.putExtra("product_id", "" + product_id.get(position));
                           in.putExtra("product_name" , ""+product_name.get(position));
                           getActivity().startActivity(in);


            }
        });


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }





    public void SpecificCategoryExecution(){
        String url = UrlsEbookAfrics.AllCategories+categoryid;
        url=url.replace(" ","%20");
        Logger.d("Executing Specific Categories API   " + url);


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

                    SpecificCategoryActivity.category_name.setText(""+allcategories.getCategories().get(0).getName());

                    product_image.clear();
                     product_id .clear();
                    product_name.clear();


                    for(int i = 0 ; i< allcategories.getCategories().get(0).getProduct().size() ; i++){
                        product_image.add(""+allcategories.getCategories().get(0).getProduct().get(i).getImage());
                        product_id.add(""+allcategories.getCategories().get(0).getProduct().get(i).getProductId());
                        product_name.add(""+allcategories.getCategories().get(0).getProduct().get(i).getName());
                    }
                    horizontalListLoader(1);
                    gridView.setAdapter(new AdapterSpecificGrid(getActivity() , product_name , product_image , product_id));
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

    private void horizontalListLoader(int i) {
        if(i ==0 ){
            loader.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        }else if (i == 1 ){

            loader.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
        }
    }


}
