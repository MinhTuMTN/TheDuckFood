package com.theduckfood.presenter.contact;

import com.theduckfood.model.UserAccount;
import com.theduckfood.model.UserProfile;
import com.theduckfood.model.respone.GetProfileResponse;
import com.theduckfood.model.respone.SimpleMessageResponse;

public interface IProfileView {
    public void getProfile(GetProfileResponse getProfileResponse);

}
