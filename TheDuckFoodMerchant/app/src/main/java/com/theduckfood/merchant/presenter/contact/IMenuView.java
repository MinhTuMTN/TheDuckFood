package com.theduckfood.merchant.presenter.contact;

import com.theduckfood.merchant.model.response.StoreGetAllFoodsResponse;

public interface IMenuView {
    void loadFoods(StoreGetAllFoodsResponse foodsResponse);
    void loading(boolean isLoading);
}
