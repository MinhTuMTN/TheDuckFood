package com.theduckfood.model.respone;

import com.theduckfood.model.Coupon;
import com.theduckfood.model.Order;

public class CreateOrderResponse {
    private boolean error;
    private String message;
    private Double amount_before_coupon;
    private Coupon coupon;
    private Order order;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(boolean error, String message, Double amount_before_coupon, Coupon coupon, Order order) {
        this.error = error;
        this.message = message;
        this.amount_before_coupon = amount_before_coupon;
        this.coupon = coupon;
        this.order = order;
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

    public Double getAmount_before_coupon() {
        return amount_before_coupon;
    }

    public void setAmount_before_coupon(Double amount_before_coupon) {
        this.amount_before_coupon = amount_before_coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
