
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slider {

    @SerializedName("banner_image_id")
    @Expose
    private String bannerImageId;
    @SerializedName("banner_id")
    @Expose
    private String bannerId;
    @SerializedName("link")
    @Expose
    private String link;
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
    private Integer categoryId;

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
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
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
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 
     * @param categoryId
     *     The category_id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}
