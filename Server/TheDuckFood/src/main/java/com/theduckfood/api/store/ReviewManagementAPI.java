package com.theduckfood.api.store;

import com.theduckfood.entity.Review;
import com.theduckfood.entity.Store;
import com.theduckfood.entity.StoreAccount;
import com.theduckfood.model.response.ReviewItemResponse;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.model.response.StoreGetAllReviewsResponse;
import com.theduckfood.repositories.ReviewRepository;
import com.theduckfood.repositories.StoreAccountRepository;
import com.theduckfood.repositories.StoreRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/merchant/review")
public class ReviewManagementAPI {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    StoreAccountRepository storeAccountRepository;

    @GetMapping
    public ResponseEntity<StoreGetAllReviewsResponse> getAllReviews(
            @RequestHeader("Authorization") String bearerToken
    ) {
        Store store = getStoreFromToken(bearerToken);
        List<Review> reviews = reviewRepository.findReviewsByOrder_Store(store);

        Long[] starts = new Long[5];
        Arrays.fill(starts, 0L);

        if (reviews == null || reviews.size() == 0)
            return ResponseEntity.status(404).body(
                    new StoreGetAllReviewsResponse(
                            true,
                            "Chưa có đánh giá nào",
                            null,
                            0L,
                            0,
                            0L,
                            0L,
                            0L,
                            0L,
                            0L
                    )
            );
        List<ReviewItemResponse> reviewItemResponses = new ArrayList<>();
        for (Review review : reviews) {
            starts[review.getRate() - 1] += 1;
            reviewItemResponses.add(new ReviewItemResponse(
                    review,
                    review.getOrder().getCreatedAt(),
                    review.getUserProfile().getFullName()
            ));
        }
        return ResponseEntity.ok(new StoreGetAllReviewsResponse(
                false,
                "Thành công",
                reviewItemResponses,
                (long) reviews.size(),
                reviews.get(0).getOrder().getStore().getRate(),
                starts[0],
                starts[1],
                starts[2],
                starts[3],
                starts[4]
        ));
    }

    private Store getStoreFromToken(String bearerToken) {
        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        StoreAccount storeAccount = storeAccountRepository.findStoreAccountByEmailAndStatusNotContaining(
                email,
                Constants.STORE_STATUS_DELETED);
        if (storeAccount == null)
            return null;

        return storeAccount.getStore();
    }
}

