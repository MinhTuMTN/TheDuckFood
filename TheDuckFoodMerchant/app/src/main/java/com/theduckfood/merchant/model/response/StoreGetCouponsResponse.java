package com.theduckfood.merchant.model.response;

import com.theduckfood.merchant.model.Coupon;

import java.util.List;

public class StoreGetCouponsResponse {
    private boolean error;
    private String message;
    private List<Coupon> coupons;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
