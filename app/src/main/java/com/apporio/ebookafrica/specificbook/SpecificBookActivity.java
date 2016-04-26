package com.apporio.ebookafrica.specificbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.apporio.ebookafrica.R;

public class SpecificBookActivity extends FragmentActivity {


    FragmentTransaction ft ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_book);


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        setfragmentinContainer(new FragmentSpecificBook(), "" + R.string.specific_category, 1);


    }





    private void    setfragmentinContainer(Fragment fragment , String fragment_name , int colour ) {
        if(colour == 1){
        }else if(colour == 2){
        }else if(colour == 3 ){
        }

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container
                , fragment);
        ft.commit();


    }

}
