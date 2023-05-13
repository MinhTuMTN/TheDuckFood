package com.theduckfood.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;
import com.theduckfood.R;
import com.theduckfood.adapter.SliderAdapter;
import com.theduckfood.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
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
}