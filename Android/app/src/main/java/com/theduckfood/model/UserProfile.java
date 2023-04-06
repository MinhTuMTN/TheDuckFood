package com.theduckfood.model;

import java.util.Date;

public class UserProfile {
    private Long userId;
    private String phone;
    private String fullName;
    private int points = 0;
    private Double balance = 0D;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private boolean isVerified = false;
    private String fcmToken = null;

    public UserProfile() {

    }

    public UserProfile(Long userId, String phone, String fullName, int points, Double balance, Date createdAt, Date updatedAt, boolean isVerified, String fcmToken) {
        this.userId = userId;
        this.phone = phone;
        this.fullName = fullName;
        this.points = points;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isVerified = isVerified;
        this.fcmToken = fcmToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
