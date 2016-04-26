package com.apporio.ebookafrica.categoryfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apporio.ebookafrica.R;

/**
 * Created by spinnosolutions on 4/21/16.
 */
public class FragmentCategory extends Fragment {


    ImageView  imageone , imagetwo ;

    public FragmentCategory(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_books_categories, container, false);

        imageone = (ImageView) rootView.findViewById(R.id.im1);
        imagetwo = (ImageView) rootView.findViewById(R.id.im2);



        imageone.setImageResource(R.drawable.editor_choise_banner);
        imagetwo.setImageResource(R.drawable.top_books_banner);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }




}
