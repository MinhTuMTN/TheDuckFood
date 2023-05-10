package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.ActivityProfileBinding;
import com.theduckfood.merchant.model.Store;
import com.theduckfood.merchant.util.Constant;

import java.text.DecimalFormat;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                changeClickable(false);
                binding.btnChinhSua.setText("Chỉnh sửa");
            } else  {
                changeClickable(true);
                binding.btnChinhSua.setText("Lưu");
            }
        });
    }
}