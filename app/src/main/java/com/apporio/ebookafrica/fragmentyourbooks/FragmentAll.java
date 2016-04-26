package com.apporio.ebookafrica.fragmentyourbooks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apporio.ebookafrica.R;

/**
 * Created by spinnosolutions on 4/9/16.
 */
public class FragmentAll extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_all, container, false);

        return v;
    }





    public static FragmentAll newInstance(String text) {

        FragmentAll f = new FragmentAll();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

}
