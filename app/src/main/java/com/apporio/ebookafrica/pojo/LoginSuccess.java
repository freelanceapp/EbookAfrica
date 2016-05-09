
package com.apporio.ebookafrica.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class LoginSuccess {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("customer")
    @Expose
    private Customer customer;

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * 
     * @param customer
     *     The customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
