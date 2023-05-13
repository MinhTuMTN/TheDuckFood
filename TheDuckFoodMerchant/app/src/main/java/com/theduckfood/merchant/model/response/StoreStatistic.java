package com.theduckfood.merchant.model.response;

import java.util.Date;

public class StoreStatistic {
    private Date date;
    private Double amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
