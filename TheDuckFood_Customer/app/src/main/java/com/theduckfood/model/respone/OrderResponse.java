package com.theduckfood.model.respone;

import com.theduckfood.model.Order;

import java.io.Serializable;
import java.util.List;

public class OrderResponse implements Serializable {
    private Order order;
    private StoreResponse store;
    private String address;
    List<OrderItemResponse> orderItems;

    public OrderResponse() {
    }

    public OrderResponse(Order order, StoreResponse store, String address, List<OrderItemResponse> orderItems) {
        this.order = order;
        this.store = store;
        this.address = address;
        this.orderItems = orderItems;
    }

    public StoreResponse getStore() {
        return store;
    }

    public void setStore(StoreResponse store) {
        this.store = store;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }
}
