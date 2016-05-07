package com.apporio.ebookafrica.specificbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.apporio.apporiologin.LoginEvent;
import com.apporio.ebookafrica.R;

import de.greenrobot.event.EventBus;

public class SpecificBookActivity extends FragmentActivity {


    FragmentTransaction ft ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_book);
        EventBus.getDefault().register(this);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        setfragmentinContainer(new FragmentSpecificBook(), "" + R.string.title_activity_specific_book, 1);


    }





    private void    setfragmentinContainer(Fragment fragment , String fragment_name , int colour ) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container
                , fragment);
        ft.commit();


    }


    public void onEvent(LoginEvent Value){
        Toast.makeText(SpecificBookActivity.this, "Response is " + Value.LoginEvent(), Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
