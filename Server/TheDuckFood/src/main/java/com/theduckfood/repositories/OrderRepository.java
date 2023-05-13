package com.theduckfood.repositories;

import com.theduckfood.entity.Order;
import com.theduckfood.entity.Store;
import com.theduckfood.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getOrderByOrderId(Long orderId);
    Order getOrderByOrderIdAndStatus(Long orderId, String status);
    List<Order> getOrdersByUserProfile(UserProfile userProfile);
    List<Order> getOrdersByStoreAndStatus(Store store, String status);
    @Query("SELECT cast(o.createdAt as date), SUM(o.amount) " +
            "FROM Order o " +
            "WHERE o.createdAt between ?1 AND ?2 AND o.store = ?3 " +
            "GROUP BY cast(o.createdAt as date) ")
    List<Object[]> findOrderAmountByDateBetween(Date startDate, Date endDate, Store store);
    @Query("SELECT SUM(o.amount), count(*) " +
            "FROM Order o " +
            "WHERE cast(o.createdAt as date ) = cast(GETDATE() as date) ")
    List<Object[]> findOrderAmountAndOrderCountToday(Store store);
}
