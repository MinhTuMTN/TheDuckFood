package com.theduckfood.model.respone;

import com.theduckfood.model.Food;
import com.theduckfood.model.Store;

import java.util.List;

public class StoreDetailResponse {
    private boolean error;
    private String message;
    private Store store;
    private List<Food> foods;

    public StoreDetailResponse() {
    }

    public StoreDetailResponse(boolean error, String message, Store store, List<Food> foods) {
        this.error = error;
        this.message = message;
        this.store = store;
        this.foods = foods;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
