package com.theduckfood.model.request;

public class UserReviewRequest {
    private float rate;
    private String review_content;
    private Long orderId;

    public UserReviewRequest() {
    }

    public UserReviewRequest(float rate, String review_content, Long orderId) {
        this.rate = rate;
        this.review_content = review_content;
        this.orderId = orderId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
