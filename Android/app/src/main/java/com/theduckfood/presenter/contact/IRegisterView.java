package com.theduckfood.presenter.contact;

import com.theduckfood.model.respone.SignUpResponse;

public interface IRegisterView {
    public void register(SignUpResponse signUpResponse);
    public void loading(boolean isLoading);
}
