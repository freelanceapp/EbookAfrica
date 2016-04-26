package com.apporio.ebookafrica.fragmentspecificcategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apporio.ebookafrica.R;

/**
 * Created by spinnosolutions on 4/25/16.
 */
public class AdapterSpecificGrid extends BaseAdapter {


    Context con ;
    LayoutInflater inflate;

    int [] images ;



    public AdapterSpecificGrid(Context con, int[] images){

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
        MyView = inflate.inflate(R.layout.item_specific_grid, parent, false);

        ImageView imagebook = (ImageView) MyView.findViewById(R.id.imagebook);
        imagebook.setImageResource(images[position]);

        return  MyView ;
    }
}
