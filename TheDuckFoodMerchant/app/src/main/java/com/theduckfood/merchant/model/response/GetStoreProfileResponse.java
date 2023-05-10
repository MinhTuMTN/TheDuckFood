package com.theduckfood.merchant.model.response;

import com.theduckfood.merchant.model.Store;

public class GetStoreProfileResponse {
    private boolean error;
    private String message;
    private Store store;

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
}
