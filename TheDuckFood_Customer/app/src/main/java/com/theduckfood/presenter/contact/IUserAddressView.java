package com.theduckfood.presenter.contact;

import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.model.respone.UserAddressResponse;

public interface IUserAddressView {
    public void addUserAddress(SimpleMessageResponse simpleMessageResponse);
    public void getUserAddress(UserAddressResponse userAddressResponse);
    public void deleteUserAddress(UserAddressResponse userAddressResponse);
}
