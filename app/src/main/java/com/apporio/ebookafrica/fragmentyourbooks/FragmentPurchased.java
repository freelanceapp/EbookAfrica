package com.apporio.ebookafrica.fragmentyourbooks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apporio.ebookafrica.R;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by spinnosolutions on 4/9/16.
 */
public class FragmentPurchased extends Fragment {


    StickyListHeadersListView list ;
    String [] prices = {"34" , "55" ,"43" ,"12" , "13" , "34"};
    String [] dates = {"01-april-2016" , "11-april-2016" ,"22-april-2016" ,"13-april-2016" , "14-april-2016" , "14-april-2016"};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_all, container, false);
        list = (StickyListHeadersListView) v.findViewById(R.id.list);
        list.setAdapter(new AdapterQuickReturn(getActivity(), prices, dates));
        return v;
    }





    public static FragmentPurchased newInstance(String text) {

        FragmentPurchased f = new FragmentPurchased();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
