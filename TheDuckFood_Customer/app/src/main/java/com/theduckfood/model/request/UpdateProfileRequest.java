package com.theduckfood.model.request;

public class UpdateProfileRequest {
    private String phone;
    private String fullName;

    public UpdateProfileRequest() {
    }

    public UpdateProfileRequest(String fullName, String phone) {
        this.phone = phone;
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
