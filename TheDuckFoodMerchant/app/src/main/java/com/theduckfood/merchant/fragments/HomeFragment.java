package com.theduckfood.merchant.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;
import com.theduckfood.merchant.R;
import com.theduckfood.merchant.activities.MainActivity;
import com.theduckfood.merchant.adapter.SliderAdapter;
import com.theduckfood.merchant.databinding.FragmentHomeBinding;
import com.theduckfood.merchant.model.Store;
import com.theduckfood.merchant.model.response.GetStoreProfileResponse;
import com.theduckfood.merchant.presenter.HomePresenter;
import com.theduckfood.merchant.presenter.contact.IHomeView;
import com.theduckfood.merchant.util.Constant;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment implements IHomeView {
    private static HomeFragment instance;
    private FragmentHomeBinding binding;
    Dialog dialog;
    HomePresenter homePresenter;

    public static HomeFragment newInstance() {
        return instance == null ? new HomeFragment() : instance;
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
        initDialog();
        addListener();

        homePresenter = new HomePresenter(this, getContext());
        homePresenter.loadProfile();
    }

    private void addListener() {
        binding.swtTrangThai.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.txtTrangThai.setText("Mở cửa");
                binding.txtMoTaTrangThai.setText("Tắt để tạm dừng các đơn hàng đến");
            } else {
                binding.txtTrangThai.setText("Đã đóng cửa");
                binding.txtMoTaTrangThai.setText("Bật để mở");
            }

            homePresenter.changeStatus(isChecked);
        });

        binding.btnDonHang.setOnClickListener(v -> {

        });
    }

    private void initDialog() {
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    private void handleSlider() {
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

    @Override
    public void loadProfile(GetStoreProfileResponse storeProfileResponse) {
        if (storeProfileResponse == null || storeProfileResponse.getStore() == null) {
            Toast.makeText(getContext(), "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
        Store store = storeProfileResponse.getStore();
        binding.txtTen.setText(store.getStoreName());
        binding.txtDiaChi.setText(store.getAddress());
        Glide.with(requireContext())
                .load(store.getAvatar())
                .into(binding.imgAva);

        boolean opening = store.getStatus().equals(Constant.STORE_STATUS_OPENING);
        binding.swtTrangThai.setChecked(opening);
        if (opening) {
            binding.txtTrangThai.setText("Mở cửa");
            binding.txtMoTaTrangThai.setText("Tắt để tạm dừng các đơn hàng đến");
        } else {
            binding.txtTrangThai.setText("Đã đóng cửa");
            binding.txtMoTaTrangThai.setText("Bật để mở");
        }

        binding.btnDonHang.setOnClickListener(v -> ((MainActivity) requireActivity()).changeBottomBar(R.id.menu_orders));
        binding.btnThucDon.setOnClickListener(v -> ((MainActivity) requireActivity()).changeBottomBar(R.id.menu_menu));
    }

    @Override
    public void isLoading(boolean isLoading) {
        if (isLoading)
            dialog.show();
        else
            dialog.dismiss();
    }

}