package com.theduckfood.model.respone;

import java.io.Serializable;

public class OrderItemResponse implements Serializable {
    private String foodName;
    private int amount;
    private Double price;

    public OrderItemResponse(String foodName, int amount, Double price) {
        this.foodName = foodName;
        this.amount = amount;
        this.price = price;
    }

    public OrderItemResponse() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
