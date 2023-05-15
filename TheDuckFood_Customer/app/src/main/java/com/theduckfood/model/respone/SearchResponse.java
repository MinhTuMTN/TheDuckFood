package com.theduckfood.model.respone;

import com.theduckfood.model.Food;
import com.theduckfood.model.FoodSearchResponse;
import com.theduckfood.model.Store;

import java.util.List;

public class SearchResponse {
    private boolean error;
    private String message;
    private List<Store> stores;
    private List<FoodSearchResponse> foods;

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

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public List<FoodSearchResponse> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodSearchResponse> foods) {
        this.foods = foods;
    }
}
