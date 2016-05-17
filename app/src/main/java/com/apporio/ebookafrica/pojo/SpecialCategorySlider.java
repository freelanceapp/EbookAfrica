
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpecialCategorySlider {

    @SerializedName("banner_image_id")
    @Expose
    private String bannerImageId;
    @SerializedName("banner_id")
    @Expose
    private String bannerId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @SerializedName("language_id")
    @Expose
    private String languageId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category_id")
    @Expose
    private List<String> categoryId = new ArrayList<String>();

    /**
     * 
     * @return
     *     The bannerImageId
     */
    public String getBannerImageId() {
        return bannerImageId;
    }

    /**
     * 
     * @param bannerImageId
     *     The banner_image_id
     */
    public void setBannerImageId(String bannerImageId) {
        this.bannerImageId = bannerImageId;
    }

    /**
     * 
     * @return
     *     The bannerId
     */
    public String getBannerId() {
        return bannerId;
    }

    /**
     * 
     * @param bannerId
     *     The banner_id
     */
    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The sortOrder
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * 
     * @param sortOrder
     *     The sort_order
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
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
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The categoryId
     */
    public List<String> getCategoryId() {
        return categoryId;
    }

    /**
     * 
     * @param categoryId
     *     The category_id
     */
    public void setCategoryId(List<String> categoryId) {
        this.categoryId = categoryId;
    }

}
