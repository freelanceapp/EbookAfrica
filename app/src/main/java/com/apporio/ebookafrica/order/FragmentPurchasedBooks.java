package com.apporio.ebookafrica.order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import com.apporio.ebookafrica.pojo.purchasedProductPojo.PurchasedBooks;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by spinnosolutions on 5/18/16.
 */
public class FragmentPurchasedBooks extends Fragment {

    ListView list;

    SessionManager sm  ;
    private static RequestQueue queue ;
    private static StringRequest sr;

    @SuppressLint("ValidFragment")
    public FragmentPurchasedBooks(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sm = new SessionManager(getActivity());
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        View rootView = inflater.inflate(R.layout.fragment_purchased_books, container, false);
        list = (ListView) rootView.findViewById(R.id.list);


        PreviousOrderExecution();


        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }








    public void PreviousOrderExecution(){
        String url = UrlsEbookAfrics.PurchasedBooks+sm.getUserDetails().get(SessionManager.CUSTOMER_ID);
        url=url.replace(" ","%20");
        Logger.d("Executing Purchased Books Product API   " + url);


        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResponseChecker rcheck = new ResponseChecker();
                rcheck = gson.fromJson(response, ResponseChecker.class);

                if(rcheck.getStatus().equals("success")){
                    PurchasedBooks pb = new PurchasedBooks();
                    pb = gson.fromJson(response, PurchasedBooks.class);

                    Toast.makeText(getActivity() , "Total no of purchases "+pb.getPurchasedBooksOrders().get(0).getPurchasedBooksOrderTotals(), Toast.LENGTH_SHORT).show();

                    list.setAdapter(new AdapterPurchasedBooks(getActivity() , pb.getPurchasedBooksOrders()));

                } else {
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
        Toast.makeText(getActivity() , "Executing purchased books",Toast.LENGTH_SHORT).show();
    }

}
