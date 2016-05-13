
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderInfo {

    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("products")
    @Expose
    private List<ConfirmOrderProduct> confirmOrderProducts = new ArrayList<ConfirmOrderProduct>();
    @SerializedName("totals")
    @Expose
    private List<Total> totals = new ArrayList<Total>();

    /**
     * 
     * @return
     *     The orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 
     * @param orderId
     *     The order_id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 
     * @return
     *     The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The products
     */
    public List<ConfirmOrderProduct> getConfirmOrderProducts() {
        return confirmOrderProducts;
    }

    /**
     * 
     * @param confirmOrderProducts
     *     The products
     */
    public void setConfirmOrderProducts(List<ConfirmOrderProduct> confirmOrderProducts) {
        this.confirmOrderProducts = confirmOrderProducts;
    }

    /**
     * 
     * @return
     *     The totals
     */
    public List<Total> getTotals() {
        return totals;
    }

    /**
     * 
     * @param totals
     *     The totals
     */
    public void setTotals(List<Total> totals) {
        this.totals = totals;
    }

}
