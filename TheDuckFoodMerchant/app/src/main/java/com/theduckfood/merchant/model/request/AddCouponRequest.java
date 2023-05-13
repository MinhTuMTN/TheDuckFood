package com.theduckfood.merchant.model.request;

import java.util.Date;

public class AddCouponRequest {
    private String couponCode;
    private float discountPercent;
    private Double maxDiscount;
    private Double minPrice;
    private int amount;
    private Date startAt;
    private Date expiredAt;

    public AddCouponRequest(String couponCode, float discountPercent, Double maxDiscount, Double minPrice, int amount, Date startAt, Date expiredAt) {
        this.couponCode = couponCode;
        this.discountPercent = discountPercent;
        this.maxDiscount = maxDiscount;
        this.minPrice = minPrice;
        this.amount = amount;
        this.startAt = startAt;
        this.expiredAt = expiredAt;
    }

    public AddCouponRequest() {

    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
}
