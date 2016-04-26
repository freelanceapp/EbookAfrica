package com.apporio.ebookafrica.homefragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apporio.ebookafrica.R;

/**
 * Created by spinnosolutions on 4/21/16.
 */
public class AdapterHorizontalList extends BaseAdapter {

    Context con ;
    LayoutInflater inflate;

    int [] images ;



    public AdapterHorizontalList(Context con, int[] images){

        this.con  = con ;
        this.images = images ;
    }
    @Override
    public int getCount() {
        return images.length;
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
        MyView = inflate.inflate(R.layout.horizontal_list_items, parent, false);


        ImageView imagebook = (ImageView) MyView.findViewById(R.id.imagebook);

        imagebook.setImageResource(images[position]);



        return  MyView ;
    }
}
