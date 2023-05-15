package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.theduckfood.R;
import com.theduckfood.databinding.ActivityMainBinding;
import com.theduckfood.fragments.OrdersFragment;
import com.theduckfood.fragments.HomeFragment;
import com.theduckfood.fragments.ProfileFragment;
import com.theduckfood.presenter.MainPresenter;
import com.theduckfood.presenter.contact.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView {
    ActivityMainBinding binding;
    private static final int SEARCH_ACTIVITY_REQUEST_CODE = 1;
    int menuId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        replaceFragment(new HomeFragment());
        this.menuId = R.id.menu_home;
        getAndUpdateFCMToken();
        getData();
    }

    private void getData() {
        boolean switchToOrders = getIntent().getBooleanExtra("order", false);
        Log.d("MAIN", switchToOrders ? "true" : "false");
        if (switchToOrders) {
            this.menuId = R.id.menu_orders;
            binding.bottomNavigationView.setSelectedItemId(R.id.menu_orders);
        }
    }

    private void addEvents() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    this.menuId = R.id.menu_home;
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.menu_search:
                    Intent searchIntent = new Intent(this, SearchActivity.class);
                    startActivityForResult(searchIntent, SEARCH_ACTIVITY_REQUEST_CODE);
                    break;
                case R.id.menu_orders:
                    this.menuId = R.id.menu_orders;
                    replaceFragment(OrdersFragment.newInstance());
                    break;
                case R.id.menu_profile:
                    this.menuId = R.id.menu_profile;
                    replaceFragment(ProfileFragment.newInstance());
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            binding.bottomNavigationView.setSelectedItemId(menuId);
        }
    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.fragmentContainer.getId(), fragment);
        fragmentTransaction.commit();
    }


    private void getAndUpdateFCMToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateFCMToken);
    }

    private void updateFCMToken(String fcmToken) {
        MainPresenter mainPresenter = new MainPresenter(this, this);
        mainPresenter.updateFCMToken(fcmToken);
    }


}