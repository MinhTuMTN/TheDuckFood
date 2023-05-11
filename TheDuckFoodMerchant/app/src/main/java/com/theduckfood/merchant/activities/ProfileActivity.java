package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.ActivityProfileBinding;
import com.theduckfood.merchant.model.Store;
import com.theduckfood.merchant.model.request.StoreUpdateInfoRequest;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.presenter.ProfilePresenter;
import com.theduckfood.merchant.presenter.contact.IProfileView;
import com.theduckfood.merchant.util.Constant;

import java.text.DecimalFormat;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity implements IProfileView {
    ActivityProfileBinding binding;
    ProfilePresenter profilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        profilePresenter = new ProfilePresenter(this, this);
        changeClickable(false);
        addInfo();
        addEvent();
    }

    private void addInfo() {
        Store store = (Store) getIntent().getSerializableExtra("store");
        if (store != null) {
            binding.txtTenQuan.setText(store.getStoreName());
            binding.ratingBar.setRating(store.getRate());

            DecimalFormat df = new DecimalFormat("#.#");
            String formatted = df.format(store.getRate()) + "/5.0";
            binding.textView8.setText(formatted);

            String urlImage = store.getAvatar();
            if (! urlImage.startsWith("http"))
                urlImage = Constant.IMAGE_BASE_URL + store.getAvatar();
            Glide.with(this)
                    .load(urlImage)
                    .into(binding.imgAva);

            binding.editTenQuan.setText(store.getStoreName());
            binding.editSoDienThoai.setText(store.getPhone());
            binding.editDiaChi.setText(store.getAddress());
        }
    }

    private void changeClickable(boolean clickable) {
        binding.editDiaChi.setEnabled(clickable);
        binding.editTenQuan.setEnabled(clickable);
        binding.editSoDienThoai.setEnabled(clickable);
    }

    private void addEvent() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
        binding.btnChinhSua.setOnClickListener(v -> {
            if (binding.btnChinhSua.getText().equals("Lưu")) {
                String storeName = binding.editTenQuan.getText().toString().trim();
                String storePhone = binding.editSoDienThoai.getText().toString().trim();
                String storeAddress = binding.editDiaChi.getText().toString().trim();
    
                if (storeName.isEmpty() || storePhone.isEmpty() || storeAddress.isEmpty()) {
                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!storePhone.matches("0[0-9]{9}")) {
                    Toast.makeText(this, "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
                    return;
                }

                StoreUpdateInfoRequest storeUpdateInfoRequest = new StoreUpdateInfoRequest();
                storeUpdateInfoRequest.setStoreName(storeName);
                storeUpdateInfoRequest.setStorePhone(storePhone);
                storeUpdateInfoRequest.setStoreAddress(storeAddress);
                profilePresenter.updateProfile(storeUpdateInfoRequest);
                changeClickable(false);
                binding.btnChinhSua.setText("Chỉnh sửa");
            } else  {
                changeClickable(true);
                binding.btnChinhSua.setText("Lưu");
            }
        });
    }

    @Override
    public void updateProfile(SimpleMessageResponse simpleMessageResponse) {
        if (simpleMessageResponse == null || simpleMessageResponse.isError()) {
            Toast.makeText(this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("position", R.id.menu_account);
        startActivity(intent);
    }

}