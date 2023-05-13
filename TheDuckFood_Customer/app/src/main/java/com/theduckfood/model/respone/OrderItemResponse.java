package com.theduckfood.model.respone;

public class OrderItemResponse {
    private String foodName;
    private int amount;

    public OrderItemResponse() {
    }

    public OrderItemResponse(String foodName, int amount) {
        this.foodName = foodName;
        this.amount = amount;
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
