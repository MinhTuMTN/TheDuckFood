package com.theduckfood.repositories;

import com.theduckfood.entity.Review;
import com.theduckfood.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
   List<Review> findReviewsByOrder_Store(Store order_store);
}
