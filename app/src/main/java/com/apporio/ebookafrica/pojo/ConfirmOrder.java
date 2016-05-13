
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmOrder {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("order_info")
    @Expose
    private OrderInfo orderInfo;

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
     *     The orderInfo
     */
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    /**
     * 
     * @param orderInfo
     *     The order_info
     */
    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

}
