package com.theduckfood.api.user;

import com.theduckfood.entity.*;
import com.theduckfood.model.request.FoodOrderItemRequest;
import com.theduckfood.model.request.OrderRequest;
import com.theduckfood.model.response.*;
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

    @Autowired
    OrderItemRepository orderItemRepository;

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
            temp = temp > coupon.getMaxDiscount() ? coupon.getMaxDiscount() : temp;
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
        order.setCoupon(couponIsValid ? coupon : null);
        order.setStore(store.get());
        order.setUserAddress(userAddress);
        order.setUserProfile(userProfile);
        order.setOrderItems(orderItems);

        try {
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrder(order);
                orderItemRepository.save(orderItem);
            }
            orderRepository.save(order);

            Store updateStore = store.get();
            updateStore.setBalance(updateStore.getBalance() + order.getAmount() - Constants.SERVICE_FEE);
            storeRepository.save(updateStore);

            if (couponIsValid) {
                coupon.setUsed(coupon.getUsed() + 1);
                couponRepository.save(coupon);
            }

            userProfile.setBalance(userProfile.getBalance() - order.getAmount() - Constants.SHIP_FEE - Constants.SERVICE_FEE);
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

    @GetMapping("/cancel-order")
    @Transactional
    public ResponseEntity<SimpleMessageResponse> cancelOrder(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "orderId", required = true) Long orderId
    ) {
        try {
            String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
            UserProfile userProfile = userAccountRepository.findUserAccountByEmail(email).getUser();

            Order order = orderRepository.getOrderByOrderIdAndStatus(orderId, Constants.ORDER_STATUS_PROCESSING);
            if (order == null || !Objects.equals(
                    userProfile.getUserId(),
                    order.getUserProfile().getUserId()))
                throw new Exception();

            order.setStatus(Constants.ORDER_STATUS_USER_CANCELED);
            orderRepository.save(order);

            userProfile.setBalance(order.getAmount() + Constants.SHIP_FEE + Constants.SERVICE_FEE);
            userProfileRepository.save(userProfile);

            return ResponseEntity.ok(new SimpleMessageResponse(
                    false,
                    "Hủy đơn hàng thành công"
            ));
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(new SimpleMessageResponse(
                    true,
                    "Không thể hủy đơn hàng này"
            ));
        }
    }

    @GetMapping
    public ResponseEntity<UserGetAllOrderResponse> getAllOrders(
            @RequestHeader("Authorization") String bearerToken
    ) {
        try {
            String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
            UserProfile userProfile = userAccountRepository.findUserAccountByEmail(email).getUser();

            List<Order> orders = orderRepository.getOrdersByUserProfile(userProfile);
            if (orders == null || orders.size() == 0)
                throw new Exception();

            List<OrderResponse> orderResponses = new ArrayList<>();
            for (Order order : orders) {
                List<OrderItemResponse> orderItemResponses = new ArrayList<>();
                for (OrderItem orderItem : order.getOrderItems())
                    orderItemResponses.add(new OrderItemResponse(
                            orderItem.getFood().getFoodName(),
                            orderItem.getAmount()));
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setOrder(order);
                orderResponse.setAddress(order.getUserAddress().getStreetAddress());
                orderResponse.setOrderItems(orderItemResponses);
                orderResponses.add(orderResponse);
            }
            return ResponseEntity.ok(new UserGetAllOrderResponse(
                    false,
                    "Thành công",
                    orderResponses
            ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(new UserGetAllOrderResponse(
                    true,
                    "Đã có lỗi xảy ra",
                    null
            ));
        }
    }

}
