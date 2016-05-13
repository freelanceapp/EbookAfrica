package com.apporio.ebookafrica.fragmentspecificcategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CustomVolleyRequestQueue;

import java.util.ArrayList;

/**
 * Created by spinnosolutions on 4/25/16.
 */
public class AdapterSpecificGrid extends BaseAdapter {


    Context con ;
    LayoutInflater inflate;


    ArrayList<String> product_image = new ArrayList<>();
    ArrayList<String> product_id = new ArrayList<>();
    ArrayList<String> product_name = new ArrayList<>();
    ImageLoader mImageLoader;



    public AdapterSpecificGrid(Context con,  ArrayList<String> product_name,  ArrayList<String> product_image , ArrayList<String> product_id){

        this.con  = con ;
        this.product_name = product_name;
        this.product_image = product_image ;
        this.product_id = product_id ;
        mImageLoader = CustomVolleyRequestQueue.getInstance(con).getImageLoader();
    }
    @Override
    public int getCount() {
        return product_id.size();
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
        View MyView ;
        inflate = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyView = inflate.inflate(R.layout.item_specific_grid, parent, false);

        NetworkImageView image_book = (NetworkImageView) MyView.findViewById(R.id.image_book);
        TextView book_name  = (TextView) MyView.findViewById(R.id.book_name);


        mImageLoader.get(product_image.get(position), ImageLoader.getImageListener(image_book, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
        image_book.setImageUrl(product_image.get(position), mImageLoader);
        book_name.setText(""+product_name.get(position));

        return  MyView ;
    }
}
