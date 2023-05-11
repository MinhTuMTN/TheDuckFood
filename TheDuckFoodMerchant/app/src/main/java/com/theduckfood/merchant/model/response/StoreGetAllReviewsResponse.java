package com.theduckfood.merchant.model.response;

import java.util.List;

public class StoreGetAllReviewsResponse {
    private boolean error;
    private String message;
    private List<ReviewItemResponse> reviews;
    private Long reviewCount;
    private float averageRating;
    private Long oneStart;
    private Long twoStart;
    private Long threeStart;
    private Long fourStart;
    private Long fiveStart;

    public Long getOneStart() {
        return oneStart;
    }

    public void setOneStart(Long oneStart) {
        this.oneStart = oneStart;
    }

    public Long getTwoStart() {
        return twoStart;
    }

    public void setTwoStart(Long twoStart) {
        this.twoStart = twoStart;
    }

    public Long getThreeStart() {
        return threeStart;
    }

    public void setThreeStart(Long threeStart) {
        this.threeStart = threeStart;
    }

    public Long getFourStart() {
        return fourStart;
    }

    public void setFourStart(Long fourStart) {
        this.fourStart = fourStart;
    }

    public Long getFiveStart() {
        return fiveStart;
    }

    public void setFiveStart(Long fiveStart) {
        this.fiveStart = fiveStart;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
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

    public List<ReviewItemResponse> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewItemResponse> reviews) {
        this.reviews = reviews;
    }
}
