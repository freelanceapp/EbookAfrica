package com.apporio.ebookafrica.constants;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by wscubetech on 2/7/15.
 */
public class CheckNetwork {
    Context context;

    public CheckNetwork(Context context) {
        this.context = context;
    }

    public boolean isNetworkOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (netInfo != null
                    && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
        return status;

    }
}
