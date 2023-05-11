package com.theduckfood.merchant.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.ActivityEditFoodBinding;
import com.theduckfood.merchant.model.Food;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.presenter.EditFoodPresenter;
import com.theduckfood.merchant.presenter.contact.IEditFoodView;
import com.theduckfood.merchant.util.Constant;

import java.text.NumberFormat;
import java.util.Locale;

public class EditFoodActivity extends AppCompatActivity implements IEditFoodView {
    ActivityEditFoodBinding binding;
    EditFoodPresenter editFoodPresenter;
    Uri imageUri;
    Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addEvents();
        loadInfo();
        editFoodPresenter = new EditFoodPresenter(this, this);
    }

    private void addEvents() {
        binding.btnThemHinhAnh.setOnClickListener(this::pickImage);
        binding.btnSuaMon.setOnClickListener(this::editFood);
        binding.editGiaTien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable view) {
                binding.editGiaTien.removeTextChangedListener(this);
                String s = binding.editGiaTien.getText().toString().replace(",", "");
                try {
                    NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
                    String price = formatter.format(Float.parseFloat(s));
                    binding.editGiaTien.setText(price);
                    binding.editGiaTien.setSelection(price.length());
                } catch (NumberFormatException ignored) {

                }
                binding.editGiaTien.addTextChangedListener(this);
            }
        });
    }

    private void loadInfo() {
        Intent intent = getIntent();
        Food food = (Food) intent.getSerializableExtra("food");
        this.food = food;

        String urlImage = food.getImage().startsWith("http") ? food.getImage() : Constant.IMAGE_BASE_URL + food.getImage();
        Glide.with(this)
                .load(urlImage)
                .into(binding.imgUpLoadAva);

        binding.editTenMon.setText(food.getFoodName());
        binding.editMoTa.setText(food.getDescription());
        binding.editGiaTien.setText(String.valueOf(food.getPricePromotion()));
    }

    private void editFood(View view) {
        String foodName = binding.editTenMon.getText().toString().trim();
        String description = binding.editMoTa.getText().toString().trim();
        String price = binding.editGiaTien.getText().toString().trim().replace(",", "");

        if (foodName.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        float foodPrice;
        try {
            foodPrice = Float.parseFloat(price);
            if (foodPrice < 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Giá món ăn không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        editFoodPresenter.editFood(food.getFoodId(), foodName, description, foodPrice, imageUri);
    }

    private void pickImage(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_MEDIA_IMAGES,
                                Manifest.permission.READ_MEDIA_AUDIO,
                                Manifest.permission.READ_MEDIA_VIDEO
                        },
                        101);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        101);
            }
        }

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001) {
            assert data != null;

            this.imageUri = data.getData();
            Glide.with(EditFoodActivity.this)
                    .load(imageUri)
                    .into(binding.imgUpLoadAva);
        }
    }

    @Override
    public void editFoodResponse(SimpleMessageResponse simpleMessageResponse) {
        if (simpleMessageResponse == null || simpleMessageResponse.isError()) {
            Toast.makeText(this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Cập nhật món ăn thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("position", R.id.menu_menu);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}