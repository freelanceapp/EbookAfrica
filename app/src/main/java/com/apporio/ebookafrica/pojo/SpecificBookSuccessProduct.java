
package com.apporio.ebookafrica.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpecificBookSuccessProduct {


    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("stock_status_id")
    @Expose
    private String stockStatusId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("sample_books")
    @Expose
    private String sampleBooks;
    @SerializedName("manufacturer_id")
    @Expose
    private String manufacturerId;
    @SerializedName("author_id")
    @Expose
    private String authorId;
    @SerializedName("shipping")
    @Expose
    private String shipping;
    @SerializedName("pages")
    @Expose
    private String pages;
    @SerializedName("hours")
    @Expose
    private String hours;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("points")
    @Expose
    private String points;
    @SerializedName("tax_class_id")
    @Expose
    private String taxClassId;
    @SerializedName("date_available")
    @Expose
    private String dateAvailable;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("weight_class_id")
    @Expose
    private String weightClassId;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("length_class_id")
    @Expose
    private String lengthClassId;
    @SerializedName("minimum")
    @Expose
    private String minimum;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("viewed")
    @Expose
    private String viewed;
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
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("meta_title")
    @Expose
    private String metaTitle;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("special")
    @Expose
    private String special;
    @SerializedName("reward")
    @Expose
    private Object reward;
    @SerializedName("stock_status")
    @Expose
    private Object stockStatus;
    @SerializedName("weight_class")
    @Expose
    private Object weightClass;
    @SerializedName("length_class")
    @Expose
    private Object lengthClass;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("reviews")
    @Expose
    private String reviews;
    @SerializedName("options")
    @Expose
    private List<Object> options = new ArrayList<Object>();
    @SerializedName("images")
    @Expose
    private List<Object> images = new ArrayList<Object>();
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("related_product")
    @Expose
    private List<RelatedProduct> relatedProduct = new ArrayList<RelatedProduct>();

    /**
     *
     * @return
     * The productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     *
     * @param productId
     * The product_id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     *
     * @return
     * The model
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model
     * The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return
     * The sku
     */
    public String getSku() {
        return sku;
    }

    /**
     *
     * @param sku
     * The sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     *
     * @return
     * The isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     *
     * @param isbn
     * The isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     *
     * @return
     * The quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     * The quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     * The stockStatusId
     */
    public String getStockStatusId() {
        return stockStatusId;
    }

    /**
     *
     * @param stockStatusId
     * The stock_status_id
     */
    public void setStockStatusId(String stockStatusId) {
        this.stockStatusId = stockStatusId;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The sampleBooks
     */
    public String getSampleBooks() {
        return sampleBooks;
    }

    /**
     *
     * @param sampleBooks
     * The sample_books
     */
    public void setSampleBooks(String sampleBooks) {
        this.sampleBooks = sampleBooks;
    }

    /**
     *
     * @return
     * The manufacturerId
     */
    public String getManufacturerId() {
        return manufacturerId;
    }

    /**
     *
     * @param manufacturerId
     * The manufacturer_id
     */
    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     *
     * @return
     * The authorId
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     *
     * @param authorId
     * The author_id
     */
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    /**
     *
     * @return
     * The shipping
     */
    public String getShipping() {
        return shipping;
    }

    /**
     *
     * @param shipping
     * The shipping
     */
    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    /**
     *
     * @return
     * The pages
     */
    public String getPages() {
        return pages;
    }

    /**
     *
     * @param pages
     * The pages
     */
    public void setPages(String pages) {
        this.pages = pages;
    }

    /**
     *
     * @return
     * The hours
     */
    public String getHours() {
        return hours;
    }

    /**
     *
     * @param hours
     * The hours
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The points
     */
    public String getPoints() {
        return points;
    }

    /**
     *
     * @param points
     * The points
     */
    public void setPoints(String points) {
        this.points = points;
    }

    /**
     *
     * @return
     * The taxClassId
     */
    public String getTaxClassId() {
        return taxClassId;
    }

    /**
     *
     * @param taxClassId
     * The tax_class_id
     */
    public void setTaxClassId(String taxClassId) {
        this.taxClassId = taxClassId;
    }

    /**
     *
     * @return
     * The dateAvailable
     */
    public String getDateAvailable() {
        return dateAvailable;
    }

    /**
     *
     * @param dateAvailable
     * The date_available
     */
    public void setDateAvailable(String dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    /**
     *
     * @return
     * The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     * The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     * The weightClassId
     */
    public String getWeightClassId() {
        return weightClassId;
    }

    /**
     *
     * @param weightClassId
     * The weight_class_id
     */
    public void setWeightClassId(String weightClassId) {
        this.weightClassId = weightClassId;
    }

    /**
     *
     * @return
     * The length
     */
    public String getLength() {
        return length;
    }

    /**
     *
     * @param length
     * The length
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     *
     * @return
     * The width
     */
    public String getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     *
     * @return
     * The height
     */
    public String getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The lengthClassId
     */
    public String getLengthClassId() {
        return lengthClassId;
    }

    /**
     *
     * @param lengthClassId
     * The length_class_id
     */
    public void setLengthClassId(String lengthClassId) {
        this.lengthClassId = lengthClassId;
    }

    /**
     *
     * @return
     * The minimum
     */
    public String getMinimum() {
        return minimum;
    }

    /**
     *
     * @param minimum
     * The minimum
     */
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    /**
     *
     * @return
     * The sortOrder
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     *
     * @param sortOrder
     * The sort_order
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The viewed
     */
    public String getViewed() {
        return viewed;
    }

    /**
     *
     * @param viewed
     * The viewed
     */
    public void setViewed(String viewed) {
        this.viewed = viewed;
    }

    /**
     *
     * @return
     * The dateAdded
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     *
     * @param dateAdded
     * The date_added
     */
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     *
     * @return
     * The dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     *
     * @param dateModified
     * The date_modified
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    /**
     *
     * @return
     * The languageId
     */
    public String getLanguageId() {
        return languageId;
    }

    /**
     *
     * @param languageId
     * The language_id
     */
    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The tag
     */
    public String getTag() {
        return tag;
    }

    /**
     *
     * @param tag
     * The tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     *
     * @return
     * The metaTitle
     */
    public String getMetaTitle() {
        return metaTitle;
    }

    /**
     *
     * @param metaTitle
     * The meta_title
     */
    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    /**
     *
     * @return
     * The storeId
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     *
     * @param storeId
     * The store_id
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /**
     *
     * @return
     * The manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     *
     * @param manufacturer
     * The manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     *
     * @return
     * The discount
     */
    public String getDiscount() {
        return discount;
    }

    /**
     *
     * @param discount
     * The discount
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /**
     *
     * @return
     * The special
     */
    public String getSpecial() {
        return special;
    }

    /**
     *
     * @param special
     * The special
     */
    public void setSpecial(String special) {
        this.special = special;
    }

    /**
     *
     * @return
     * The reward
     */
    public Object getReward() {
        return reward;
    }

    /**
     *
     * @param reward
     * The reward
     */
    public void setReward(Object reward) {
        this.reward = reward;
    }

    /**
     *
     * @return
     * The stockStatus
     */
    public Object getStockStatus() {
        return stockStatus;
    }

    /**
     *
     * @param stockStatus
     * The stock_status
     */
    public void setStockStatus(Object stockStatus) {
        this.stockStatus = stockStatus;
    }

    /**
     *
     * @return
     * The weightClass
     */
    public Object getWeightClass() {
        return weightClass;
    }

    /**
     *
     * @param weightClass
     * The weight_class
     */
    public void setWeightClass(Object weightClass) {
        this.weightClass = weightClass;
    }

    /**
     *
     * @return
     * The lengthClass
     */
    public Object getLengthClass() {
        return lengthClass;
    }

    /**
     *
     * @param lengthClass
     * The length_class
     */
    public void setLengthClass(Object lengthClass) {
        this.lengthClass = lengthClass;
    }

    /**
     *
     * @return
     * The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The reviews
     */
    public String getReviews() {
        return reviews;
    }

    /**
     *
     * @param reviews
     * The reviews
     */
    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    /**
     *
     * @return
     * The options
     */
    public List<Object> getOptions() {
        return options;
    }

    /**
     *
     * @param options
     * The options
     */
    public void setOptions(List<Object> options) {
        this.options = options;
    }

    /**
     *
     * @return
     * The images
     */
    public List<Object> getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(List<Object> images) {
        this.images = images;
    }

    /**
     *
     * @return
     * The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     * The relatedProduct
     */
    public List<RelatedProduct> getRelatedProduct() {
        return relatedProduct;
    }

    /**
     *
     * @param relatedProduct
     * The related_product
     */
    public void setRelatedProduct(List<RelatedProduct> relatedProduct) {
        this.relatedProduct = relatedProduct;
    }

}
