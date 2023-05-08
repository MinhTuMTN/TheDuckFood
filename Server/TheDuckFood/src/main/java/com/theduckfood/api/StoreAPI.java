package com.theduckfood.api;

import com.theduckfood.entity.*;
import com.theduckfood.model.request.ChangePasswordRequest;
import com.theduckfood.model.request.ReviewRequest;
import com.theduckfood.model.response.FoodDetailsResponse;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.model.response.StoreDetailsResponse;
import com.theduckfood.repositories.*;
import com.theduckfood.util.Constants;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/store")
public class StoreAPI {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ReviewRepository reviewRepository;
    @GetMapping
    public ResponseEntity<StoreDetailsResponse> getStoreDetails(
            @RequestParam(value = "storeId", required = true) Long storeId) {
        Store store = storeRepository.getStoreByStoreIdAndStatusNotContains(
                storeId,
                Constants.STORE_STATUS_DELETED);
        if (store != null)
            return  ResponseEntity.ok(new StoreDetailsResponse(
                    false,
                    "Thành công",
                    store,
                    store.getFoods()));
        return ResponseEntity.status(404).body(new StoreDetailsResponse(
                true,
                "Không tìm thấy cửa hàng",
                null,
                null
        ));
    }

    @PostMapping("/review")
    @Transactional
    public ResponseEntity<SimpleMessageResponse> userReview(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody ReviewRequest reviewRequest) {
        try {
            String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
            UserProfile userProfile = userAccountRepository.findUserAccountsByEmailAndStatus(
                    email,
                    Constants.USER_ACCOUNT_STATUS_ACTIVATED
            ).getUser();

            Order order = orderRepository.getOrderByOrderIdAndStatus(
                    reviewRequest.getOrderId(),
                    Constants.ORDER_STATUS_SUCCESS
            );

            if(order.getReview() != null)
                throw new Exception();

            if(!Objects.equals(userProfile.getUserId(), order.getUserProfile().getUserId()))
                throw new Exception();

            Review review = new Review(
                    reviewRequest.getReview_content(),
                    reviewRequest.getRate(),
                    userProfile,
                    order);
            reviewRepository.save(review);

            Store store = order.getStore();
            store.setRate((store.getRate() * store.getReviewCount() + reviewRequest.getRate())
                    / (store.getReviewCount() + 1));
            store.setReviewCount(store.getReviewCount() + 1);
            storeRepository.save(store);

            return  ResponseEntity.ok(new SimpleMessageResponse(
                    false,
                    "Thành công"
            ));
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e.getMessage());
            return  ResponseEntity.status(400).body(new SimpleMessageResponse(
                    true,
                    "Đã có lỗi xảy ra"));
        }
    }
}
