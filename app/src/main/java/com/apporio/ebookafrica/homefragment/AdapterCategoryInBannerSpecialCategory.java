package com.apporio.ebookafrica.homefragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CustomVolleyRequestQueue;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.pojo.AllCategories;
import com.apporio.ebookafrica.pojo.Product;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import views.HorizontalListView;

/**
 * Created by spinnosolutions on 5/16/16.
 */
public class AdapterCategoryInBannerSpecialCategory extends BaseAdapter {


    Context con ;
    ArrayList<String> category_urls ;
    LayoutInflater inflate;
    private static RequestQueue queue ;
    private static StringRequest sr;


    public AdapterCategoryInBannerSpecialCategory(Context con , ArrayList<String>  category_urls ){

        this.con = con ;
        this.category_urls = category_urls ;
        queue = VolleySingleton.getInstance(con).getRequestQueue();
    }
    @Override
    public int getCount() {
        return category_urls.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View MyView;
        inflate = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyView = inflate.inflate(R.layout.item_related_list_view, parent, false);



        HorizontalListView h_view = (HorizontalListView) MyView.findViewById(R.id.horizintal_list);
        TextView categoryname  = (TextView) MyView.findViewById(R.id.heading_horizontal_list);
        CategoryExecutionInadapter(category_urls.get(position) , h_view , categoryname);
        return MyView;
    }






    public void CategoryExecutionInadapter(String categorylink ,  final HorizontalListView h_view , final TextView categoryname ){
        String url = categorylink ;
        url=url.replace(" ","%20");
        Logger.d("Executing Category API   INSIDE ADAPTER " + categorylink);


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

                    ArrayList<List<Product>> Category_products  = new ArrayList<>();
                    for(int i = 0 ; i< allcategories.getCategories().size() ; i++){
                        Category_products.add(allcategories.getCategories().get(i).getProduct());
                    }
                    categoryname.setText(""+allcategories.getCategories().get(0).getName());
                    h_view.setAdapter(new AdapterHoriZontalList(Category_products.get(0)));

                }else {
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


















    public class AdapterHoriZontalList extends BaseAdapter {

        List<Product> products = new ArrayList<>();
        ImageLoader mImageLoader;


        public AdapterHoriZontalList(List<Product> products) {

            this.products = products;
            mImageLoader = CustomVolleyRequestQueue.getInstance(con).getImageLoader();
        }

        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View MyView_h_list;
            inflate = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            MyView_h_list = inflate.inflate(R.layout.horizontal_list_items, parent, false);
            NetworkImageView imagebook = (NetworkImageView) MyView_h_list.findViewById(R.id.imagebook);
            mImageLoader.get(products.get(position).getImage(), ImageLoader.getImageListener(imagebook, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
            imagebook.setImageUrl(products.get(position).getImage(), mImageLoader);

            return MyView_h_list;
        }
    }





}
