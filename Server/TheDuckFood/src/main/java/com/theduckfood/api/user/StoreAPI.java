package com.theduckfood.api.user;

import com.theduckfood.entity.*;
import com.theduckfood.model.request.ChangePasswordRequest;
import com.theduckfood.model.request.ReviewRequest;
import com.theduckfood.model.response.FoodDetailsResponse;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.model.response.StoreDetailsResponse;
import com.theduckfood.model.response.UserGetListStore;
import com.theduckfood.repositories.*;
import com.theduckfood.util.Constants;
import com.theduckfood.util.JWTUtil;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    UserFavoritesRepository userFavoritesRepository;

    @GetMapping
    public ResponseEntity<StoreDetailsResponse> getStoreDetails(
            @RequestParam(value = "storeId", required = true) Long storeId) {
        Store store = storeRepository.getStoreByStoreIdAndStatusNotContains(
                storeId,
                Constants.STORE_STATUS_DELETED);
        if (store != null)
            return ResponseEntity.ok(new StoreDetailsResponse(
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

            if (order.getReview() != null)
                return ResponseEntity.status(409).body(new SimpleMessageResponse(
                        true,
                        "Đơn hàng đã được đánh giá"
                ));

            if (!Objects.equals(userProfile.getUserId(), order.getUserProfile().getUserId()))
                return ResponseEntity.status(401).body(new SimpleMessageResponse(
                        true,
                        "JWT Token không hợp lệ"
                ));

            Review review = new Review(
                    reviewRequest.getReview_content(),
                    reviewRequest.getRate(),
                    userProfile,
                    order);
            reviewRepository.save(review);

            Store store = Hibernate.unproxy(order.getStore(), Store.class);

            store.setRate((store.getRate() * store.getReviewCount() + reviewRequest.getRate())
                    / (store.getReviewCount() + 1));
            store.setReviewCount(store.getReviewCount() + 1);
            storeRepository.save(store);

            return ResponseEntity.ok(new SimpleMessageResponse(
                    false,
                    "Thành công"
            ));
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.status(400).body(new SimpleMessageResponse(
                    true,
                    "Đã có lỗi xảy ra"));
        }
    }

    @GetMapping("/favorites")
    public ResponseEntity<SimpleMessageResponse> userFavorite(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "storeId", required = true) Long storeId) {
        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        UserProfile userProfile = userAccountRepository.findUserAccountByEmail(email).getUser();
        Store store = storeRepository.getStoreByStoreIdAndStatusNotContains(storeId, Constants.STORE_STATUS_DELETED);

        UserFavorites userFavorites = userFavoritesRepository.getUserFavoritesByUserProfileAndStore(userProfile, store);
        if (userFavorites != null) {
            userFavoritesRepository.delete(userFavorites);
            return ResponseEntity.ok(new SimpleMessageResponse(
                    false,
                    "Đã bỏ thích cửa hàng này"
            ));
        }

        userFavorites = new UserFavorites();
        userFavorites.setStore(store);
        userFavorites.setUserProfile(userProfile);
        userFavoritesRepository.save(userFavorites);
        return ResponseEntity.ok(new SimpleMessageResponse(
                false,
                "Đã thích cửa hàng này"
        ));
    }

    @GetMapping("/list-store")
    public ResponseEntity<UserGetListStore> getListStore(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "sort", required = false) String sortParam,
            @RequestParam(value = "sortType", required = false) String sortType
    ) {

        page = page == null ? 0 : page;
        limit = limit == null ? 5 : limit;
        if (sortParam != null)
            switch (sortParam) {
                case "rate" -> sortParam = "rate";
                case "date" -> sortParam = "createdAt";
                default -> sortParam = "storeId";
            }
        else sortParam = "storeId";

        Sort sort = (sortType != null && sortType.equals("ASC")) ? Sort.by(sortParam).ascending() : Sort.by(sortParam).descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        List<Store> stores = storeRepository.getStoresByStatusNotContaining(
                Constants.STORE_STATUS_DELETED,
                pageable
        );
        return ResponseEntity.ok(new UserGetListStore(
                false,
                "Thành công",
                stores
        ));

    }
}
