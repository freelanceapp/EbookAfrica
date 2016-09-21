package com.apporio.ebookafrica.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.FragmentStatus;
import com.apporio.ebookafrica.constants.SessionManager;

public class PreviousOrdersActivity extends FragmentActivity {

    FragmentTransaction ft ;
    SessionManager sm ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_orders);
        sm = new SessionManager(PreviousOrdersActivity.this);


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        setfragmentinContainer(new FragmentPurchasedBooks(), "" + R.string.title_activity_specific_book, 1);
    }




    public void    setfragmentinContainer(Fragment fragment, String fragment_name, int colour) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_container, fragment);
        ft.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentStatus.LastOpenActivity = "AlreadyPurchasedActivity";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentStatus.LastOpenActivity = "";

    }
}
