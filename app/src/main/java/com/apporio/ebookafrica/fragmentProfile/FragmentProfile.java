package com.apporio.ebookafrica.fragmentProfile;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.apporio.apporiologin.AppOrioLoginScreen;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.FragmentStatus;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CheckNetwork;
import com.apporio.ebookafrica.constants.SessionManager;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.order.PreviousOrdersActivity;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spinnosolutions on 5/20/16.
 */
public class FragmentProfile extends Fragment {

    SessionManager sm ;
    Dialog dialogPassword  ;

    private static RequestQueue queue ;

    LinearLayout when_log_out_layout , when_logged_in_layout ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_profile, container, false);
        sm = new SessionManager(getActivity());
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        when_log_out_layout = (LinearLayout) v.findViewById(R.id.when_log_out);
        when_logged_in_layout = (LinearLayout) v.findViewById(R.id.when_loged_in);

        FragmentStatus.GetOpenfragment = "FragmentProfile";
        if(sm.isLoggedIn()){

            when_logged_in_layout.setVisibility(View.VISIBLE);
            when_log_out_layout.setVisibility(View.GONE);
        }else {
            when_logged_in_layout.setVisibility(View.GONE);
            when_log_out_layout.setVisibility(View.VISIBLE);
        }




        v.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logoutUser();
            }
        });

        v.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AppOrioLoginScreen.class);
                in.putExtra("apporio_login_url", UrlsEbookAfrics.Login);
                in.putExtra("apporio_sign_url", UrlsEbookAfrics.SighUp);
                startActivity(in);
            }
        });



        v.findViewById(R.id.changepassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                dialogPassword = new MyDialog(getActivity()).getMyDialog(R.layout.dialog_password);

                TextView title;
                final EditText edtOld, edtNew, edtConfirm;
                final Button ChangePassword;
                title = (TextView) dialogPassword.findViewById(R.id.txtTitle);
                edtOld = (EditText) dialogPassword.findViewById(R.id.edtoldPassword);
                edtNew = (EditText) dialogPassword.findViewById(R.id.edtnewPassword);
                edtConfirm = (EditText) dialogPassword.findViewById(R.id.edtagainPassword);
                ChangePassword = (Button) dialogPassword.findViewById(R.id.btnPassword);

                ChangePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!edtOld.getText().toString().trim().equals("") && !edtNew.getText().toString().trim().equals("") && !edtConfirm.getText().toString().trim().equals("")) {
                            if (edtNew.getText().toString().trim().equals(edtConfirm.getText().toString().trim())) {
                                if (new CheckNetwork(getActivity()).isNetworkOnline()) {
                                    ChangePasswordExecution(edtOld.getText().toString().trim() , edtNew.getText().toString().trim() );
                                } else {

                                    Toast.makeText(getActivity() , "No Internet Connection"  , Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Password Not Matches", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Required Field Missing", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                title.setText("Change Password");


                dialogPassword.show();




            }
        });






        v.findViewById(R.id.viewpurchases).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , PreviousOrdersActivity.class));
            }
        });




        v.findViewById(R.id.giftcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity() , "Under Development " , Toast.LENGTH_SHORT).show();
            }
        });






        return v;
    }








    private void ChangePasswordExecution(String oldpassword , String newpassword) {
        String url = UrlsEbookAfrics.ChangePassword;
        url=url.replace(" ","%20");
        Logger.d("Executing Change password API   " + url);
        Map<String, String> jsonParams = new HashMap<String, String>();


        jsonParams.put("password", oldpassword);
        jsonParams.put("username", sm.getUserDetails().get(SessionManager.EMAIL));
        jsonParams.put("newpassword", newpassword);

        JsonObjectRequest postRequest = new JsonObjectRequest( Request.Method.POST, UrlsEbookAfrics.ChangePassword,

                new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialogPassword.dismiss();
                        Logger.d("response of change password API " + response);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        ResponseChecker rcheck = new ResponseChecker();
                        rcheck = gson.fromJson(""+response, ResponseChecker.class);

                        if(rcheck.getStatus().equals("success")){
                    Toast.makeText(getActivity() , "Password Changes Successfully" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity() , "Invalid password" , Toast.LENGTH_SHORT).show();
                }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Toast.makeText(getActivity() , "" , Toast.LENGTH_SHORT).show();
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
