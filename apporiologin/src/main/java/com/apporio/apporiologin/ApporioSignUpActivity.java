package com.apporio.apporiologin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class ApporioSignUpActivity extends Activity {


    public static EventBus bus = EventBus.getDefault();
    LoginEvent levent = null ;
    RequestQueue queue ;


    ImageView  banner_image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apporio_sign_up);
        queue = VolleySingleton.getInstance(ApporioSignUpActivity.this).getRequestQueue();
        banner_image = (ImageView) findViewById(R.id.banner_image);
        banner_image.setImageResource(R.drawable.signupbanner);


        findViewById(R.id.signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignUpCall(getIntent().getExtras().getString("apporio_sign_url") , "samir" , "goel" ,"343245" , "samir@apporio.com" ,"123456");
            }
        });


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void doSignUpCall(String SignUp_Url , String firstname , String lastname  , String phone  , String email  , String password ) {

        Map<String, String> jsonParams = new HashMap<String, String>();


        jsonParams.put("firstname", firstname);         // change the parameter key name according to the back end developer script that we need to send
        jsonParams.put("lastname", lastname);
        jsonParams.put("phone", phone);
        jsonParams.put("password", password);
        jsonParams.put("email", email);

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, SignUp_Url,

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
        Toast.makeText(ApporioSignUpActivity.this, "Signbing up loader", Toast.LENGTH_SHORT).show();
    }

}
