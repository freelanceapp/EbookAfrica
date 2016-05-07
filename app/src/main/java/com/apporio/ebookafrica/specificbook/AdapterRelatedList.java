package com.apporio.ebookafrica.specificbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apporio.ebookafrica.R;

import views.HorizontalListView;

/**
 * Created by spinnosolutions on 4/26/16.
 */
public class AdapterRelatedList extends BaseAdapter {

    Context con ;
    LayoutInflater inflate;

    int [] comic_images = {R.drawable.cover_one , R.drawable.cover_two , R.drawable.cover_three , R.drawable.cover_four , R.drawable.cover_five , R.drawable.cover_six , R.drawable.cover_seven , R.drawable.cover_eight , R.drawable.cover_nine , R.drawable.cover_ten  };

    public AdapterRelatedList(Context con ){

        this.con  = con ;
    }




    @Override
    public int getCount() {
        return 3;
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
        MyView = inflate.inflate(R.layout.item_related_list_view, parent, false);
        HorizontalListView h_view = (HorizontalListView) MyView.findViewById(R.id.horizintal_list);
        h_view.setAdapter(new AdapterHoriZontalList(comic_images ));
        return MyView;
    }











///////// adapter for horizontal list
    public class AdapterHoriZontalList extends BaseAdapter{

    int [] images_h_list ;



    public  AdapterHoriZontalList(int [] images_h_list ){

        this.images_h_list = images_h_list ;
    }

    @Override
    public int getCount() {
        return images_h_list.length;
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
        View MyView_h_list ;
        inflate = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyView_h_list = inflate.inflate(R.layout.horizontal_list_items, parent, false);
        ImageView imagebook = (ImageView) MyView_h_list.findViewById(R.id.imagebook);
        imagebook.setImageResource(images_h_list[position]);
        return MyView_h_list;
    }
}




}
