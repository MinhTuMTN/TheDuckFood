package com.theduckfood.merchant.model.response;

public class OrderItemResponse {
    private String foodName;
    private int amount;

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
