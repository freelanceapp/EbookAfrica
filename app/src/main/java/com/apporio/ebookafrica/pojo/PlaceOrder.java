
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrder {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("order_detail")
    @Expose
    private PlaceOrder_OrderDetail placeOrderOrderDetail;
    @SerializedName("message")
    @Expose
    private String message;


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public PlaceOrder_OrderDetail getPlaceOrderOrderDetail() {
        return placeOrderOrderDetail;
    }


    public void setPlaceOrderOrderDetail(PlaceOrder_OrderDetail placeOrderOrderDetail) {
        this.placeOrderOrderDetail = placeOrderOrderDetail;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

}
