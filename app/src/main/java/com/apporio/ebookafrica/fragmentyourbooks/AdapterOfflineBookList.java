package com.apporio.ebookafrica.fragmentyourbooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apporio.ebookafrica.R;

/**
 * Created by spinnosolutions on 5/13/16.
 */
public class AdapterOfflineBookList extends BaseAdapter {


    Context con ;
    String[] theNamesOfFiles ;
    LayoutInflater inflate;

    public AdapterOfflineBookList(Context con , String[] theNamesOfFiles ){

        this.con = con ;
        this.theNamesOfFiles = theNamesOfFiles ;
    }


    @Override
    public int getCount() {
        return theNamesOfFiles.length;
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
        TextView text = (TextView) MyView.findViewById(R.id.bookname);
        text.setText(""+theNamesOfFiles[position].replace("_" , " "));

        return MyView;
    }
}
