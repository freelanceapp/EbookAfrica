package com.apporio.ebookafrica.homefragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CustomVolleyRequestQueue;
import com.apporio.ebookafrica.fragmentspecificcategory.SpecificCategoryActivity;
import com.apporio.ebookafrica.pojo.Product;
import com.apporio.ebookafrica.specificbook.SpecificBookActivity;

import java.util.ArrayList;
import java.util.List;

import views.HorizontalListView;

/**
 * Created by spinnosolutions on 5/11/16.
 */
public class AdapterHomePageList extends BaseAdapter {

    Context con;
    LayoutInflater inflate;

    ArrayList<String> category_names  = new ArrayList<>();
    ArrayList<String> category_id  = new ArrayList<>();
    ArrayList<List<Product>> Category_products  = new ArrayList<>();

    public AdapterHomePageList(Context con , ArrayList<String> category_names  , ArrayList<String>   category_id ,  ArrayList<List<Product>> Category_products  ) {
        this.con = con;
        this.category_names = category_names ;
        this.category_id = category_id ;
        this.Category_products = Category_products ;
    }


    @Override
    public int getCount() {
        return Category_products.size();
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
        View MyView;
        inflate = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyView = inflate.inflate(R.layout.item_related_list_view, parent, false);
        HorizontalListView h_view = (HorizontalListView) MyView.findViewById(R.id.horizintal_list);
        TextView categoryname  = (TextView) MyView.findViewById(R.id.heading_horizontal_list);
        LinearLayout category_link = (LinearLayout) MyView.findViewById(R.id.category_link);
        categoryname.setText(""+category_names.get(position)+ " >");
        h_view.setAdapter(new AdapterHoriZontalList(Category_products.get(position)));

        category_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(con, SpecificCategoryActivity.class);
                in.putExtra("category_id" , "" + category_id.get(position));
                con.startActivity(in);
            }
        });


        h_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positionnew, long id) {

                Intent in = new Intent(con, SpecificBookActivity.class);
                in.putExtra("product_id", "" + Category_products.get(position).get(positionnew).getProductId());
                in.putExtra("product_name" , ""+Category_products.get(position).get(positionnew).getName());
                con.startActivity(in);

            }
        });

        return MyView;
    }


    ///////// adapter for horizontal list
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