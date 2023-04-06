package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.theduckfood.R;
import com.theduckfood.databinding.ActivityMainBinding;
import com.theduckfood.fragments.ProfileFragment;
import com.theduckfood.services.FirebaseCloudMessagingService;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();

    }

    private void addEvents() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    break;
                default:
                    replaceFragment(ProfileFragment.newInstance());
                    break;
            }
            return true;
        });
    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.fragmentContainer.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void updateFCMToken(String fcmToken) {
        Log.d("FCM", fcmToken);
    }


}