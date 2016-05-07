package com.apporio.apporiologin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

public class AppOrioLoginScreen extends AppCompatActivity {



    public static EventBus bus = EventBus.getDefault();
    LoginEvent levent = null ;


    Button  Login_Btn ;
    EditText email_edt , password_edt ;
    TextView new_account_txt ;

     String Login_Url = "";
     String SignUp_Url = "";

    RequestQueue queue ;
    SharedPreferences sharedpreferences  ;
    ImageView   banner    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        sharedpreferences = getSharedPreferences(Contants.MyPREFERENCES, Context.MODE_PRIVATE);

        queue = VolleySingleton.getInstance(AppOrioLoginScreen.this).getRequestQueue();
        Login_Url = getIntent().getExtras().getString("apporio_login_url");
        SignUp_Url = getIntent().getExtras().getString("apporio_sign_url");


        Login_Btn = (Button) findViewById(R.id.login_btn);
        email_edt = (EditText) findViewById(R.id.email_edt);
        password_edt = (EditText) findViewById(R.id.password_edt);
        new_account_txt = (TextView)findViewById(R.id.signup_txt);
        banner = (ImageView) findViewById(R.id.banner);


        banner.setImageResource(R.drawable.login_banner);





        new_account_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SignUp_Url== null ) {
                    Toast.makeText(AppOrioLoginScreen.this, "Sign up Url Is Not Set Up" + SignUp_Url, Toast.LENGTH_SHORT).show();
                }else {
                    Intent in  = new Intent(AppOrioLoginScreen.this , ApporioSignUpActivity.class);
                    in.putExtra("apporio_sign_url", "" + SignUp_Url);
                    startActivity(in);
                }
            }
        });













        Login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SharedPreferences.Editor editor = sharedpreferences.edit();
//                editor.putInt("login_state", 1);
//                editor.commit();
//
//
//                levent = new LoginEvent(1);
//                bus.post(levent);

                doLoginCall("tomas@onjection.com" , "123456");


            }
        });

    }

    private void doLoginCall(String username , String password) {

        Map<String, String> jsonParams = new HashMap<String, String>();


        jsonParams.put("password", password);         // change the parameter key name according to the back end developer script that we need to send
        jsonParams.put("username", username);         //

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, Login_Url,

                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        levent = new LoginEvent(""+response);
                        bus.post(levent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        levent = new LoginEvent(""+error);
                        bus.post(levent);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };
        queue.add(postRequest);
        Toast.makeText(AppOrioLoginScreen.this , "Start Executing in library need to replace it with loader " , Toast.LENGTH_SHORT).show();


    }

}
