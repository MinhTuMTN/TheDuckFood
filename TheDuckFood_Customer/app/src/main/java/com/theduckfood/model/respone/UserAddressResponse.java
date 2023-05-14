package com.theduckfood.model.respone;

import com.theduckfood.model.UserAddress;

import java.util.List;

public class UserAddressResponse {
    private boolean error;
    private String message;
    private List<UserAddress> userAddresses;

    public UserAddressResponse() {
    }

    public UserAddressResponse(boolean error, String message, List<UserAddress> userAddresses) {
        this.error = error;
        this.message = message;
        this.userAddresses = userAddresses;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }
}
