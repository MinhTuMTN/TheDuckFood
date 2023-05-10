package com.theduckfood.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewRequest {
    private int rate;
    private String review_content;
    private Long orderId;
}
