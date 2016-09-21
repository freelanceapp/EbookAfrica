package com.apporio.apporiologin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by spinnosolutions on 4/29/16.
 */
public class AppOrioLoginPrefrences {

    Context con ;
    SharedPreferences sharedpreferences;

    public AppOrioLoginPrefrences(Context con){
        this.con = con ;
        sharedpreferences = con.getSharedPreferences(Contants.MyPREFERENCES, Context.MODE_PRIVATE);
    }



    public int getLoginStatus (){
        return sharedpreferences.getInt("login_state", 0);
    }


}
