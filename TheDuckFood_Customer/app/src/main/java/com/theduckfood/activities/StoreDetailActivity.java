package com.theduckfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.theduckfood.adapter.FoodListAdapter;
import com.theduckfood.databinding.ActivityShopDetailBinding;
import com.theduckfood.model.Food;
import com.theduckfood.model.Store;
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
    StoreDetailPresenter storeDetailPresenter;
    Long storeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storeDetailPresenter = new StoreDetailPresenter(this, StoreDetailActivity.this);
        addEvents();

    }

    private void addEvents() {
        loadStoreDetail();
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.chkFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> chkFavoriteClick());
    }

    private void loadStoreDetail() {
        storeId = getIntent().getLongExtra("store", 18L);
        storeDetailPresenter.getStoreDetail(storeId);
    }

    private void getFoods(List<Food> foods, Store store) {
        foodListAdapter = new FoodListAdapter(StoreDetailActivity.this, foods, store);
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

        if (storeDetailResponse.getStore().getStatus().equals(Constant.STORE_STATUS_OPENING)) {
            binding.layoutClosed.setVisibility(View.GONE);
            binding.recyclerFoods.setVisibility(View.VISIBLE);
            getFoods(storeDetailResponse.getFoods(), storeDetailResponse.getStore());
        } else {
            binding.layoutClosed.setVisibility(View.VISIBLE);
            binding.recyclerFoods.setVisibility(View.GONE);
        }
    }

    private void chkFavoriteClick() {
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
