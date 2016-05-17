
package com.apporio.ebookafrica.pojo.tg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayPalPojo {

    @SerializedName("client")
    @Expose
    private PayPalClient payPalClient;
    @SerializedName("response")
    @Expose
    private PayPalResponse payPalResponse;
    @SerializedName("response_type")
    @Expose
    private String responseType;

    /**
     * 
     * @return
     *     The client
     */
    public PayPalClient getPayPalClient() {
        return payPalClient;
    }

    /**
     * 
     * @param payPalClient
     *     The client
     */
    public void setPayPalClient(PayPalClient payPalClient) {
        this.payPalClient = payPalClient;
    }

    /**
     * 
     * @return
     *     The response
     */
    public PayPalResponse getPayPalResponse() {
        return payPalResponse;
    }

    /**
     * 
     * @param payPalResponse
     *     The response
     */
    public void setPayPalResponse(PayPalResponse payPalResponse) {
        this.payPalResponse = payPalResponse;
    }

    /**
     * 
     * @return
     *     The responseType
     */
    public String getResponseType() {
        return responseType;
    }

    /**
     * 
     * @param responseType
     *     The response_type
     */
    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

}
