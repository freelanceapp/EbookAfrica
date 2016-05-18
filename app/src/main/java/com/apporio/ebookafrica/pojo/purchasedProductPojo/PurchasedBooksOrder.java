
package com.apporio.ebookafrica.pojo.purchasedProductPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PurchasedBooksOrder {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("invoice_no")
    @Expose
    private String invoiceNo;
    @SerializedName("invoice_prefix")
    @Expose
    private String invoicePrefix;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("store_name")
    @Expose
    private String storeName;
    @SerializedName("store_url")
    @Expose
    private String storeUrl;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("payment_firstname")
    @Expose
    private String paymentFirstname;
    @SerializedName("payment_lastname")
    @Expose
    private String paymentLastname;
    @SerializedName("payment_company")
    @Expose
    private String paymentCompany;
    @SerializedName("payment_address_1")
    @Expose
    private String paymentAddress1;
    @SerializedName("payment_address_2")
    @Expose
    private String paymentAddress2;
    @SerializedName("payment_postcode")
    @Expose
    private String paymentPostcode;
    @SerializedName("payment_city")
    @Expose
    private String paymentCity;
    @SerializedName("payment_zone_id")
    @Expose
    private String paymentZoneId;
    @SerializedName("payment_zone")
    @Expose
    private String paymentZone;
    @SerializedName("payment_zone_code")
    @Expose
    private String paymentZoneCode;
    @SerializedName("payment_country_id")
    @Expose
    private String paymentCountryId;
    @SerializedName("payment_country")
    @Expose
    private String paymentCountry;
    @SerializedName("payment_iso_code_2")
    @Expose
    private String paymentIsoCode2;
    @SerializedName("payment_iso_code_3")
    @Expose
    private String paymentIsoCode3;
    @SerializedName("payment_address_format")
    @Expose
    private String paymentAddressFormat;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("shipping_firstname")
    @Expose
    private String shippingFirstname;
    @SerializedName("shipping_lastname")
    @Expose
    private String shippingLastname;
    @SerializedName("shipping_company")
    @Expose
    private String shippingCompany;
    @SerializedName("shipping_address_1")
    @Expose
    private String shippingAddress1;
    @SerializedName("shipping_address_2")
    @Expose
    private String shippingAddress2;
    @SerializedName("shipping_postcode")
    @Expose
    private String shippingPostcode;
    @SerializedName("shipping_city")
    @Expose
    private String shippingCity;
    @SerializedName("shipping_zone_id")
    @Expose
    private String shippingZoneId;
    @SerializedName("shipping_zone")
    @Expose
    private String shippingZone;
    @SerializedName("shipping_zone_code")
    @Expose
    private String shippingZoneCode;
    @SerializedName("shipping_country_id")
    @Expose
    private String shippingCountryId;
    @SerializedName("shipping_country")
    @Expose
    private String shippingCountry;
    @SerializedName("shipping_iso_code_2")
    @Expose
    private String shippingIsoCode2;
    @SerializedName("shipping_iso_code_3")
    @Expose
    private String shippingIsoCode3;
    @SerializedName("shipping_address_format")
    @Expose
    private String shippingAddressFormat;
    @SerializedName("shipping_method")
    @Expose
    private String shippingMethod;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("order_status_id")
    @Expose
    private String orderStatusId;
    @SerializedName("language_id")
    @Expose
    private String languageId;
    @SerializedName("currency_id")
    @Expose
    private String currencyId;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("currency_value")
    @Expose
    private String currencyValue;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("products")
    @Expose
    private PurchasedBooksProducts purchasedBooksProducts;
    @SerializedName("order_totals")
    @Expose
    private List<PurchasedBooksOrderTotal> purchasedBooksOrderTotals = new ArrayList<PurchasedBooksOrderTotal>();

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
     *     The invoiceNo
     */
    public String getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * 
     * @param invoiceNo
     *     The invoice_no
     */
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    /**
     * 
     * @return
     *     The invoicePrefix
     */
    public String getInvoicePrefix() {
        return invoicePrefix;
    }

    /**
     * 
     * @param invoicePrefix
     *     The invoice_prefix
     */
    public void setInvoicePrefix(String invoicePrefix) {
        this.invoicePrefix = invoicePrefix;
    }

    /**
     * 
     * @return
     *     The storeId
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * 
     * @param storeId
     *     The store_id
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /**
     * 
     * @return
     *     The storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 
     * @param storeName
     *     The store_name
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * 
     * @return
     *     The storeUrl
     */
    public String getStoreUrl() {
        return storeUrl;
    }

    /**
     * 
     * @param storeUrl
     *     The store_url
     */
    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

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
     *     The paymentFirstname
     */
    public String getPaymentFirstname() {
        return paymentFirstname;
    }

    /**
     * 
     * @param paymentFirstname
     *     The payment_firstname
     */
    public void setPaymentFirstname(String paymentFirstname) {
        this.paymentFirstname = paymentFirstname;
    }

    /**
     * 
     * @return
     *     The paymentLastname
     */
    public String getPaymentLastname() {
        return paymentLastname;
    }

    /**
     * 
     * @param paymentLastname
     *     The payment_lastname
     */
    public void setPaymentLastname(String paymentLastname) {
        this.paymentLastname = paymentLastname;
    }

    /**
     * 
     * @return
     *     The paymentCompany
     */
    public String getPaymentCompany() {
        return paymentCompany;
    }

    /**
     * 
     * @param paymentCompany
     *     The payment_company
     */
    public void setPaymentCompany(String paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    /**
     * 
     * @return
     *     The paymentAddress1
     */
    public String getPaymentAddress1() {
        return paymentAddress1;
    }

    /**
     * 
     * @param paymentAddress1
     *     The payment_address_1
     */
    public void setPaymentAddress1(String paymentAddress1) {
        this.paymentAddress1 = paymentAddress1;
    }

    /**
     * 
     * @return
     *     The paymentAddress2
     */
    public String getPaymentAddress2() {
        return paymentAddress2;
    }

    /**
     * 
     * @param paymentAddress2
     *     The payment_address_2
     */
    public void setPaymentAddress2(String paymentAddress2) {
        this.paymentAddress2 = paymentAddress2;
    }

    /**
     * 
     * @return
     *     The paymentPostcode
     */
    public String getPaymentPostcode() {
        return paymentPostcode;
    }

    /**
     * 
     * @param paymentPostcode
     *     The payment_postcode
     */
    public void setPaymentPostcode(String paymentPostcode) {
        this.paymentPostcode = paymentPostcode;
    }

    /**
     * 
     * @return
     *     The paymentCity
     */
    public String getPaymentCity() {
        return paymentCity;
    }

    /**
     * 
     * @param paymentCity
     *     The payment_city
     */
    public void setPaymentCity(String paymentCity) {
        this.paymentCity = paymentCity;
    }

    /**
     * 
     * @return
     *     The paymentZoneId
     */
    public String getPaymentZoneId() {
        return paymentZoneId;
    }

    /**
     * 
     * @param paymentZoneId
     *     The payment_zone_id
     */
    public void setPaymentZoneId(String paymentZoneId) {
        this.paymentZoneId = paymentZoneId;
    }

    /**
     * 
     * @return
     *     The paymentZone
     */
    public String getPaymentZone() {
        return paymentZone;
    }

    /**
     * 
     * @param paymentZone
     *     The payment_zone
     */
    public void setPaymentZone(String paymentZone) {
        this.paymentZone = paymentZone;
    }

    /**
     * 
     * @return
     *     The paymentZoneCode
     */
    public String getPaymentZoneCode() {
        return paymentZoneCode;
    }

    /**
     * 
     * @param paymentZoneCode
     *     The payment_zone_code
     */
    public void setPaymentZoneCode(String paymentZoneCode) {
        this.paymentZoneCode = paymentZoneCode;
    }

    /**
     * 
     * @return
     *     The paymentCountryId
     */
    public String getPaymentCountryId() {
        return paymentCountryId;
    }

    /**
     * 
     * @param paymentCountryId
     *     The payment_country_id
     */
    public void setPaymentCountryId(String paymentCountryId) {
        this.paymentCountryId = paymentCountryId;
    }

    /**
     * 
     * @return
     *     The paymentCountry
     */
    public String getPaymentCountry() {
        return paymentCountry;
    }

    /**
     * 
     * @param paymentCountry
     *     The payment_country
     */
    public void setPaymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
    }

    /**
     * 
     * @return
     *     The paymentIsoCode2
     */
    public String getPaymentIsoCode2() {
        return paymentIsoCode2;
    }

    /**
     * 
     * @param paymentIsoCode2
     *     The payment_iso_code_2
     */
    public void setPaymentIsoCode2(String paymentIsoCode2) {
        this.paymentIsoCode2 = paymentIsoCode2;
    }

    /**
     * 
     * @return
     *     The paymentIsoCode3
     */
    public String getPaymentIsoCode3() {
        return paymentIsoCode3;
    }

    /**
     * 
     * @param paymentIsoCode3
     *     The payment_iso_code_3
     */
    public void setPaymentIsoCode3(String paymentIsoCode3) {
        this.paymentIsoCode3 = paymentIsoCode3;
    }

    /**
     * 
     * @return
     *     The paymentAddressFormat
     */
    public String getPaymentAddressFormat() {
        return paymentAddressFormat;
    }

    /**
     * 
     * @param paymentAddressFormat
     *     The payment_address_format
     */
    public void setPaymentAddressFormat(String paymentAddressFormat) {
        this.paymentAddressFormat = paymentAddressFormat;
    }

    /**
     * 
     * @return
     *     The paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 
     * @param paymentMethod
     *     The payment_method
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 
     * @return
     *     The shippingFirstname
     */
    public String getShippingFirstname() {
        return shippingFirstname;
    }

    /**
     * 
     * @param shippingFirstname
     *     The shipping_firstname
     */
    public void setShippingFirstname(String shippingFirstname) {
        this.shippingFirstname = shippingFirstname;
    }

    /**
     * 
     * @return
     *     The shippingLastname
     */
    public String getShippingLastname() {
        return shippingLastname;
    }

    /**
     * 
     * @param shippingLastname
     *     The shipping_lastname
     */
    public void setShippingLastname(String shippingLastname) {
        this.shippingLastname = shippingLastname;
    }

    /**
     * 
     * @return
     *     The shippingCompany
     */
    public String getShippingCompany() {
        return shippingCompany;
    }

    /**
     * 
     * @param shippingCompany
     *     The shipping_company
     */
    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    /**
     * 
     * @return
     *     The shippingAddress1
     */
    public String getShippingAddress1() {
        return shippingAddress1;
    }

    /**
     * 
     * @param shippingAddress1
     *     The shipping_address_1
     */
    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    /**
     * 
     * @return
     *     The shippingAddress2
     */
    public String getShippingAddress2() {
        return shippingAddress2;
    }

    /**
     * 
     * @param shippingAddress2
     *     The shipping_address_2
     */
    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    /**
     * 
     * @return
     *     The shippingPostcode
     */
    public String getShippingPostcode() {
        return shippingPostcode;
    }

    /**
     * 
     * @param shippingPostcode
     *     The shipping_postcode
     */
    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    /**
     * 
     * @return
     *     The shippingCity
     */
    public String getShippingCity() {
        return shippingCity;
    }

    /**
     * 
     * @param shippingCity
     *     The shipping_city
     */
    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    /**
     * 
     * @return
     *     The shippingZoneId
     */
    public String getShippingZoneId() {
        return shippingZoneId;
    }

    /**
     * 
     * @param shippingZoneId
     *     The shipping_zone_id
     */
    public void setShippingZoneId(String shippingZoneId) {
        this.shippingZoneId = shippingZoneId;
    }

    /**
     * 
     * @return
     *     The shippingZone
     */
    public String getShippingZone() {
        return shippingZone;
    }

    /**
     * 
     * @param shippingZone
     *     The shipping_zone
     */
    public void setShippingZone(String shippingZone) {
        this.shippingZone = shippingZone;
    }

    /**
     * 
     * @return
     *     The shippingZoneCode
     */
    public String getShippingZoneCode() {
        return shippingZoneCode;
    }

    /**
     * 
     * @param shippingZoneCode
     *     The shipping_zone_code
     */
    public void setShippingZoneCode(String shippingZoneCode) {
        this.shippingZoneCode = shippingZoneCode;
    }

    /**
     * 
     * @return
     *     The shippingCountryId
     */
    public String getShippingCountryId() {
        return shippingCountryId;
    }

    /**
     * 
     * @param shippingCountryId
     *     The shipping_country_id
     */
    public void setShippingCountryId(String shippingCountryId) {
        this.shippingCountryId = shippingCountryId;
    }

    /**
     * 
     * @return
     *     The shippingCountry
     */
    public String getShippingCountry() {
        return shippingCountry;
    }

    /**
     * 
     * @param shippingCountry
     *     The shipping_country
     */
    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    /**
     * 
     * @return
     *     The shippingIsoCode2
     */
    public String getShippingIsoCode2() {
        return shippingIsoCode2;
    }

    /**
     * 
     * @param shippingIsoCode2
     *     The shipping_iso_code_2
     */
    public void setShippingIsoCode2(String shippingIsoCode2) {
        this.shippingIsoCode2 = shippingIsoCode2;
    }

    /**
     * 
     * @return
     *     The shippingIsoCode3
     */
    public String getShippingIsoCode3() {
        return shippingIsoCode3;
    }

    /**
     * 
     * @param shippingIsoCode3
     *     The shipping_iso_code_3
     */
    public void setShippingIsoCode3(String shippingIsoCode3) {
        this.shippingIsoCode3 = shippingIsoCode3;
    }

    /**
     * 
     * @return
     *     The shippingAddressFormat
     */
    public String getShippingAddressFormat() {
        return shippingAddressFormat;
    }

    /**
     * 
     * @param shippingAddressFormat
     *     The shipping_address_format
     */
    public void setShippingAddressFormat(String shippingAddressFormat) {
        this.shippingAddressFormat = shippingAddressFormat;
    }

    /**
     * 
     * @return
     *     The shippingMethod
     */
    public String getShippingMethod() {
        return shippingMethod;
    }

    /**
     * 
     * @param shippingMethod
     *     The shipping_method
     */
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    /**
     * 
     * @return
     *     The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * 
     * @param comment
     *     The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
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
     *     The orderStatusId
     */
    public String getOrderStatusId() {
        return orderStatusId;
    }

    /**
     * 
     * @param orderStatusId
     *     The order_status_id
     */
    public void setOrderStatusId(String orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    /**
     * 
     * @return
     *     The languageId
     */
    public String getLanguageId() {
        return languageId;
    }

    /**
     * 
     * @param languageId
     *     The language_id
     */
    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    /**
     * 
     * @return
     *     The currencyId
     */
    public String getCurrencyId() {
        return currencyId;
    }

    /**
     * 
     * @param currencyId
     *     The currency_id
     */
    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * 
     * @return
     *     The currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 
     * @param currencyCode
     *     The currency_code
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * 
     * @return
     *     The currencyValue
     */
    public String getCurrencyValue() {
        return currencyValue;
    }

    /**
     * 
     * @param currencyValue
     *     The currency_value
     */
    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
    }

    /**
     * 
     * @return
     *     The dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     * 
     * @param dateModified
     *     The date_modified
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * 
     * @return
     *     The dateAdded
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     * 
     * @param dateAdded
     *     The date_added
     */
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * 
     * @return
     *     The ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 
     * @param ip
     *     The ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 
     * @return
     *     The products
     */
    public PurchasedBooksProducts getPurchasedBooksProducts() {
        return purchasedBooksProducts;
    }

    /**
     * 
     * @param purchasedBooksProducts
     *     The products
     */
    public void setPurchasedBooksProducts(PurchasedBooksProducts purchasedBooksProducts) {
        this.purchasedBooksProducts = purchasedBooksProducts;
    }

    /**
     * 
     * @return
     *     The orderTotals
     */
    public List<PurchasedBooksOrderTotal> getPurchasedBooksOrderTotals() {
        return purchasedBooksOrderTotals;
    }

    /**
     * 
     * @param purchasedBooksOrderTotals
     *     The order_totals
     */
    public void setPurchasedBooksOrderTotals(List<PurchasedBooksOrderTotal> purchasedBooksOrderTotals) {
        this.purchasedBooksOrderTotals = purchasedBooksOrderTotals;
    }

}
