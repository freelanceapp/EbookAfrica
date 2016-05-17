package com.apporio.ebookafrica.fragmentyourbooks;

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
import com.apporio.ebookafrica.database.PurchasedProductManager;
import com.apporio.ebookafrica.database.Purchasedproducts;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by spinnosolutions on 5/13/16.
 */
public class AdapterOfflineBookList extends BaseAdapter {


    Context con ;
    LayoutInflater inflate;
    ImageLoader mImageLoader;
    RealmResults<Purchasedproducts>  data ;
    ArrayList<String> booknames = new ArrayList<>();


    public AdapterOfflineBookList(Context con , ArrayList<String> booknames ){

        this.con = con ;
        mImageLoader = CustomVolleyRequestQueue.getInstance(con).getImageLoader();
        data = new PurchasedProductManager(con).getFullTable() ;
        this.booknames = booknames ;
    }


    @Override
    public int getCount() {
        return booknames.size();
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
        MyView = inflate.inflate(R.layout.simple_list_item, parent, false);
        NetworkImageView  imagebook  = (NetworkImageView) MyView.findViewById(R.id.imagebook);
        TextView text = (TextView) MyView.findViewById(R.id.bookname);
        TextView author_name = (TextView) MyView.findViewById(R.id.author_name);
        TextView pages  = (TextView) MyView.findViewById(R.id.pages);
        TextView hours = (TextView) MyView.findViewById(R.id.hours);
        text.setText("" + booknames.get(position).replace("_", " ").replace(".epub" ,""));
        pages.setText(""+data.get(position).getPages()+" Pages "+" | ");
        hours.setText(""+data.get(position).getHours()+" hours");
        author_name.setText(""+data.get(position).getAuthor());

        mImageLoader.get("" + data.get(position).getImage(), ImageLoader.getImageListener(imagebook, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
        imagebook.setImageUrl(""+data.get(position).getImage(), mImageLoader);

        return MyView;
    }
}
