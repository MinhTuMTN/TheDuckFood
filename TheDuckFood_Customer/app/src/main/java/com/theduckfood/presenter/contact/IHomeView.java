package com.theduckfood.presenter.contact;

import com.theduckfood.model.respone.UserGetListStore;

public interface IHomeView {
    void getRecommendStores(UserGetListStore userGetListStore);
    void getNewestStores(UserGetListStore userGetListStore);
}
