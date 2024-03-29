package com.apporio.apporiologin;

import android.app.Activity;
import android.content.Intent;
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
    ImageView   banner    ;
    public static Activity activity ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        activity = this ;
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
                if(email_edt.getText().toString().equals("")||email_edt.getText().toString() == null ||password_edt.getText().toString().equals("")||password_edt.getText().toString() == null ){
                    Toast.makeText(AppOrioLoginScreen.this, "Required field missing" , Toast.LENGTH_SHORT).show();
                }else {

                    if(new ConnectionDetector(getApplicationContext()).isConnectingToInternet()){
                        doLoginCall(""+email_edt.getText().toString() , ""+password_edt.getText().toString());
                    }else {
                        levent = new LoginEvent("No InterNet Connection Available" , 0);
                        bus.post(levent);
                    }

                }
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
                        levent = new LoginEvent(""+response , 1);
                        bus.post(levent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        levent = new LoginEvent("Server Error ! " , 0 );
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


    }

}
