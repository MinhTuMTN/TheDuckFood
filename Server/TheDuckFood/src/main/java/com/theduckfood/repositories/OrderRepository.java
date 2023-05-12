package com.theduckfood.repositories;

import com.theduckfood.entity.Order;
import com.theduckfood.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getOrderByOrderId(Long orderId);
    Order getOrderByOrderIdAndStatus(Long orderId, String status);
    List<Order> getOrdersByUserProfile(UserProfile userProfile);
}
