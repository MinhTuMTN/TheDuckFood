package com.theduckfood.presenter.contact;

import com.theduckfood.model.respone.GetProfileResponse;
import com.theduckfood.model.respone.SimpleMessageResponse;

public interface IUpdateProfileView {
    public void updateProfile(SimpleMessageResponse simpleMessageResponse);
    public void getProfile(GetProfileResponse getProfileResponse);
}
