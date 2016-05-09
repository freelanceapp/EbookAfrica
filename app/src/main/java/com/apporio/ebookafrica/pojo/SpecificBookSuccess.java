
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecificBookSuccess {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("product")
    @Expose
    private SpecificBookSuccessProduct specificBookSuccessProduct;

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
    public SpecificBookSuccessProduct getSpecificBookSuccessProduct() {
        return specificBookSuccessProduct;
    }

    /**
     * 
     * @param specificBookSuccessProduct
     *     The product
     */
    public void setSpecificBookSuccessProduct(SpecificBookSuccessProduct specificBookSuccessProduct) {
        this.specificBookSuccessProduct = specificBookSuccessProduct;
    }

}
