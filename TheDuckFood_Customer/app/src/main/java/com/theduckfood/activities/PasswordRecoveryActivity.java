package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.theduckfood.databinding.ActivityPasswordRecoveryBinding;
import com.theduckfood.fragments.EnterEmailFragment;

public class PasswordRecoveryActivity extends AppCompatActivity {

    ActivityPasswordRecoveryBinding binding;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordRecoveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(binding.fragmentContainer.getId(), new EnterEmailFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0)
            fragmentManager.popBackStack();
        else
            super.onBackPressed();
    }
}