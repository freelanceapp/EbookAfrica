
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BannerSliderPojo {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("slider")
    @Expose
    private List<Slider> slider = new ArrayList<Slider>();

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
    public List<Slider> getSlider() {
        return slider;
    }

    /**
     * 
     * @param slider
     *     The slider
     */
    public void setSlider(List<Slider> slider) {
        this.slider = slider;
    }

}
