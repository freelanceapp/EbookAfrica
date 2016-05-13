package com.apporio.ebookafrica.fragmentyourbooks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apporio.ebookafrica.FragmentStatus;
import com.apporio.ebookafrica.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

/**
 * Created by spinnosolutions on 4/1/16.
 */
public class FragmentYourBooksMain extends Fragment {

    View rootView ;
    SmartTabLayout smarttablayout ;
    ViewPager viewPager ;



    public FragmentYourBooksMain() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentStatus.GetOpenfragment = "FragmentYourBooksMain";
        rootView = inflater.inflate(R.layout.fragment_your_books_main, container, false);
        init();
        viewPager.setAdapter(buildAdapter());
        smarttablayout.setViewPager(viewPager);

        return rootView;
    }



    public void init() {
        smarttablayout = (SmartTabLayout) rootView.findViewById(R.id.tabs);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
    }





    private PagerAdapter buildAdapter() {
        return(new YourBooksFragmentViewPagerAdapter(getActivity(), getChildFragmentManager()));
    }

}
