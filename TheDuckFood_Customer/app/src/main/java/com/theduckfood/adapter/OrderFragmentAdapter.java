package com.theduckfood.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.theduckfood.fragments.OrderCurrentFragment;
import com.theduckfood.fragments.OrderHistoryFragment;

public class OrderFragmentAdapter extends FragmentStateAdapter {
    public OrderFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new OrderCurrentFragment();
            default:
                return new OrderHistoryFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
