
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpecialCategory {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("slider")
    @Expose
    private List<SpecialCategorySlider> specialCategorySlider = new ArrayList<SpecialCategorySlider>();

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
     *     The slider
     */
    public List<SpecialCategorySlider> getSpecialCategorySlider() {
        return specialCategorySlider;
    }

    /**
     * 
     * @param specialCategorySlider
     *     The slider
     */
    public void setSpecialCategorySlider(List<SpecialCategorySlider> specialCategorySlider) {
        this.specialCategorySlider = specialCategorySlider;
    }

}
