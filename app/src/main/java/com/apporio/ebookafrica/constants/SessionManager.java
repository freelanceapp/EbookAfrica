package com.apporio.ebookafrica.constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

import com.apporio.ebookafrica.logger.Logger;

import java.io.File;
import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static String CUSTOMER_ID  = "customer_id";
    public static String FIRST_NAME  = "firstname";
    public static String LAST_NAME  = "lastname";
    public static String EMAIL  = "email";
    public static String TELEPHONE  = "telephone";
    public static String FAX  = "fax";
    public static String NEWS_LETTER  = "newsletter";
    public static String WISHLIST  = "wishlist";
    public static String CART  = "cart";
    public static String TOTAL  = "total";










    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String customer_id, String firstname , String lastname , String email , String telephone  , String fax , String newsletter , String wishlist  , String cart  ,String total ){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(CUSTOMER_ID, customer_id);
        editor.putString(FIRST_NAME, firstname);
        editor.putString(LAST_NAME, lastname);
        editor.putString(EMAIL, email);
        editor.putString(TELEPHONE, telephone);
        editor.putString(FAX, fax);
        editor.putString(NEWS_LETTER, newsletter);
        editor.putString(WISHLIST, wishlist);
        editor.putString(CART, cart);
        editor.putString(TOTAL, total);
        editor.commit();

    }




    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(CUSTOMER_ID, pref.getString(CUSTOMER_ID, null));
        user.put(FIRST_NAME, pref.getString(FIRST_NAME, null));
        user.put(LAST_NAME, pref.getString(LAST_NAME, null));
        user.put(EMAIL, pref.getString(EMAIL, null));
        user.put(TELEPHONE, pref.getString(TELEPHONE, null));
        user.put(FAX, pref.getString(FAX, null));
        user.put(NEWS_LETTER, pref.getString(NEWS_LETTER, null));
        user.put(WISHLIST, pref.getString(WISHLIST, null));
        user.put(CART, pref.getString(CART, null));
        user.put(TOTAL, pref.getString(TOTAL, null));

        // return user
        return user;
    }


    public void logoutUser(){
        editor.clear();
        editor.commit();
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }









}