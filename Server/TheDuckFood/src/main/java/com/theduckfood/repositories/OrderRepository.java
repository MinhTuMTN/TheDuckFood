package com.theduckfood.repositories;

import com.theduckfood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getOrderByOrderId(Long orderId);
    Order getOrderByOrderIdAndStatus(Long orderId, String status);
}
