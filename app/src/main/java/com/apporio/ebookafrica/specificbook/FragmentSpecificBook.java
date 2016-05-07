package com.apporio.ebookafrica.specificbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.apporio.apporiologin.AppOrioLoginScreen;
import com.apporio.apporiologin.Contants;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.SharedPrefrencesKey;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.lalit.LalitActivity;

import views.CustomRatingBarGreen;

/**
 * Created by spinnosolutions on 4/25/16.
 */
public class FragmentSpecificBook extends Fragment {


    ListView bottomrelated_listview ;
    CustomRatingBarGreen  rating_bar_top;
    ImageView button_iimage ;
    LinearLayout buy_now ;
    SharedPreferences sharedpreferences  ;


    public FragmentSpecificBook(){}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedpreferences = getActivity().getSharedPreferences(Contants.MyPREFERENCES, Context.MODE_PRIVATE);

        View rootView = inflater.inflate(R.layout.fragment_specific_book, container, false);
        bottomrelated_listview = (ListView) rootView.findViewById(R.id.related_list);
        rating_bar_top = (CustomRatingBarGreen) rootView.findViewById(R.id.rating_bar_top);
        button_iimage = (ImageView) rootView.findViewById(R.id.button_iimage);
        buy_now = (LinearLayout) rootView.findViewById(R.id.buy_now);


        button_iimage.setImageResource(R.drawable.cover_2);

        bottomrelated_listview.setAdapter(new AdapterRelatedList(getActivity()));
        setListViewHeightBasedOnChildren(bottomrelated_listview);
        rating_bar_top.setScore(3);


        rootView.findViewById(R.id.preview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LalitActivity.class));
            }
        });



        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(sharedpreferences.getBoolean(SharedPrefrencesKey.LOGIN_STATE, false)){

                DownloadBook();
            }else {
                Intent in  = new Intent(getActivity() , AppOrioLoginScreen.class);
                in.putExtra("apporio_login_url" ,UrlsEbookAfrics.Login);
                in.putExtra("apporio_sign_url" , UrlsEbookAfrics.SighUp);
                startActivity(in);
            }
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




    private void DownloadBook() {
        Toast.makeText(getActivity() , "Starts Downloading" , Toast.LENGTH_SHORT).show();
    }









}
