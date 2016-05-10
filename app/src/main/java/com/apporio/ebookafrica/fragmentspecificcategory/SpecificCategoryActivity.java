package com.apporio.ebookafrica.fragmentspecificcategory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.apporio.ebookafrica.R;

public class SpecificCategoryActivity extends AppCompatActivity {


    FragmentTransaction ft ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_category);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        setfragmentinContainer(new FragmentSpecificCategory(), "" + R.string.specific_category, 1);

    }



    private void    setfragmentinContainer(Fragment fragment , String fragment_name , int colour ) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_specific_category
                , fragment);
        ft.commit();


    }
}
