package com.theduckfood.repositories;

import com.theduckfood.entity.StoreAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreAccountRepository extends JpaRepository<StoreAccount, String> {
    StoreAccount findStoreAccountByEmailAndPasswordAndStatusNotContaining(
            String email,
            String password,
            String status);


    StoreAccount findStoreAccountByEmailAndStatusNotContaining(String email, String status);
}
