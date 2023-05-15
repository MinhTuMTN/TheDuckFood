package com.theduckfood.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.theduckfood.R;
import com.theduckfood.adapter.FoodSearchAdapter;
import com.theduckfood.adapter.StoreSearchAdapter;
import com.theduckfood.databinding.ActivitySearchBinding;
import com.theduckfood.model.respone.SearchResponse;
import com.theduckfood.presenter.SearchPresenter;
import com.theduckfood.presenter.contact.ISearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ISearchView {
    ActivitySearchBinding binding;
    SearchPresenter searchPresenter;
    FoodSearchAdapter foodSearchAdapter;
    StoreSearchAdapter storeSearchAdapter;
    int position = 0;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvent();
        initialize();
        getSearchParam();
    }

    private void getSearchParam() {
        String searchParam = getIntent().getStringExtra("search");
        if (searchParam != null) {
            binding.edtSearch.setText(searchParam);
            searchPresenter.search(searchParam);
        }
    }

    private void initialize() {
        initDialog();
        searchPresenter = new SearchPresenter(this, this);
        foodSearchAdapter = new FoodSearchAdapter(this, new ArrayList<>());
        storeSearchAdapter = new StoreSearchAdapter(this, new ArrayList<>());
        binding.listItem.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void addEvent() {
        binding.edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchParam = binding.edtSearch.getText().toString();
                    searchPresenter.search(searchParam);
                    return true;
                }
                return false;
            }
        });
        binding.btnFood.setOnClickListener(v -> {
            if (position == 0)
                return;

            binding.btnStore.setTextColor(ContextCompat.getColor(this, R.color.black));
            binding.btnFood.setTextColor(ContextCompat.getColor(this, R.color.coral));
            position = 0;
            if (foodSearchAdapter.hasContent()) {
                binding.listItem.setAdapter(foodSearchAdapter);
                binding.layoutNotFound.setVisibility(View.GONE);
                binding.layoutList.setVisibility(View.VISIBLE);
            } else {
                binding.layoutNotFound.setVisibility(View.VISIBLE);
                binding.layoutList.setVisibility(View.GONE);
            }
        });
        binding.btnStore.setOnClickListener(v -> {
            if (position == 1)
                return;

            binding.btnFood.setTextColor(ContextCompat.getColor(this, R.color.black));
            binding.btnStore.setTextColor(ContextCompat.getColor(this, R.color.coral));
            position = 1;
            if (storeSearchAdapter.hasContent()) {
                binding.listItem.setAdapter(storeSearchAdapter);
                binding.layoutNotFound.setVisibility(View.GONE);
                binding.layoutList.setVisibility(View.VISIBLE);
            } else {
                binding.layoutNotFound.setVisibility(View.VISIBLE);
                binding.layoutList.setVisibility(View.GONE);
            }
        });
        binding.btnBack4.setOnClickListener(v -> onBackPressed());
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    public void searchResponse(SearchResponse searchResponse) {
        loading(false);
        foodSearchAdapter.setFoodSearchResponses(searchResponse.getFoods());
        storeSearchAdapter.setStores(searchResponse.getStores());
        if (searchResponse.isError()
                || position == 0 && searchResponse.getFoods().size() == 0
                || position == 1 && searchResponse.getStores().size() == 0
        ) {
            binding.layoutNotFound.setVisibility(View.VISIBLE);
            binding.layoutList.setVisibility(View.GONE);
            return;
        }

        binding.layoutList.setVisibility(View.VISIBLE);
        binding.layoutNotFound.setVisibility(View.GONE);
        if (position == 0) {
            binding.listItem.setAdapter(foodSearchAdapter);
        } else
            binding.listItem.setAdapter(storeSearchAdapter);
    }

    @Override
    public void loading(boolean isLoading) {
        if (isLoading)
            dialog.show();
        else
            dialog.dismiss();
    }

    private void initDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }
}