package com.apporio.ebookafrica.homefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.SessionManager;

public class ActivityBannerAndSpecialCategory extends FragmentActivity {


    FragmentTransaction ft ;
    SessionManager sm ;
    TextView bannername ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_banner_and_special_category);

        bannername = (TextView) findViewById(R.id.bannername);
        sm = new SessionManager(ActivityBannerAndSpecialCategory.this);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        bannername.setText(""+getIntent().getExtras().getString("fragment_name"));

        setfragmentinContainer(new FragmentBannerAndSpecialCategory(getIntent().getExtras().getStringArrayList("array")), "" + R.string.title_activity_specific_book, 1);



    }




    public void    setfragmentinContainer(Fragment fragment, String fragment_name, int colour) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_container, fragment);
        ft.commit();


    }


}
