package com.theduckfood.api.user;

import com.theduckfood.entity.Food;
import com.theduckfood.model.response.FoodDetailsResponse;
import com.theduckfood.model.response.SearchResponse;
import com.theduckfood.repositories.FoodRepository;
import com.theduckfood.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/foods")
public class FoodAPI {
    @Autowired
    FoodRepository foodRepository;
    @GetMapping
    public ResponseEntity<FoodDetailsResponse> search(
            @RequestParam(value = "foodId", required = true) Long foodId) {
        Food food = foodRepository.getFoodByFoodIdAndStatus(
                foodId,
                Constants.FOOD_STATUS_SELLING);
        if (food != null)
            return  ResponseEntity.ok(new FoodDetailsResponse(
                    false,
                    "Thành công",
                    food));
        return ResponseEntity.status(404).body(new FoodDetailsResponse(
                true,
                "Không tìm thấy món ăn",
                null
        ));
    }
}
