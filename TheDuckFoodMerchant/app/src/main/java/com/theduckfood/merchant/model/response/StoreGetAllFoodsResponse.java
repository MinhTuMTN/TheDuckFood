package com.theduckfood.merchant.model.response;

import com.theduckfood.merchant.model.Food;

import java.util.List;

public class StoreGetAllFoodsResponse {
    private boolean error;
    private String message;
    private List<Food> foods;

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

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
