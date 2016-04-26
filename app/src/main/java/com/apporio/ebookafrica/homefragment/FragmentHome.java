package com.apporio.ebookafrica.homefragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.specificbook.SpecificBookActivity;

import views.HorizontalListView;


/**
 * Created by admin on 2/4/2016.
 */
public class FragmentHome extends Fragment {


    ImageView top_banner ;

    public FragmentHome(){}



    int [] comic_images = {R.drawable.cover_one , R.drawable.cover_two , R.drawable.cover_three , R.drawable.cover_four , R.drawable.cover_five , R.drawable.cover_six , R.drawable.cover_seven , R.drawable.cover_eight , R.drawable.cover_nine , R.drawable.cover_ten  };
    int [] nobel_images = {R.drawable.cover_1 , R.drawable.cover_2 , R.drawable.cover_3 , R.drawable.cover_4 , R.drawable.cover_5 , R.drawable.cover_6 , R.drawable.cover_7 , R.drawable.cover_8 , R.drawable.cover_9 , R.drawable.cover_10  };

    HorizontalListView  horizintal_list_one , horizintal_list_two ;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        top_banner = (ImageView) rootView.findViewById(R.id.top_banner);


        top_banner.setImageResource(R.drawable.vintagebooks);
        horizintal_list_one = (HorizontalListView) rootView.findViewById(R.id.horizontal_list_one);
        horizintal_list_two = (HorizontalListView) rootView.findViewById(R.id.horizontal_list_two);


        horizintal_list_one.setAdapter(new AdapterHorizontalList(getActivity() , comic_images));
        horizintal_list_two.setAdapter(new AdapterHorizontalList(getActivity() , nobel_images));



        horizintal_list_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in  = new Intent(getActivity() , SpecificBookActivity.class);
                getActivity().startActivity(in);
            }
        });


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }




}
