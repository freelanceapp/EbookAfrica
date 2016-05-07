package com.apporio.apporiologin;

/**
 * Created by spinnosolutions on 4/29/16.
 */
public class LoginEvent {


    private String  value;
    public LoginEvent(String  value) {
        this.value = value;
    }

    public String  LoginEvent() {
        return value ;
    }
}
