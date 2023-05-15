package com.theduckfood.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;
import com.theduckfood.R;
import com.theduckfood.activities.SearchActivity;
import com.theduckfood.adapter.FoodRecommendAdapter;
import com.theduckfood.adapter.HomeStoreAdapter;
import com.theduckfood.adapter.SliderAdapter;
import com.theduckfood.databinding.FragmentHomeBinding;
import com.theduckfood.model.CartItem;
import com.theduckfood.model.Food;
import com.theduckfood.model.respone.FoodRecommend;
import com.theduckfood.model.respone.UserGetListStore;
import com.theduckfood.presenter.HomePresenter;
import com.theduckfood.presenter.contact.IHomeView;
import com.theduckfood.util.Constant;
import com.theduckfood.util.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeView {
    FragmentHomeBinding binding;


    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleSlider();
        handleFoodRecommend();
        handleStore();
        addListener();
    }

    private void addListener() {
        binding.cardView1.setOnClickListener(v ->
                startActivity(new Intent(getContext(), SearchActivity.class))
        );
    }

    private void handleStore() {
        HomePresenter homePresenter = new HomePresenter(this, getContext());
        homePresenter.getListStore("rate", "DESC", 0, 7);
        homePresenter.getListStore("date", "DESC", 0, 7);

    }

    private void handleFoodRecommend() {
        List<FoodRecommend> foodRecommendList = new ArrayList<>();
        foodRecommendList.add(new FoodRecommend(R.drawable.bubble_tea,"Trà sữa"));
        foodRecommendList.add(new FoodRecommend(R.drawable.fast_food,"Fast food"));
        foodRecommendList.add(new FoodRecommend(R.drawable.bread,"Bánh mì"));
        foodRecommendList.add(new FoodRecommend(R.drawable.pho,"Phở/Bún"));
        foodRecommendList.add(new FoodRecommend(R.drawable.rice_bowl,"Cơm Việt"));
        foodRecommendList.add(new FoodRecommend(R.drawable.basket,"Trái cây"));

        FoodRecommendAdapter foodRecommendAdapter = new FoodRecommendAdapter(getContext(),foodRecommendList);
        binding.recyclerFoods.setAdapter(foodRecommendAdapter);
        binding.recyclerFoods.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
//        binding.recyclerFoods.setHasFixedSize(true);
//        binding.recyclerFoods.setNestedScrollingEnabled(false);

    }

    private void handleSlider() {
        SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(getContext());
        String userName = sharedPreferenceManager.getStringValue(SharedPreferenceManager.USER_PROFILE_FULL_NAME_KEY) + "!";
        binding.txtTen.setText(userName);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.slider_1);
        arrayList.add(R.drawable.slider_2);
        arrayList.add(R.drawable.slider_4);
        arrayList.add(R.drawable.slider_5);

        SliderAdapter sliderAdapter = new SliderAdapter(getContext(), arrayList);
        binding.sliderView.setSliderAdapter(sliderAdapter);
        binding.sliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
        binding.sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        binding.sliderView.setIndicatorRadius(5);
        binding.sliderView.setIndicatorPadding(10);
        binding.sliderView.setIndicatorSelectedColor(Color.RED);
        binding.sliderView.setIndicatorUnselectedColor(Color.GRAY);
        binding.sliderView.startAutoCycle();
        binding.sliderView.setScrollTimeInSec(5);
    }

    private boolean isOk(UserGetListStore userGetListStore) {
        return userGetListStore != null && !userGetListStore.isError();
    }

    @Override
    public void getRecommendStores(UserGetListStore userGetListStore) {
        if (isOk(userGetListStore)) {
            HomeStoreAdapter ratingStoreAdapter = new HomeStoreAdapter(getContext(), userGetListStore.getStores());
            binding.recyclerQuanNgon.setAdapter(ratingStoreAdapter);
            binding.recyclerQuanNgon.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
    }

    @Override
    public void getNewestStores(UserGetListStore userGetListStore) {
        if (isOk(userGetListStore)) {
            HomeStoreAdapter newestStoreAdapter = new HomeStoreAdapter(getContext(), userGetListStore.getStores());
            binding.recyclerQuanMoiNhat.setAdapter(newestStoreAdapter);
            binding.recyclerQuanMoiNhat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
    }
}