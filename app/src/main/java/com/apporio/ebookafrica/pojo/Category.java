
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Category {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("top")
    @Expose
    private String top;
    @SerializedName("column")
    @Expose
    private String column;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("language_id")
    @Expose
    private String languageId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("child")
    @Expose
    private List<Child> child = new ArrayList<Child>();

    /**
     * 
     * @return
     *     The categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 
     * @param categoryId
     *     The category_id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
     *     The parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 
     * @param parentId
     *     The parent_id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * @return
     *     The top
     */
    public String getTop() {
        return top;
    }

    /**
     * 
     * @param top
     *     The top
     */
    public void setTop(String top) {
        this.top = top;
    }

    /**
     * 
     * @return
     *     The column
     */
    public String getColumn() {
        return column;
    }

    /**
     * 
     * @param column
     *     The column
     */
    public void setColumn(String column) {
        this.column = column;
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
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The metaTitle
     */
    public String getMetaTitle() {
        return metaTitle;
    }

    /**
     * 
     * @param metaTitle
     *     The meta_title
     */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
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
     *     The child
     */
    public List<Child> getChild() {
        return child;
    }

    /**
     * 
     * @param child
     *     The child
     */
    public void setChild(List<Child> child) {
        this.child = child;
    }

}
