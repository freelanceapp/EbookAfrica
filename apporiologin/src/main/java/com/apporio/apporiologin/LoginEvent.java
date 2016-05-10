package com.apporio.apporiologin;

/**
 * Created by spinnosolutions on 4/29/16.
 */
public class LoginEvent {


    private String  value;
    private int determiner   ;
    public LoginEvent(String  value  , int determiner ) {
        this.value = value;
        this.determiner  = determiner  ;
    }

    public String  LoginEvent() {
        return value ;
    }

    public int  LoginDeterminer() {
        return determiner ;
    }
}
