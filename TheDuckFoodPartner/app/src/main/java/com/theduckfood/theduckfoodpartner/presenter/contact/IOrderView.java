package com.theduckfood.theduckfoodpartner.presenter.contact;

import com.theduckfood.theduckfoodpartner.model.response.DeliveryGetOrdersResponse;

public interface IOrderView {
    void getOrdersResponse(DeliveryGetOrdersResponse response);
    void loading(boolean isLoading);
}
