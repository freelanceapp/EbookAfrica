package com.apporio.ebookafrica.fragmentyourbooks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by spinnosolutions on 4/9/16.
 */
public class YourBooksFragmentViewPagerAdapter extends FragmentPagerAdapter {


    String [] titles = {"Offline"  };

    public YourBooksFragmentViewPagerAdapter(FragmentActivity activity, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {

          //  case 0: return FragmentPurchased.newInstance("FragmentPurchased, Instance 1");
            case 0: return FragmentOffline.newInstance("FragmentBooked, Instance 2");
            default: return FragmentPurchased.newInstance("FragmentApplied, Default");
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 1;
    }
}
