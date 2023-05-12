package com.theduckfood.model.response;

import com.theduckfood.entity.Order;
import com.theduckfood.entity.Review;
import com.theduckfood.entity.Store;
import com.theduckfood.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewItemResponse {
    private Review review;
    private Date createdAt;
    private String userName;
}
