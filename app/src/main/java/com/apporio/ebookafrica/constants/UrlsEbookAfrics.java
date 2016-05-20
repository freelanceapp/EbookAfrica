package com.apporio.ebookafrica.constants;

/**
 * Created by spinnosolutions on 4/29/16.
 */
public class UrlsEbookAfrics {

    public static String Login  = "http://modha.me.uk/api/api.php?func=login";
    public static String SighUp = "http://modha.me.uk/api/api.php?func=registration";

    public static String AllCategories  =  "http://modha.me.uk/api/api.php?func=getcategories&language_id=1&category_id=" ;
    public static String SpecificCategory =  "http://modha.me.uk//api/api.php?func=getcategories&language_id=1&category_id=" ; // add category id

    public static String BannerHome = "http://modha.me.uk/api/api.php?func=get_slideshow&language_id=1";

    public static String LatestProduct  = "http://modha.me.uk/api/api.php?func=getlatestproduct&language_id=1";
    public static String GetSpecificProduct  = "http://modha.me.uk/api/api.php?func=getproduct&language_id=1&product_id=";//53
    public static String relatedproducts  = "http://modha.me.uk/api/api.php?func=getproduct&language_id=1&product_id="; // 57
    public static String search = "http://modha.me.uk/api/api.php?func=getproducts&language_id=1&filter_name=";
    public static String specialcategory = "http://modha.me.uk/api/api.php?func=get_category_special&language_id=1";
    public static String ConfirmOrder   = "http://modha.me.uk/api/api.php?func=confirmorder";
    public static String PlaceOrder   = "http://modha.me.uk/api/api.php?func=place_order";
    public static String PurchasedBooks   = "http://modha.me.uk/api/api.php?func=getorders&customer_id=";
    public static String ChangePassword = "http://modha.me.uk/api/api.php?func=changepassword";


}
