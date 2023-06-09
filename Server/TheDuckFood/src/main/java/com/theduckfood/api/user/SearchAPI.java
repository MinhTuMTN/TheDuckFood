package com.theduckfood.api.user;

import com.theduckfood.entity.Food;
import com.theduckfood.entity.Store;
import com.theduckfood.model.response.FoodSearchResponse;
import com.theduckfood.model.response.SearchResponse;
import com.theduckfood.repositories.FoodRepository;
import com.theduckfood.repositories.StoreRepository;
import com.theduckfood.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchAPI {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    StoreRepository storeRepository;
    @GetMapping
    public ResponseEntity<SearchResponse> search(
            @RequestParam(value = "q", required = true) String query,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit) {
        try {
            int pageNum = page != null ? page : 0;
            int size = limit != null ? limit : 5;
            Pageable pageable = PageRequest.of(pageNum, size);
            List<Food> foods = foodRepository
                    .findFoodsByFoodNameContainingIgnoreCaseAndStatus(
                            query,
                            Constants.FOOD_STATUS_SELLING,
                            pageable
                    );
            List<FoodSearchResponse> foodSearchResponses = new ArrayList<>();
            for (Food food : foods)
                foodSearchResponses.add(new FoodSearchResponse(
                        food,
                        food.getStore().getStoreId(),
                        food.getStore().getStoreName(),
                        food.getStore().getRate(),
                        food.getStore().getReviewCount()
                ));

            Page<Store> storePages = storeRepository.findStoresByKeywordAndStatusNotContains(
                    query,
                    Constants.STORE_STATUS_DELETED,
                    pageable
            );
            return ResponseEntity.ok(new SearchResponse(false,
                    "Thành công",
                    storePages != null && storePages.hasContent()
                    ? storePages.getContent()
                    : new ArrayList<>(),
                    foodSearchResponses));

        } catch (Exception e) {
            return  ResponseEntity.status(400).body(new SearchResponse(true,
                    "Đã có lỗi xảy ra",
                    null,
                    null));
        }
    }
}
