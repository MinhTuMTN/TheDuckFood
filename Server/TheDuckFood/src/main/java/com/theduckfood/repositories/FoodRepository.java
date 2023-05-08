package com.theduckfood.repositories;

import com.theduckfood.entity.Food;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findFoodsByFoodNameContainingIgnoreCaseAndStatus(String foodName, String status, Pageable pageable);
}
