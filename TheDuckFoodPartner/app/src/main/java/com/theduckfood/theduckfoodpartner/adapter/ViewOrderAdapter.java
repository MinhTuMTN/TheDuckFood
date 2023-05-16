package com.theduckfood.theduckfoodpartner.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.theduckfood.theduckfoodpartner.fragment.OrderCurrentFragment;
import com.theduckfood.theduckfoodpartner.fragment.OrderHistoryFragment;
import com.theduckfood.theduckfoodpartner.fragment.OrderNewFragment;

public class ViewOrderAdapter extends FragmentStateAdapter {

    public ViewOrderAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new OrderNewFragment();
            case 1:
                return new OrderCurrentFragment();
            default:
                return new OrderHistoryFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }



}
