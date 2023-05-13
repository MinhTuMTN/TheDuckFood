package com.theduckfood.model.respone;

import com.theduckfood.model.Food;

public class FoodDetailResponse {
    private boolean error;
    private String message;
    private Food food;

    public FoodDetailResponse() {
    }

    public FoodDetailResponse(boolean error, String message, Food food) {
        this.error = error;
        this.message = message;
        this.food = food;
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
