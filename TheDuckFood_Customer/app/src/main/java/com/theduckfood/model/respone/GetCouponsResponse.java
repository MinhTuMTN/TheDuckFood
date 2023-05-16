package com.theduckfood.model.respone;

import com.theduckfood.model.Coupon;

import java.util.List;

public class GetCouponsResponse {
    private boolean error;
    private String message;
    private List<Coupon> coupons;

    public GetCouponsResponse(boolean error, String message, List<Coupon> coupons) {
        this.error = error;
        this.message = message;
        this.coupons = coupons;
    }

    public GetCouponsResponse() {
    }

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
