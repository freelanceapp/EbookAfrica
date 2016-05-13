
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Search {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("products")
    @Expose
    private List<ProductSearch> productSearches = new ArrayList<ProductSearch>();

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
     *     The count
     */
    public String getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The products
     */
    public List<ProductSearch> getProductSearches() {
        return productSearches;
    }

    /**
     * 
     * @param productSearches
     *     The products
     */
    public void setProductSearches(List<ProductSearch> productSearches) {
        this.productSearches = productSearches;
    }

}
