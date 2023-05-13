package com.theduckfood.model.respone;

import java.io.Serializable;

public class StoreResponse implements Serializable {
    private Long storeId;
    private String storeName;
    private String storeAddress;

    public StoreResponse() {
    }

    public StoreResponse(Long storeId, String storeName, String storeAddress) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
