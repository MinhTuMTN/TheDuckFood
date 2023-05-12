package com.theduckfood.merchant.model.response;

import com.theduckfood.merchant.model.Review;

public class ReviewItemResponse {
    private Review review;
    private String createdAt;
    private String userName;

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
