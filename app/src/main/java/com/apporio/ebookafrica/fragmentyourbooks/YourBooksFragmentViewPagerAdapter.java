package com.apporio.ebookafrica.fragmentyourbooks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by spinnosolutions on 4/9/16.
 */
public class YourBooksFragmentViewPagerAdapter extends FragmentPagerAdapter {


    String [] titles = {"Applied" ,"Booked","Worked"  };

    public YourBooksFragmentViewPagerAdapter(FragmentActivity activity, FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {

            case 0: return FragmentAll.newInstance("FragmentApplied, Instance 4");
            case 1: return FragmentOffline.newInstance("FragmentBooked, Instance 5");
            case 2: return FragmentCollection.newInstance("FragmentWorked, Instance 6");
            default: return FragmentAll.newInstance("FragmentApplied, Default");
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
