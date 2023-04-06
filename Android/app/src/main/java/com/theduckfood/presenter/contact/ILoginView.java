package com.theduckfood.presenter.contact;

import com.theduckfood.model.respone.LoginResponse;

public interface ILoginView {
    public void login(LoginResponse loginResponse);
    public void isLogging(boolean isLogging);
}
