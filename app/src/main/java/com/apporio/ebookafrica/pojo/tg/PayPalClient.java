
package com.apporio.ebookafrica.pojo.tg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayPalClient {

    @SerializedName("environment")
    @Expose
    private String environment;
    @SerializedName("paypal_sdk_version")
    @Expose
    private String paypalSdkVersion;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("product_name")
    @Expose
    private String productName;

    /**
     * 
     * @return
     *     The environment
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * 
     * @param environment
     *     The environment
     */
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    /**
     * 
     * @return
     *     The paypalSdkVersion
     */
    public String getPaypalSdkVersion() {
        return paypalSdkVersion;
    }

    /**
     * 
     * @param paypalSdkVersion
     *     The paypal_sdk_version
     */
    public void setPaypalSdkVersion(String paypalSdkVersion) {
        this.paypalSdkVersion = paypalSdkVersion;
    }

    /**
     * 
     * @return
     *     The platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 
     * @param platform
     *     The platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * 
     * @return
     *     The productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 
     * @param productName
     *     The product_name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

}
