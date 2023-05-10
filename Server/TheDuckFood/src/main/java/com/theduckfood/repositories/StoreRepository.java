package com.theduckfood.repositories;

import com.theduckfood.entity.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoresByStoreNameContainingIgnoreCaseAndStatusNotContains(
            String storeName,
            String status,
            Pageable pageable);
    Store getStoreByStoreIdAndStatusNotContains(Long storeId, String status);
}
