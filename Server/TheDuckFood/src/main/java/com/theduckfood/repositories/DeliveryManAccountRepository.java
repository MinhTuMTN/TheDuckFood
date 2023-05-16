package com.theduckfood.repositories;

import com.theduckfood.entity.DeliveryManAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeliveryManAccountRepository extends JpaRepository<DeliveryManAccount, String> {
    DeliveryManAccount findDeliveryManAccountByEmailAndPasswordAndStatus(
            String email,
            String password,
            String status
    );
}
