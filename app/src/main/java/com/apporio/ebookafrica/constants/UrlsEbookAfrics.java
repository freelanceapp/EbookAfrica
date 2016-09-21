package com.apporio.ebookafrica.constants;

/**
 * Created by spinnosolutions on 4/29/16.
 */
public class UrlsEbookAfrics {


    public static String BaseUrl  = "http://www.e-bookafrica.com";
    public static String Login  = BaseUrl+"/api/api.php?func=login";
    public static String SighUp = BaseUrl+"/api/api.php?func=registration";

    public static String AllCategories  =  BaseUrl+"/api/api.php?func=getcategories&language_id=1&category_id=" ;
    public static String SpecificCategory =  BaseUrl+"/api/api.php?func=getcategories&language_id=1&category_id=" ; // add category id

    public static String BannerHome = BaseUrl+"/api/api.php?func=get_slideshow&language_id=1";

    public static String LatestProduct  = BaseUrl+"/api/api.php?func=getlatestproduct&language_id=1";
    public static String GetSpecificProduct  = BaseUrl+"/api/api.php?func=getproduct&language_id=1&product_id=";//53
    public static String relatedproducts  = BaseUrl+"/api/api.php?func=getproduct&language_id=1&product_id="; // 57
    public static String search = BaseUrl+"/api/api.php?func=getproducts&language_id=1&filter_name=";
    public static String specialcategory = BaseUrl+"/api/api.php?func=get_category_special&language_id=1";
    public static String ConfirmOrder   = BaseUrl+"/api/api.php?func=confirmorder";
    public static String PlaceOrder   = BaseUrl+"/api/api.php?func=place_order";
    public static String PurchasedBooks   = BaseUrl+"/api/api.php?func=getorders&customer_id=";
    public static String ChangePassword = BaseUrl+"/api/api.php?func=changepassword";

    public static String GiftCard = BaseUrl+"/api/api.php?func=giftvoucher";


}
