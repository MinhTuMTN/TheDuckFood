package com.theduckfood.theduckfoodpartner.presenter.contact;

import com.theduckfood.theduckfoodpartner.model.response.LoginResponse;

public interface ILoginView {
    public void login(LoginResponse loginResponse);
    public void isLogging(boolean isLogging);
}
