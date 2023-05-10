package com.theduckfood.model;

import java.util.Date;

public class Food {
    private Long foodId;
    private String foodName;
    private String description;
    private String image;
    private Double price;
    private Double pricePromotion;
    private String status;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    public Food() {
    }

    public Food(Long foodId, String foodName, String description, String image, Double price, Double pricePromotion, String status, Date createdAt, Date updatedAt) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.pricePromotion = pricePromotion;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(Double pricePromotion) {
        this.pricePromotion = pricePromotion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
