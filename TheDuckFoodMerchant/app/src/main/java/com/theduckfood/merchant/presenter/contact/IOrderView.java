package com.theduckfood.merchant.presenter.contact;

import com.theduckfood.merchant.model.response.StoreGetOrdersResponse;

public interface IOrderView {
    void getOrdersResponse(StoreGetOrdersResponse ordersResponse);
}
