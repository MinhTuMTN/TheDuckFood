package com.theduckfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.theduckfood.adapter.FoodListAdapter;
import com.theduckfood.databinding.ActivityShopDetailBinding;
import com.theduckfood.model.Food;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.model.respone.StoreDetailResponse;
import com.theduckfood.presenter.StoreDetailPresenter;
import com.theduckfood.presenter.contact.IStoreDetailView;
import com.theduckfood.util.Constant;

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
        addEvents(storeDetailPresenter);

    }

    private void addEvents(StoreDetailPresenter storeDetailPresenter) {
        loadStoreDetail(storeDetailPresenter);
        binding.btnBack.setOnClickListener(v -> switchToMainActivity());
        binding.chkFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chkFavoriteClick(storeDetailPresenter);
            }
        });
    }

    private void loadStoreDetail(StoreDetailPresenter storeDetailPresenter) {
        Long storeId = getIntent().getLongExtra("store", 18L);
        storeDetailPresenter.getStoreDetail(storeId);
    }

    private void getFoods(List<Food> foods) {
        foodListAdapter = new FoodListAdapter(StoreDetailActivity.this, foods);
        binding.recyclerFoods.setAdapter(foodListAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StoreDetailActivity.this);
        binding.recyclerFoods.setLayoutManager(linearLayoutManager);
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
        String urlImage = storeDetailResponse.getStore().getAvatar().startsWith("http") ? storeDetailResponse.getStore().getAvatar() : Constant.IMAGE_BASE_URL + storeDetailResponse.getStore().getAvatar();
        Glide.with(StoreDetailActivity.this)
                .load(urlImage)
                .into(binding.imgAvatar);
        binding.txtName.setText(storeDetailResponse.getStore().getStoreName());
        binding.txtPhone.setText(storeDetailResponse.getStore().getPhone());
        binding.txtAddress.setText(storeDetailResponse.getStore().getAddress());
        String reviewCount = "(" + storeDetailResponse.getStore().getReviewCount() + "+)";
        binding.txtReviewCount.setText(reviewCount);
        binding.ratingBar.setRating(storeDetailResponse.getStore().getRate());
        binding.txtRate.setText(String.valueOf(round(storeDetailResponse.getStore().getRate(), 1)));

        getFoods(storeDetailResponse.getFoods());
    }

    private void chkFavoriteClick(StoreDetailPresenter storeDetailPresenter) {
        Long storeId = Long.valueOf(18);
        storeDetailPresenter.favorite(storeId);
    }

    @Override
    public void favorite(SimpleMessageResponse simpleMessageResponse) {
        if (simpleMessageResponse == null) {
            Toast.makeText(this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, simpleMessageResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void switchToMainActivity() {
        Intent intent = new Intent(StoreDetailActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
