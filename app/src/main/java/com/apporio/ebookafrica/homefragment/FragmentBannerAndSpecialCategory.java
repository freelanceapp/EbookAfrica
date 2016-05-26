package com.apporio.ebookafrica.homefragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.constants.FragmentStatus;
import com.apporio.ebookafrica.R;

import java.util.ArrayList;

/**
 * Created by spinnosolutions on 5/16/16.
 */
@SuppressLint("ValidFragment")
public class FragmentBannerAndSpecialCategory extends Fragment {


    ListView list ;
    private static RequestQueue queue ;
    private static StringRequest sr;
    ArrayList<String>  category_urls  = new ArrayList<>();




    @SuppressLint("ValidFragment")
    public FragmentBannerAndSpecialCategory(ArrayList<String>  category_urls){

        this.category_urls = category_urls ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentStatus.GetOpenfragment = "FragmentBannerAndSpecialCategory";
        View rootView = inflater.inflate(R.layout.fragment_banner_special_category, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        list  = (ListView) rootView.findViewById(R.id.list);



        list.setAdapter(new AdapterCategoryInBannerSpecialCategory(getActivity(), category_urls));
      //  setListViewHeightBasedOnChildren(list);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }






    @Override
    public void onDestroy() {
        super.onDestroy();
        if(sr != null){
            queue.cancelAll(sr);
        }

    }
}