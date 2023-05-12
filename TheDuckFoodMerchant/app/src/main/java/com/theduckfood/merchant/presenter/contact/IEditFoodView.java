package com.theduckfood.merchant.presenter.contact;

import com.theduckfood.merchant.model.response.SimpleMessageResponse;

public interface IEditFoodView {
    void editFoodResponse(SimpleMessageResponse simpleMessageResponse);
    void deleteFoodResponse(SimpleMessageResponse simpleMessageResponse);
}
