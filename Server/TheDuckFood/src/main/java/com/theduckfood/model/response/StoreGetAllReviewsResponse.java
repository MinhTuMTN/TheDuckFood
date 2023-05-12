package com.theduckfood.model.response;

import com.theduckfood.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreGetAllReviewsResponse {
    private boolean error;
    private String message;
    List<ReviewItemResponse> reviews;
    private Long reviewCount;
    private float averageRating;
    private Long oneStart;
    private Long twoStart;
    private Long threeStart;
    private Long fourStart;
    private Long fiveStart;
}
