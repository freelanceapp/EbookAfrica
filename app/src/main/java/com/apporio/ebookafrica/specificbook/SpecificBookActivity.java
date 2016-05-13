package com.apporio.ebookafrica.specificbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologin.AppOrioLoginScreen;
import com.apporio.apporiologin.ApporioSignUpActivity;
import com.apporio.apporiologin.LoginEvent;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.SessionManager;
import com.apporio.ebookafrica.pojo.LoginSuccess;
import com.apporio.ebookafrica.pojo.LoginUnSuccess;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.greenrobot.event.EventBus;

public class SpecificBookActivity extends FragmentActivity {


    FragmentTransaction ft ;
    SessionManager  sm ;
    TextView bookname ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_book);
        bookname = (TextView) findViewById(R.id.bookname);
        EventBus.getDefault().register(this);
        sm = new SessionManager(SpecificBookActivity.this);

        bookname.setText(""+getIntent().getExtras().getString("product_name"));

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setfragmentinContainer(new FragmentSpecificBook("" + getIntent().getExtras().getString("product_id")), "" + R.string.title_activity_specific_book, 1);
    }




    public void    setfragmentinContainer(Fragment fragment, String fragment_name, int colour) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_container, fragment);
        ft.commit();


    }


    public void onEvent(LoginEvent Value){
        if(Value.LoginDeterminer() == 0 ){
            Toast.makeText(SpecificBookActivity.this, "" + Value.LoginEvent(), Toast.LENGTH_SHORT).show();
        }else{
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            ResponseChecker rcheck = new ResponseChecker();
            rcheck = gson.fromJson(Value.LoginEvent(), ResponseChecker.class);
            if(rcheck.getStatus().equals("success")){
                LoginSuccess l_success = new LoginSuccess();
                l_success = gson.fromJson(Value.LoginEvent(), LoginSuccess.class);
                if(ApporioSignUpActivity.Activity_Is_Open){
                    Toast.makeText(SpecificBookActivity.this, "Welcome" +l_success.getCustomer().getFirstname()+" "+l_success.getCustomer().getLastname(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SpecificBookActivity.this, "Welcome" +l_success.getCustomer().getFirstname()+" "+l_success.getCustomer().getLastname(), Toast.LENGTH_SHORT).show();
                }
                sm.createLoginSession("" + l_success.getCustomer().getCustomerId(),
                        "" + l_success.getCustomer().getFirstname(),
                        "" + l_success.getCustomer().getLastname(),
                        "" + l_success.getCustomer().getEmail(),
                        "" + l_success.getCustomer().getTelephone(),
                        "" + l_success.getCustomer().getFax(),
                        "" + l_success.getCustomer().getNewsletter(),
                        "" + l_success.getCustomer().getWishlist(),
                        "" + l_success.getCustomer().getCart(),
                        "" + l_success.getCustomer().getTotal());

                if(ApporioSignUpActivity.Activity_Is_Open){
                    ApporioSignUpActivity.activity.finish();
                    AppOrioLoginScreen.activity.finish();
                }else {
                    AppOrioLoginScreen.activity.finish();
                }
                AppOrioLoginScreen.activity.finish();

            }else if (rcheck.getStatus().equals("failed")){
                LoginUnSuccess l_unsuccess = new LoginUnSuccess();
                l_unsuccess = gson.fromJson(Value.LoginEvent(), LoginUnSuccess.class);
                Toast.makeText(SpecificBookActivity.this, "" +l_unsuccess.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }














}
