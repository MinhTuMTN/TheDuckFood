package com.theduckfood.merchant.presenter.contact;

import com.theduckfood.merchant.model.response.StoreLoginResponse;

public interface ILoginView {
    public void login(StoreLoginResponse loginResponse);
    public void isLogging(boolean isLogging);
}
