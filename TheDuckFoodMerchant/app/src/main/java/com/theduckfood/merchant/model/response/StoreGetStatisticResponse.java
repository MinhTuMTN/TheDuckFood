package com.theduckfood.merchant.model.response;

import com.theduckfood.merchant.model.Store;

import java.util.List;

public class StoreGetStatisticResponse {
    private boolean error;
    private String message;
    private Store store;
    private List<StoreStatistic> statistics;

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

    public List<StoreStatistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<StoreStatistic> statistics) {
        this.statistics = statistics;
    }
}
