package com.theduckfood.api.store;

import com.theduckfood.entity.Order;
import com.theduckfood.entity.OrderItem;
import com.theduckfood.entity.Store;
import com.theduckfood.entity.StoreAccount;
import com.theduckfood.model.response.*;
import com.theduckfood.repositories.OrderRepository;
import com.theduckfood.repositories.StoreAccountRepository;
import com.theduckfood.repositories.StoreRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.DateTimeUtil;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/merchant/order")
public class OrderManagementAPI {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    StoreAccountRepository storeAccountRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping
    public ResponseEntity<StoreGetOrdersResponse> getOrders(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("status") String status
    ) {
        try {
            Store store = getStoreFromToken(bearerToken);
            List<Order> orders = orderRepository.getOrdersByStoreAndStatus(store, status);

            List<StoreOrderResponse> storeOrderResponses = new ArrayList<>();
            for (Order order : orders) {
                List<OrderItemResponse> orderItemResponses = new ArrayList<>();
                for (OrderItem orderItem : order.getOrderItems())
                    orderItemResponses.add(new OrderItemResponse(
                            orderItem.getFood().getFoodName(),
                            orderItem.getAmount(),
                            orderItem.getFoodPrice()
                    ));

                storeOrderResponses.add(new StoreOrderResponse(
                        order,
                        order.getUserAddress().getStreetAddress(),
                        order.getUserProfile().getFullName(),
                        order.getDeliveryMan() == null ? null : order.getDeliveryMan().getFullName(),
                        orderItemResponses
                ));
            }

            assert store != null;
            return ResponseEntity.ok(new StoreGetOrdersResponse(
                    false,
                    "Thành công",
                    store.getStoreName(),
                    storeOrderResponses
            ));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new StoreGetOrdersResponse(
                    false,
                    "Đã có lỗi xảy ra",
                    null,
                    null
            ));
        }
    }

    @GetMapping("/statistic")
    public ResponseEntity<StoreGetStatisticResponse> statistic(
            @RequestHeader("Authorization") String bearerToken
    ) {
        Store store = getStoreFromToken(bearerToken);

        LocalDate now = LocalDate.now();
        LocalDate today = now.atStartOfDay().toLocalDate().plusDays(1);
        LocalDate sevenDaysAgo = now.atStartOfDay().toLocalDate().minusDays(7);
        List<Object[]> results = orderRepository.findOrderAmountByDateBetween(
                DateTimeUtil.convertLocalDateToDate(sevenDaysAgo),
                DateTimeUtil.convertLocalDateToDate(today),
                store);

        List<StoreStatistic> statistics = new ArrayList<>();
        for (Object[] row : results) {
            Date date = (Date) row[0];
            Double totalAmount = (Double) row[1];
            System.out.println(date.toString() + " - " + totalAmount.toString());
            statistics.add(new StoreStatistic(date, totalAmount));
        }
        return ResponseEntity.ok(
                new StoreGetStatisticResponse(
                        false,
                        "Thành công",
                        store,
                        statistics
                )
        );
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
