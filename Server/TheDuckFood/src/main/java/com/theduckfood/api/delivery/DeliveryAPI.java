package com.theduckfood.api.delivery;

import com.theduckfood.entity.Order;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.repositories.OrderRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.FCMClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryAPI {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping
    public ResponseEntity<SimpleMessageResponse> updateStatus(
            @RequestParam("orderId") Long orderId,
            @RequestParam("status") String orderStatus
    ) {
        try {
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
}
