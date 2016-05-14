package com.apporio.ebookafrica.fragmentyourbooks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.SessionManager;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by spinnosolutions on 4/9/16.
 */
public class FragmentPurchased extends Fragment {


    StickyListHeadersListView list ;
    String [] prices = {"34" , "55" ,"43" ,"12" , "13" , "34"};
    String [] dates = {"01-april-2016" , "11-april-2016" ,"22-april-2016" ,"13-april-2016" , "14-april-2016" , "14-april-2016"};
    private static RequestQueue queue ;
    private static StringRequest sr;
    SessionManager sm  ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_all, container, false);
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        sm = new SessionManager(getActivity());
        list = (StickyListHeadersListView) v.findViewById(R.id.list);
      //  list.setAdapter(new AdapterQuickReturn(getActivity(), prices, dates));
        purchasedBookExecutionAPI();
        return v;
    }





    public static FragmentPurchased newInstance(String text) {

        FragmentPurchased f = new FragmentPurchased();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }








    public void purchasedBookExecutionAPI(){

        String url = UrlsEbookAfrics.PurchasedBooks +sm.getUserDetails().get(SessionManager.CUSTOMER_ID);
        url=url.replace(" ","%20");
        Logger.d("Executing Purchased Books API   " + url);


        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResponseChecker rcheck = new ResponseChecker();
                rcheck = gson.fromJson(response, ResponseChecker.class);
                if(rcheck.getStatus().equals("success")){
                    Toast.makeText(getActivity() ,"success" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity() ,"You don't have purchased any book yet " , Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.d(""+error);
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);

    }








}
