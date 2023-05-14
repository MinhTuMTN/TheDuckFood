package com.theduckfood.repositories;

import com.theduckfood.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoresByStoreNameContainingIgnoreCaseAndStatusNotContains(
            String storeName,
            String status,
            Pageable pageable);
    Store getStoreByStoreIdAndStatusNotContains(Long storeId, String status);
    List<Store> getStoresByStatusNotContaining(String status, Pageable pageable);

    @Query("SELECT s FROM Store s " +
            "WHERE s.status NOT LIKE %:status% " +
            "AND LOWER(s.storeName) LIKE %:keyword%")
    Page<Store> findStoresByKeywordAndStatusNotContains(
            @Param("keyword") String keyword,
            @Param("status") String status,
            Pageable pageable
    );

}
