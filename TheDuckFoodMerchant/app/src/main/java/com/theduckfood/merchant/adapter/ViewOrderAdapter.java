package com.theduckfood.merchant.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.theduckfood.merchant.fragments.OrderCurrentFragment;
import com.theduckfood.merchant.fragments.OrderDeliveryFragment;
import com.theduckfood.merchant.fragments.OrderCancelledFragment;
import com.theduckfood.merchant.fragments.OrderCompletedFragment;

public class ViewOrderAdapter extends FragmentStateAdapter {
    public ViewOrderAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new OrderCurrentFragment();
            case 1:
                return new OrderDeliveryFragment();
            case 2:
                return new OrderCompletedFragment();
            default:
                return new OrderCancelledFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
