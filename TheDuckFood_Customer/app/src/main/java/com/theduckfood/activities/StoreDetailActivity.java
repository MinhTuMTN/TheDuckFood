package com.theduckfood.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.theduckfood.adapter.FoodListAdapter;
import com.theduckfood.databinding.ActivityShopDetailBinding;
import com.theduckfood.model.Food;
import com.theduckfood.model.respone.StoreDetailResponse;
import com.theduckfood.presenter.StoreDetailPresenter;
import com.theduckfood.presenter.contact.IStoreDetailView;

import java.util.ArrayList;
import java.util.List;

public class StoreDetailActivity extends AppCompatActivity implements IStoreDetailView {
    ActivityShopDetailBinding binding;
    List<Food> foods;
    FoodListAdapter foodListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StoreDetailPresenter storeDetailPresenter = new StoreDetailPresenter(this, StoreDetailActivity.this);

        loadStoreDetail(storeDetailPresenter);
}

    private void addEvents() {

    }

    private void loadStoreDetail(StoreDetailPresenter storeDetailPresenter) {
        Long storeId = Long.valueOf(5);
        storeDetailPresenter.getStoreDetail(storeId);
    }

    private void getFoods(List<Food> foods) {
        foodListAdapter = new FoodListAdapter(StoreDetailActivity.this, foods);
        binding.recyclerFoods.setAdapter(foodListAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(StoreDetailActivity.this, 2);
        binding.recyclerFoods.setLayoutManager(gridLayoutManager);
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
    @Override
    public void getStoreDetail(StoreDetailResponse storeDetailResponse) {
        if (storeDetailResponse == null) {
            Toast.makeText(this, "Lỗi! Không thể lấy thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }
        Glide.with(StoreDetailActivity.this)
                .load(storeDetailResponse.getStore().getAvatar())
                .into(binding.imgAvatar);
        binding.txtName.setText(storeDetailResponse.getStore().getStoreName());
        binding.txtPhone.setText(storeDetailResponse.getStore().getPhone());
        binding.txtAddress.setText(storeDetailResponse.getStore().getAddress());
        binding.txtReviewCount.setText(storeDetailResponse.getStore().getReviewCount().toString() + " đánh giá");
        binding.txtRate.setText(String.valueOf(round(storeDetailResponse.getStore().getRate(), 1)));
        getFoods(storeDetailResponse.getFoods());
    }
}
