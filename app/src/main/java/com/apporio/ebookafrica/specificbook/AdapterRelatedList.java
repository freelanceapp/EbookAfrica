package com.apporio.ebookafrica.specificbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CustomVolleyRequestQueue;

import java.util.ArrayList;

/**
 * Created by spinnosolutions on 4/26/16.
 */
public class AdapterRelatedList extends BaseAdapter {

    Context con ;
    LayoutInflater inflate;
    ImageLoader mImageLoader;

    ArrayList<String> bookname = new ArrayList<>();
    ArrayList<String> book_id = new ArrayList<>();
    ArrayList<String> book_image = new ArrayList<>();

    public AdapterRelatedList(Context con , ArrayList<String> bookname , ArrayList<String> book_id ,ArrayList<String> book_image ){
        this.con  = con ;
        this.bookname = bookname ;
        this.book_id = book_id ;
        this.book_image = book_image ;
        mImageLoader = CustomVolleyRequestQueue.getInstance(con).getImageLoader();
    }


    @Override
    public int getCount() {
        return book_id.size();
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
        NetworkImageView imagebook = (NetworkImageView) MyView_h_list.findViewById(R.id.imagebook);
        mImageLoader.get(book_image.get(position), ImageLoader.getImageListener(imagebook, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
        imagebook.setImageUrl(book_image.get(position), mImageLoader);

        return MyView_h_list;
    }



}
