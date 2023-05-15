package com.theduckfood.model;

import java.util.Date;

public class Coupon {
    private Long couponId;

    private String couponCode;

    private Double minPrice;
    private Double maxDiscount;
    private float discount;
    private int amount;
    private int used;

    private Date createdAt;
    private Date updatedAt;
    private Date startAt;
    private Date expiredAt;

    private String status;

    public Coupon() {
    }

    public Coupon(Long couponId, String couponCode, Double minPrice, Double maxDiscount, float discount, int amount, int used, Date createdAt, Date updatedAt, Date startAt, Date expiredAt, String status) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.minPrice = minPrice;
        this.maxDiscount = maxDiscount;
        this.discount = discount;
        this.amount = amount;
        this.used = used;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startAt = startAt;
        this.expiredAt = expiredAt;
        this.status = status;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
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

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
