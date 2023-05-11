package com.theduckfood.merchant.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewOrderAdapter extends FragmentStatePagerAdapter {
    public ViewOrderAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CurrentOrderFragment();
            case 1:
                return new DeliveryFragment();
            case 2:
                return new OrderCompletedFragment();
            default:
                return new OrderCancelledFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Hiện tại";
                break;
            case 1:
                title = "Đang giao";
                break;
            case 2:
                title = "Hoàn thành";
                break;
            default:
                title = "Đã huỷ";
                break;
        }
        return title;
    }
}
