
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("newsletter")
    @Expose
    private String newsletter;
    @SerializedName("wishlist")
    @Expose
    private Object wishlist;
    @SerializedName("cart")
    @Expose
    private Object cart;
    @SerializedName("total")
    @Expose
    private String total;

    /**
     * 
     * @return
     *     The customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 
     * @param customerId
     *     The customer_id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * 
     * @return
     *     The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * 
     * @param firstname
     *     The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * 
     * @return
     *     The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * 
     * @param lastname
     *     The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 
     * @param telephone
     *     The telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 
     * @return
     *     The fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * 
     * @param fax
     *     The fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 
     * @return
     *     The newsletter
     */
    public String getNewsletter() {
        return newsletter;
    }

    /**
     * 
     * @param newsletter
     *     The newsletter
     */
    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    /**
     * 
     * @return
     *     The wishlist
     */
    public Object getWishlist() {
        return wishlist;
    }

    /**
     * 
     * @param wishlist
     *     The wishlist
     */
    public void setWishlist(Object wishlist) {
        this.wishlist = wishlist;
    }

    /**
     * 
     * @return
     *     The cart
     */
    public Object getCart() {
        return cart;
    }

    /**
     * 
     * @param cart
     *     The cart
     */
    public void setCart(Object cart) {
        this.cart = cart;
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

}
