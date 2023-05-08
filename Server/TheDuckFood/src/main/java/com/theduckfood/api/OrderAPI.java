package com.theduckfood.api;

import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/create")
    public ResponseEntity<SimpleMessageResponse> createOrder(
            @RequestHeader("Authorization") String bearerToken
    ) {
        return  null;
    }
}
