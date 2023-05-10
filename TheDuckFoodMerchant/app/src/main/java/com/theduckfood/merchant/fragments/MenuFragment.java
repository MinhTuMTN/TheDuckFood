package com.theduckfood.merchant.fragments;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.adapter.FoodAdapter;
import com.theduckfood.merchant.databinding.FragmentMenuBinding;
import com.theduckfood.merchant.model.Food;
import com.theduckfood.merchant.model.response.StoreGetAllFoodsResponse;
import com.theduckfood.merchant.presenter.MenuPresenter;
import com.theduckfood.merchant.presenter.contact.IMenuView;
import com.theduckfood.merchant.util.Constant;

import java.util.List;
import java.util.Objects;

public class MenuFragment extends Fragment implements IMenuView {
    private static MenuFragment instance;
    private FragmentMenuBinding binding;

    MenuPresenter menuPresenter;
    FoodAdapter foodAdapter;
    Dialog dialog;

    public static MenuFragment newInstance() {
        return instance == null ? new MenuFragment() : instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDialog();
        addListener();

        menuPresenter = new MenuPresenter(this, getContext());

        foodAdapter = new FoodAdapter(getContext(), menuPresenter);
        binding.listThucAn.setAdapter(foodAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.listThucAn.setLayoutManager(linearLayoutManager);

        menuPresenter.getListFoods(null);
    }

    private void addListener() {
        binding.btnDaDong.setOnClickListener(v -> {
            resetButton();
            binding.txtDaDong.setTextColor(requireContext().getColor(R.color.coral));
            menuPresenter.getListFoods(Constant.FOOD_STATUS_SOLD_OUT);
        });
        binding.btnTatCa.setOnClickListener(v -> {
            resetButton();
            binding.txtTatCa.setTextColor(requireContext().getColor(R.color.coral));
            menuPresenter.getListFoods(null);
        });
        binding.btnDangMo.setOnClickListener(v -> {
            resetButton();
            binding.txtDangMo.setTextColor(requireContext().getColor(R.color.coral));
            menuPresenter.getListFoods(Constant.FOOD_STATUS_SELLING);
        });
    }

    private void resetButton() {
        binding.txtDaDong.setTextColor(Color.BLACK);
        binding.txtTatCa.setTextColor(Color.BLACK);
        binding.txtDangMo.setTextColor(Color.BLACK);
    }

    private void initDialog() {
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public void loadFoods(StoreGetAllFoodsResponse foodsResponse) {
        if (foodsResponse == null || foodsResponse.getFoods() == null) {
            Toast.makeText(getContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }

        List<Food> foods = foodsResponse.getFoods();
        if (foods.size() == 0) {
            Toast.makeText(getContext(), "Cửa hàng hiện chưa có món ăn nào", Toast.LENGTH_SHORT).show();
        }

        foodAdapter.setFoods(foods);
    }

    @Override
    public void loading(boolean isLoading) {
        if (isLoading)
            dialog.show();
        else
            dialog.dismiss();
    }
}