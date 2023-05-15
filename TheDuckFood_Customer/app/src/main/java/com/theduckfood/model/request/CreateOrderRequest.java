package com.theduckfood.model.request;

import java.util.List;

public class CreateOrderRequest {
    private String couponCode;
    private Long storeId;
    private Long userAddressId;
    private List<FoodRequest> foods;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(String couponCode, Long storeId, Long userAddressId, List<FoodRequest> foods) {
        this.couponCode = couponCode;
        this.storeId = storeId;
        this.userAddressId = userAddressId;
        this.foods = foods;
    }

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userAddressId) {
        this.userAddressId = userAddressId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<FoodRequest> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodRequest> foods) {
        this.foods = foods;
    }
}
