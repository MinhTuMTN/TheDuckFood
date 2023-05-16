package com.theduckfood.theduckfoodpartner.presenter.contact;

import com.theduckfood.theduckfoodpartner.model.response.DeliveryGetOrdersResponse;
import com.theduckfood.theduckfoodpartner.model.response.SimpleMessageResponse;

public interface IOrderView {
    void getOrdersResponse(DeliveryGetOrdersResponse response);
    void updateOrderResponse(SimpleMessageResponse response);
    void loading(boolean isLoading);
}
