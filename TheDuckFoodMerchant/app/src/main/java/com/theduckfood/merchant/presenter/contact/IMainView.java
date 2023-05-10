package com.theduckfood.merchant.presenter.contact;

import com.theduckfood.merchant.model.response.GetStoreProfileResponse;

public interface IMainView {
    void loadProfile(GetStoreProfileResponse storeProfileResponse);
    void isLoading(boolean isLoading);
}
