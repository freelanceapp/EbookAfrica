package com.apporio.ebookafrica.categoryfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apporio.ebookafrica.R;

import java.util.ArrayList;

/**
 * Created by spinnosolutions on 5/10/16.
 */
public class AdapterCategoryList extends BaseAdapter{


    Context con ;
    LayoutInflater inflate;

    ArrayList<String> Categories_name ;



    public AdapterCategoryList(Context con,  ArrayList<String> Categories_name , ArrayList<String> categoryid ){

        this.con  = con ;
        this.Categories_name = Categories_name ;
    }
    @Override
    public int getCount() {
        return Categories_name.size();
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
        MyView = inflate.inflate(R.layout.category_item_text, parent, false);

        TextView  category_name = (TextView) MyView.findViewById(R.id.text);
        category_name.setText(""+Categories_name.get(position));

        return  MyView ;
    }

}
