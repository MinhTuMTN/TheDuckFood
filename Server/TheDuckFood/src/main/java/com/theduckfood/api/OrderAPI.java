package com.theduckfood.api;

import com.theduckfood.entity.*;
import com.theduckfood.model.request.FoodOrderItemRequest;
import com.theduckfood.model.request.OrderRequest;
import com.theduckfood.model.response.CreateOrderResponse;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.repositories.*;
import com.theduckfood.util.Constants;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserAddressRepository userAddressRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserProfileRepository userProfileRepository;
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<CreateOrderResponse> createOrder(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody OrderRequest orderRequest) {
        Optional<Store> store = storeRepository.findById(orderRequest.getStoreId());
        if (store.isEmpty())
            return ResponseEntity.status(404).body(
                    new CreateOrderResponse(
                            true,
                            "Không tìm thấy cửa hàng",
                            null,
                            null,
                            null
                    )
            );

        double amount_before_coupon = 0d;
        List<OrderItem> orderItems = new ArrayList<>();
        for (FoodOrderItemRequest orderItemRequest : orderRequest.getFoods()) {
            Food food = foodRepository.getFoodByFoodIdAndStoreAndStatus(
                    orderItemRequest.getFoodId(),
                    store.get(),
                    Constants.FOOD_STATUS_SELLING);
            if (food == null)
                return ResponseEntity.status(404).body(
                        new CreateOrderResponse(
                                true,
                                "Không tìm thấy món ăn",
                                null,
                                null,
                                null
                        )
                );
            amount_before_coupon += food.getPricePromotion() * orderItemRequest.getAmount();

            OrderItem orderItem = new OrderItem();
            orderItem.setAmount(orderItemRequest.getAmount());
            orderItem.setFoodPrice((double) food.getPricePromotion());
            orderItem.setFood(food);
            orderItems.add(orderItem);
        }

        Coupon coupon = couponRepository.findCouponByCouponCodeAndStatusAndStore(
                orderRequest.getCouponCode(),
                Constants.COUPON_STATUS_ACTIVATED,
                store.get()
        );

        boolean couponIsValid = coupon != null
                && coupon.getUsed() < coupon.getAmount()
                && coupon.getMinPrice() <= amount_before_coupon
                && (new Date()).before(coupon.getExpiredAt());

        double amount = amount_before_coupon;
        if (couponIsValid) {
            double temp = amount * coupon.getDiscount();
            temp =  temp > coupon.getMaxDiscount() ? coupon.getMaxDiscount() : temp;
            amount -= temp;
        }

        UserAddress userAddress = userAddressRepository
                .findUserAddressesByUserAddressIdAndIsDeletedFalse(
                        orderRequest.getUserAddressId()
                );
        if (userAddress == null)
            return ResponseEntity.status(404).body(
                    new CreateOrderResponse(
                            true,
                            "Địa chỉ không hợp lệ",
                            null,
                            null,
                            null
                    )
            );

        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        UserProfile userProfile = userAccountRepository.findUserAccountByEmail(email).getUser();

        Order order = new Order();
        order.setAmount(amount);
        order.setCoupon(couponIsValid ? coupon: null);
        order.setStore(store.get());
        order.setUserAddress(userAddress);
        order.setUserProfile(userProfile);
        order.setOrderItems(orderItems);

        try {
            orderRepository.save(order);

            Store updateStore = store.get();
            updateStore.setBalance(updateStore.getBalance() + order.getAmount() - 2000);
            storeRepository.save(updateStore);

            if (couponIsValid) {
                coupon.setUsed(coupon.getUsed() + 1);
                couponRepository.save(coupon);
            }

            userProfile.setBalance(userProfile.getBalance() - order.getAmount() - 17000);
            userProfileRepository.save(userProfile);

            return ResponseEntity.ok(
                    new CreateOrderResponse(
                            false,
                            "Thành công",
                            amount_before_coupon,
                            couponIsValid ? coupon : null,
                            order
                    )
            );
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e.getMessage());
            return ResponseEntity.status(400)
                    .body(
                    new CreateOrderResponse(
                            false,
                            "Có lỗi xảy ra",
                            amount_before_coupon,
                            couponIsValid ? coupon : null,
                            order
                    ));
        }
    }
}
