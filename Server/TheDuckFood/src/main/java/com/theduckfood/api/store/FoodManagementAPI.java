package com.theduckfood.api.store;

import com.theduckfood.api.FileAPI;
import com.theduckfood.entity.Food;
import com.theduckfood.entity.Store;
import com.theduckfood.entity.StoreAccount;
import com.theduckfood.model.response.FoodDetailsResponse;
import com.theduckfood.model.response.SimpleMessageResponse;
import com.theduckfood.model.response.StoreGetAllFoodsResponse;
import com.theduckfood.repositories.FoodRepository;
import com.theduckfood.repositories.StoreAccountRepository;
import com.theduckfood.repositories.StoreRepository;
import com.theduckfood.util.Constants;
import com.theduckfood.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/merchant/food")
public class FoodManagementAPI {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    StoreAccountRepository storeAccountRepository;

    @Autowired
    FoodRepository foodRepository;

    @PostMapping("/add-food")
    public ResponseEntity<SimpleMessageResponse> addFood(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("foodName") String foodName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("price") String foodPrice,
            @RequestParam("image")MultipartFile image
            ) {
        try {
            float price = Float.parseFloat(foodPrice);
            if (price < 0 )
                throw new Exception();

            Store store = getStoreFromToken(bearerToken);

            Food food = new Food();
            food.setFoodName(foodName);
            food.setDescription(description);
            food.setPrice(price);
            food.setPricePromotion(price);
            food.setStore(store);

            String imageName = FileAPI.uploadImage(image);
            if(imageName == null)
                throw new Exception();
            food.setImage(imageName);

            foodRepository.save(food);
            return ResponseEntity.ok(new SimpleMessageResponse(false, "Thêm món ăn thành công"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new SimpleMessageResponse(
                            true,
                            "Đã có lỗi xảy ra"));
        }
    }

    @PostMapping("/update-food")
    public ResponseEntity<SimpleMessageResponse> updateFood(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("foodId") Long foodId,
            @RequestParam("foodName") String foodName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("price") float price,
            @RequestParam(value = "image", required = false)MultipartFile image
    ) {
        try {
            if (price < 0 )
                throw new Exception();

            Store store = getStoreFromToken(bearerToken);

            Food food = foodRepository.getFoodByFoodIdAndStoreAndStatusNotContaining(
                    foodId,
                    store,
                    Constants.FOOD_STATUS_DELETED
            );
            food.setFoodName(foodName);
            food.setPrice(price);
            food.setDescription(description);
            food.setPricePromotion(price);

            if (image != null) {
                String imageName = FileAPI.uploadImage(image);
                if(imageName == null)
                    throw new Exception();
                food.setImage(imageName);
            }

            foodRepository.save(food);
            return ResponseEntity.ok(new SimpleMessageResponse(false, "Cập nhật món ăn thành công"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new SimpleMessageResponse(
                            true,
                            "Đã có lỗi xảy ra"));
        }
    }

    @GetMapping("/details")
    public ResponseEntity<FoodDetailsResponse> getFoodDetails(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("foodId") Long foodId) {
        try {
            Store store = getStoreFromToken(bearerToken);

            Food food = foodRepository.getFoodByFoodIdAndStoreAndStatusNotContaining(
                    foodId,
                    store,
                    Constants.FOOD_STATUS_DELETED
            );

            if (food == null)
                throw new Exception();

            return ResponseEntity.ok(new FoodDetailsResponse(
                    false,
                    "Thành công",
                    food
            ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new FoodDetailsResponse(
                            true,
                            "Đã có lỗi xảy ra",
                            null));
        }

    }


    @GetMapping("/update-status")
    public ResponseEntity<SimpleMessageResponse> updateFoodStatus(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam("foodId") Long foodId,
            @RequestParam("status") String status) {
        try {
            if (!status.equals(Constants.FOOD_STATUS_DELETED)
                    && !status.equals(Constants.FOOD_STATUS_SELLING)
                    && !status.equals(Constants.FOOD_STATUS_SOLD_OUT)
            ) {
                return ResponseEntity
                        .status(400)
                        .body(new SimpleMessageResponse(
                                true,
                                "Trạng thái món ăn không hợp lệ"
                        ));
            }

            Store store = getStoreFromToken(bearerToken);

            Food food = foodRepository.getFoodByFoodIdAndStoreAndStatusNotContaining(
                    foodId,
                    store,
                    Constants.FOOD_STATUS_DELETED
            );

            if (food == null)
                throw new Exception();

            food.setStatus(status);
            foodRepository.save(food);

            return ResponseEntity.ok(new SimpleMessageResponse(
                    false,
                    "Thành công"
            ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new SimpleMessageResponse(
                            true,
                            "Đã có lỗi xảy ra"));
        }

    }

    @GetMapping
    public ResponseEntity<StoreGetAllFoodsResponse> getAllFood(
            @RequestHeader("Authorization") String bearerToken,
            @RequestParam(value = "status", required = false) String status
    ) {
        try {
            Store store = getStoreFromToken(bearerToken);
            List<Food> foods;
            if (status!= null && status.equals(Constants.FOOD_STATUS_SOLD_OUT))
                foods = foodRepository
                        .getFoodsByStoreAndStatus(store, Constants.FOOD_STATUS_SOLD_OUT);
            else if (status!= null && status.equals(Constants.FOOD_STATUS_SELLING)) {
                foods = foodRepository
                        .getFoodsByStoreAndStatus(store, Constants.FOOD_STATUS_SELLING);
            } else {
                foods = foodRepository
                        .getFoodsByStoreAndStatusNotContaining(store, Constants.FOOD_STATUS_DELETED);
            }

            return ResponseEntity.ok(new StoreGetAllFoodsResponse(
                    false,
                    "Thành công",
                    foods
            ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(new StoreGetAllFoodsResponse(
                            true,
                            "Đã có lỗi xảy ra",
                            null
                    ));
        }
    }

    private Store getStoreFromToken(String bearerToken) {
        String email = Objects.requireNonNull(JWTUtil.getPayloadFromJWTToken(bearerToken)).get("email").toString();
        StoreAccount storeAccount = storeAccountRepository.findStoreAccountByEmailAndStatusNotContaining(
                email,
                Constants.STORE_STATUS_DELETED);
        if (storeAccount == null)
            return null;

        return storeAccount.getStore();
    }
}
