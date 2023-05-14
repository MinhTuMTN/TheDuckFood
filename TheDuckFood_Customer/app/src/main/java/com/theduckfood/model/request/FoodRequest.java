package com.theduckfood.model.request;

public class FoodRequest {
    private Long foodId;
    private int amount;

    public FoodRequest() {
    }

    public FoodRequest(Long foodId, int amount) {
        this.foodId = foodId;
        this.amount = amount;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
