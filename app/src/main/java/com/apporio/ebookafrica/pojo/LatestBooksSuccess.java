
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestBooksSuccess {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("product")
    @Expose
    private LatestBooksSuccessProduct latestBooksSuccessProduct;

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
     *     The product
     */
    public LatestBooksSuccessProduct getLatestBooksSuccessProduct() {
        return latestBooksSuccessProduct;
    }

    /**
     * 
     * @param latestBooksSuccessProduct
     *     The product
     */
    public void setLatestBooksSuccessProduct(LatestBooksSuccessProduct latestBooksSuccessProduct) {
        this.latestBooksSuccessProduct = latestBooksSuccessProduct;
    }

}
