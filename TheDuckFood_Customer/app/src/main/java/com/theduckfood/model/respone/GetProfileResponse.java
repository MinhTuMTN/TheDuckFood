package com.theduckfood.model.respone;

import com.theduckfood.model.UserAccount;
import com.theduckfood.model.UserProfile;

public class GetProfileResponse {
    private UserAccount userAccount;
    private UserProfile userProfile;

    private String message;

    private boolean error;

    public GetProfileResponse() {
    }

    public GetProfileResponse(UserAccount userAccount, UserProfile userProfile, String message, boolean error) {
        this.userAccount = userAccount;
        this.userProfile = userProfile;
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
