package com.apporio.ebookafrica.specificbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.lalit.LalitActivity;

import views.CustomRatingBarGreen;

/**
 * Created by spinnosolutions on 4/25/16.
 */
public class FragmentSpecificBook extends Fragment {


    ListView bottomrelated_listview ;
    CustomRatingBarGreen  rating_bar_top;
    ImageView button_iimage ;

    public FragmentSpecificBook(){}

    int [] nobel_images = {R.drawable.cover_1 , R.drawable.cover_2 , R.drawable.cover_3 , R.drawable.cover_4 , R.drawable.cover_5 , R.drawable.cover_6 , R.drawable.cover_7 , R.drawable.cover_8 , R.drawable.cover_9 , R.drawable.cover_10  };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_specific_book, container, false);
        bottomrelated_listview = (ListView) rootView.findViewById(R.id.related_list);
        rating_bar_top = (CustomRatingBarGreen) rootView.findViewById(R.id.rating_bar_top);
        button_iimage = (ImageView) rootView.findViewById(R.id.button_iimage);


        button_iimage.setImageResource(R.drawable.cover_2);

        bottomrelated_listview.setAdapter(new AdapterRelatedList(getActivity()));
        setListViewHeightBasedOnChildren(bottomrelated_listview);
        rating_bar_top.setScore(3);


        rootView.findViewById(R.id.preview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , LalitActivity.class));
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }







    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }





}
