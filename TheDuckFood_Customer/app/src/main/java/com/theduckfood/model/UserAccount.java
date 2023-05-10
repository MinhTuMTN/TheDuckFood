package com.theduckfood.model;

import java.util.Date;

public class UserAccount {
    private String email;

    private String password;
    private String status;

    private String otp;
    private String otpCreatedAt;
    private String otpWrongCount;
    private Date otpLastAttempt;

    private Date updatedAt = new Date();

    public UserAccount(String email, String password, String status, String otp, String otpCreatedAt, String otpWrongCount, Date otpLastAttempt, Date updatedAt) {
        this.email = email;
        this.password = password;
        this.status = status;
        this.otp = otp;
        this.otpCreatedAt = otpCreatedAt;
        this.otpWrongCount = otpWrongCount;
        this.otpLastAttempt = otpLastAttempt;
        this.updatedAt = updatedAt;
    }

    public UserAccount() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtpCreatedAt() {
        return otpCreatedAt;
    }

    public void setOtpCreatedAt(String otpCreatedAt) {
        this.otpCreatedAt = otpCreatedAt;
    }

    public String getOtpWrongCount() {
        return otpWrongCount;
    }

    public void setOtpWrongCount(String otpWrongCount) {
        this.otpWrongCount = otpWrongCount;
    }

    public Date getOtpLastAttempt() {
        return otpLastAttempt;
    }

    public void setOtpLastAttempt(Date otpLastAttempt) {
        this.otpLastAttempt = otpLastAttempt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
