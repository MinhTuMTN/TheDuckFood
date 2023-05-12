package com.theduckfood.presenter.contact;

import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.model.respone.StoreDetailResponse;

public interface IStoreDetailView {
    public void  getStoreDetail(StoreDetailResponse storeDetailResponse);
    public void favorite(SimpleMessageResponse simpleMessageResponse);
}
