package com.theduckfood.theduckfoodpartner.model.response;

import com.theduckfood.theduckfoodpartner.model.Order;

public class DeliveryOrderResponse {
    private Order order;
    private String storeName;
    private String userName;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
