package com.theduckfood.api.delivery;

import com.theduckfood.entity.*;
import com.theduckfood.model.request.LoginRequest;
import com.theduckfood.model.response.DeliveryGetOrdersResponse;
import com.theduckfood.model.response.DeliveryLoginResponse;
import com.theduckfood.model.response.DeliveryOrderResponse;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.repositories.DeliveryManAccountRepository;
import com.theduckfood.repositories.DeliveryManRepository;
import com.theduckfood.repositories.OrderRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.EncodingUtil;
import com.theduckfood.util.FCMClient;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryAPI {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DeliveryManAccountRepository deliveryManAccountRepository;

    @Autowired
    DeliveryManRepository deliveryManRepository;

    @PostMapping("/login")
    public ResponseEntity<DeliveryLoginResponse> login(@RequestBody LoginRequest loginRequest) {
        DeliveryManAccount deliveryManAccount = deliveryManAccountRepository
                .findDeliveryManAccountByEmailAndPasswordAndStatus(
                        loginRequest.getEmail(),
                        EncodingUtil.encoding(loginRequest.getPassword()),
                        Constants.DELIVERY_MAN_ACCOUNT_STATUS_ACTIVATED
                );

        if (deliveryManAccount == null)
            return  ResponseEntity.status(401).body(new DeliveryLoginResponse(
                    true,
                    "Tài khoản hoặc mật khẩu không chính xác",
                    null,
                    null)
            );
        return ResponseEntity.status(200).body(new DeliveryLoginResponse(
                false,
                "Đăng nhập thành công",
                deliveryManAccount.getDeliveryMan().getFullName(),
                JWTUtil.generateJWTToken(deliveryManAccount.getEmail(), "shipper")));
    }

    @GetMapping
    public ResponseEntity<SimpleMessageResponse> updateStatus(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("orderId") Long orderId,
            @RequestParam("status") String orderStatus
    ) {
        try {
            DeliveryMan deliveryMan = getDeliveryManFromToken(bearerToken);

            if (orderStatus.equals(Constants.ORDER_STATUS_PROCESSING)
                    || orderStatus.equals(Constants.ORDER_STATUS_SHIPPING)
                    || orderStatus.equals(Constants.ORDER_STATUS_SUCCESS)
                    || orderStatus.equals(Constants.ORDER_STATUS_USER_CANCELED)
            ) {
                Order order = orderRepository.getOrderByOrderId(orderId);
                String userFCMToken = order.getUserAddress().getUserProfile().getFcmToken();

                String title = null;
                String body = null;
                switch (orderStatus) {
                    case Constants.ORDER_STATUS_PROCESSING -> {
                        order.setDeliveryMan(deliveryMan);
                        orderRepository.save(order);

                        assert deliveryMan != null;
                        deliveryMan.setBalance(deliveryMan.getBalance() + (Constants.SHIP_FEE - Constants.SERVICE_FEE));
                        deliveryManRepository.save(deliveryMan);

                        title = "Đơn hàng đã được xác nhận";
                        body = "Đơn hàng của bạn đã được xác nhận. Tài xế đang trên đường đến nhà hàng";
                        FCMClient.merchantSendNotification(order.getStore().getFcmToken(),
                                "Nhà hàng của bạn có đơn hàng mới",
                                "Để xem thông tin chi tiết đơn hàng. Hãy nhấn vào đây"
                                );
                    }
                    case Constants.ORDER_STATUS_SHIPPING -> {
                        title = "Đơn hàng đang trên đường giao đến bạn";
                        body = "Đơn hàng đã được chuẩn bị và giao cho shipper. Bạn vui lòng đợi một tí nữa nha";
                    }
                    case Constants.ORDER_STATUS_SUCCESS -> {
                        title = "Bữa ăn đã được giao thành công";
                        body = "Chúc bạn ăn ngon miệng";
                    }
                    default -> {
                        title = "Đơn hàng bị hủy";
                        body = "Chúng tôi rất tiếc vì sự cố này";
                    }
                }

                order.setStatus(orderStatus);
                orderRepository.save(order);
                FCMClient.userSendNotification(
                        userFCMToken,
                        title,
                        body
                );
                return ResponseEntity.ok(new SimpleMessageResponse(
                        false,
                        "Thành công"
                ));
            } else
                throw new Exception();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new SimpleMessageResponse(
                    true,
                    "Trạng thái không hợp lệ"
            ));
        }
    }

    @GetMapping("/list-orders")
    public ResponseEntity<DeliveryGetOrdersResponse> updateStatus(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("type") String type
    ) {
        DeliveryMan deliveryMan = getDeliveryManFromToken(bearerToken);
        List<Order> orders = new ArrayList<>();
        if (Objects.equals(type, "waiting"))
            orders = orderRepository.findOrdersByStatus(
                    Constants.ORDER_STATUS_WAITING
            );
        else if (type.equals("current")) {
            orders.addAll(orderRepository.findOrdersByDeliveryManAndStatus(
                    deliveryMan,
                    Constants.ORDER_STATUS_PROCESSING
            ));
            orders.addAll(orderRepository.findOrdersByDeliveryManAndStatus(
                    deliveryMan,
                    Constants.ORDER_STATUS_SHIPPING
            ));
        } else {
            orders.addAll(orderRepository.findOrdersByDeliveryManAndStatus(
                    deliveryMan,
                    Constants.ORDER_STATUS_SUCCESS
            ));
            orders.addAll(orderRepository.findOrdersByDeliveryManAndStatus(
                    deliveryMan,
                    Constants.ORDER_STATUS_USER_CANCELED
            ));
        }

        List<DeliveryOrderResponse> orderResponses = new ArrayList<>();
        for (Order order: orders) {
            orderResponses.add(new DeliveryOrderResponse(
                    order,
                    order.getStore().getStoreName(),
                    order.getUserProfile().getFullName()
            ));
        }

        return ResponseEntity.ok(new DeliveryGetOrdersResponse(
                false,
                "Thành công",
                orderResponses
        ));
    }

    private DeliveryMan getDeliveryManFromToken(String bearerToken) {
        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        DeliveryManAccount deliveryManAccount = deliveryManAccountRepository
                .findDeliveryManAccountByEmailAndStatusNotContaining(
                email,
                Constants.DELIVERY_MAN_ACCOUNT_STATUS_DELETED);
        if (deliveryManAccount == null)
            return null;

        return deliveryManAccount.getDeliveryMan();
    }
}
