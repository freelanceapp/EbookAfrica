package com.apporio.ebookafrica.constants;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by spinnosolutions on 5/18/16.
 */
public class PreviousLoginedStateSessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME_previous = "PreviousloginId";
    public static final String PREVIOUSLOGINEDID = "";







    public PreviousLoginedStateSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME_previous, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void savePreviousLoginID(String customer_id ){
        editor.putString(PREVIOUSLOGINEDID, customer_id);
        editor.commit();
    }




    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(PREVIOUSLOGINEDID, pref.getString(PREVIOUSLOGINEDID, null));
        return user;
    }




}
