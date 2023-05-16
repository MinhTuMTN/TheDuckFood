package com.theduckfood.theduckfoodpartner.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Long orderId;
    private Double amount;
    private Double shipFee;
    private Double serviceFee;
    private Date createdAt;
    private Date updatedAt;
    private String status;

    public Order() {
    }

    public Order(Long orderId, Double amount, Double shipFee, Double serviceFee, Date createdAt, Date updatedAt, String status) {
        this.orderId = orderId;
        this.amount = amount;
        this.shipFee = shipFee;
        this.serviceFee = serviceFee;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getShipFee() {
        return shipFee;
    }

    public void setShipFee(Double shipFee) {
        this.shipFee = shipFee;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
