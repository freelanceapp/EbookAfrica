package com.apporio.ebookafrica.fragmentspecificcategory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.R;

/**
 * Created by spinnosolutions on 4/25/16.
 */
public class FragmentSpecificCategory  extends Fragment {

    int [] comic_images = {R.drawable.cover_one , R.drawable.cover_two , R.drawable.cover_three , R.drawable.cover_four , R.drawable.cover_five , R.drawable.cover_six , R.drawable.cover_seven , R.drawable.cover_eight , R.drawable.cover_nine , R.drawable.cover_ten  };

    GridView gridView ;
    private static RequestQueue queue ;
    private static StringRequest sr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_specific_category, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();


        gridView = (GridView) rootView.findViewById(R.id.grid);
        gridView.setAdapter(new AdapterSpecificGrid(getActivity(), comic_images));



        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }













}
