package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.ActivityMainBinding;
import com.theduckfood.merchant.fragments.AccountFragment;
import com.theduckfood.merchant.fragments.HomeFragment;
import com.theduckfood.merchant.fragments.MenuFragment;
import com.theduckfood.merchant.fragments.OrdersFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        addEvents();
        loadFragment();
//        getAndUpdateFCMToken();
    }

    private void loadFragment() {
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("position", 0);
            if (id != 0) {
                changeBottomBar(id);
                return;
            }
        }
        changeBottomBar(R.id.menu_home);
    }

    private void addEvents() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    replaceFragment(HomeFragment.newInstance());
                    break;
                case R.id.menu_orders:
                    replaceFragment(OrdersFragment.newInstance());
                    break;
                case R.id.menu_menu:
                    replaceFragment(MenuFragment.newInstance());
                    break;
                default:
                    replaceFragment(AccountFragment.newInstance());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.fragmentContainer.getId(), fragment);
        fragmentTransaction.commit();
    }


    public void changeBottomBar(int itemId) {
        binding.bottomNavigationView.setSelectedItemId(itemId);
    }


//    private void getAndUpdateFCMToken() {
//        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateFCMToken);
//    }

//    private void updateFCMToken(String fcmToken) {
//        MainPresenter mainPresenter = new MainPresenter(this, this);
//        mainPresenter.updateFCMToken(fcmToken);
//    }
}