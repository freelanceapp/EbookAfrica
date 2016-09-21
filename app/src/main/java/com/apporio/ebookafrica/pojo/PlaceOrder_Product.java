
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrder_Product {

    @SerializedName("order_product_id")
    @Expose
    private String orderProductId;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("reward")
    @Expose
    private String reward;
    @SerializedName("options")
    @Expose
    private List<Object> options = new ArrayList<Object>();
    @SerializedName("download_link")
    @Expose
    private String downloadLink;



    @SerializedName("file_type")
    @Expose
    private String filetypedownloadLink;

    public String getFiletypedownloadLink() {
        return filetypedownloadLink;
    }

    public void setFiletypedownloadLink(String filetypedownloadLink) {
        this.filetypedownloadLink = filetypedownloadLink;
    }

    /**
     * 
     * @return
     *     The orderProductId
     */
    public String getOrderProductId() {
        return orderProductId;
    }

    /**
     * 
     * @param orderProductId
     *     The order_product_id
     */
    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    /**
     * 
     * @return
     *     The orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 
     * @param orderId
     *     The order_id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 
     * @return
     *     The productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 
     * @param productId
     *     The product_id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The model
     */
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *     The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 
     * @return
     *     The quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity
     *     The quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * @return
     *     The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The total
     */
    public String getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The tax
     */
    public String getTax() {
        return tax;
    }

    /**
     * 
     * @param tax
     *     The tax
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     * 
     * @return
     *     The reward
     */
    public String getReward() {
        return reward;
    }

    /**
     * 
     * @param reward
     *     The reward
     */
    public void setReward(String reward) {
        this.reward = reward;
    }

    /**
     * 
     * @return
     *     The options
     */
    public List<Object> getOptions() {
        return options;
    }

    /**
     * 
     * @param options
     *     The options
     */
    public void setOptions(List<Object> options) {
        this.options = options;
    }

    /**
     * 
     * @return
     *     The downloadLink
     */
    public String getDownloadLink() {
        return downloadLink;
    }

    /**
     * 
     * @param downloadLink
     *     The download_link
     */
    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

}
