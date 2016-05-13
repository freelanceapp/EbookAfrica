
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedPRoducts {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("product")
    @Expose
    private Productcore productcore;

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
    public Productcore getProductcore() {
        return productcore;
    }

    /**
     * 
     * @param productcore
     *     The product
     */
    public void setProductcore(Productcore productcore) {
        this.productcore = productcore;
    }

}
