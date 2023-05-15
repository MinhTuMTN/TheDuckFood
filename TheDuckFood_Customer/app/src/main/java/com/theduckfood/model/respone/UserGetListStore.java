package com.theduckfood.model.respone;

import com.theduckfood.model.Store;

import java.util.List;

public class UserGetListStore {
    private boolean error;
    private String message;
    List<Store> stores;

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
}
