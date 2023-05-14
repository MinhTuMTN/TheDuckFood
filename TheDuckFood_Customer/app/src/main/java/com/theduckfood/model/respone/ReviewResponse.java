package com.theduckfood.model.respone;

import java.io.Serializable;

public class ReviewResponse implements Serializable {
    private int rate;
    private String content;

    public ReviewResponse() {
    }

    public ReviewResponse(int rate, String content) {
        this.rate = rate;
        this.content = content;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
