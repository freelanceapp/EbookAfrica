
package com.apporio.ebookafrica.pojo.purchasedProductPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PurchasedBooks {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("orders")
    @Expose
    private List<PurchasedBooksOrder> purchasedBooksOrders = new ArrayList<PurchasedBooksOrder>();

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
     *     The orders
     */
    public List<PurchasedBooksOrder> getPurchasedBooksOrders() {
        return purchasedBooksOrders;
    }

    /**
     * 
     * @param purchasedBooksOrders
     *     The orders
     */
    public void setPurchasedBooksOrders(List<PurchasedBooksOrder> purchasedBooksOrders) {
        this.purchasedBooksOrders = purchasedBooksOrders;
    }

}
