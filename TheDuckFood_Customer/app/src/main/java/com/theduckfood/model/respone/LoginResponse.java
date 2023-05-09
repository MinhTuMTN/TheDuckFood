package com.theduckfood.model.respone;

import com.theduckfood.model.UserProfile;

public class LoginResponse {
    private boolean error;
    private String message;
    private UserProfile userProfile;
    private String authToken;

    public LoginResponse() {

    }

    public LoginResponse(boolean error, String message, UserProfile userProfile, String authToken) {
        this.error = error;
        this.message = message;
        this.userProfile = userProfile;
        this.authToken = authToken;
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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
