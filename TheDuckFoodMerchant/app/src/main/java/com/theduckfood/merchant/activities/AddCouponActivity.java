package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.ActivityAddCouponBinding;
import com.theduckfood.merchant.util.DateTimeUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class AddCouponActivity extends AppCompatActivity {

    ActivityAddCouponBinding binding;
    DatePickerDialog.OnDateSetListener dateSetListener1, dateSetListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCouponBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
        addListener();
    }

    private void initialize() {
        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                binding.txtNgayBatDau.setText(date);
            }
        };
        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                binding.txtNgayKetThuc.setText(date);
            }
        };
    }

    private void addListener() {
        binding.cardNgayBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddCouponActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener1,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        binding.cardNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddCouponActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener2,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        binding.btnTaoCoupon.setOnClickListener(this::createCoupon);
    }

    private void createCoupon(View view) {
        String couponCode = binding.edtMaCoupon.getText().toString().trim();
        if (couponCode.isEmpty()) {
            binding.edtMaCoupon.setError("Vui lòng nhập mã giảm giá");
            return;
        }
        for (int i = 0; i < couponCode.length(); i++) {
            if (!Character.isLetterOrDigit(couponCode.charAt(i))) {
                binding.edtMaCoupon.setError("Mã giảm giá không hợp lệ");
                return;
            }
        }

        int percent = 0;
        try {
            percent = Integer.parseInt(binding.edtPhanTramGiam.getText().toString().trim());

            if (percent < 5 || percent > 50) {
                binding.edtPhanTramGiam.setError("Phần trăm giảm không hợp lệ (5% - 50%)");
                return;
            }
        } catch (Exception e) {
            binding.edtPhanTramGiam.setError("Vui lòng nhập đúng định dạng số");
            return;
        }

        double maxDiscount = 0D;
        try {
            maxDiscount = Double.parseDouble(binding.edtGiamToiDa.getText().toString().trim());

            if (maxDiscount < 0) {
                binding.edtGiamToiDa.setError("Giá trị khuyến mãi tối đa không hợp lệ");
                return;
            }
        } catch (Exception e) {
            binding.edtGiamToiDa.setError("Vui lòng nhập đúng định dạng số");
            return;
        }

        double minPrice = 0D;
        try {
            minPrice = Double.parseDouble(binding.edtDonHangToiThieu.getText().toString().trim());

            if (minPrice < 0) {
                binding.edtDonHangToiThieu.setError("Giá trị đơn tối thiểu không hợp lệ");
                return;
            }
        } catch (Exception e) {
            binding.edtDonHangToiThieu.setError("Vui lòng nhập đúng định dạng số");
            return;
        }

        int amount = 0;
        try {
            amount = Integer.parseInt(binding.edtGioiHan.getText().toString().trim());

            if (amount < 0) {
                binding.edtGioiHan.setError("Số lượt khuyến mãi không hợp lệ");
                return;
            }
        } catch (Exception e) {
            binding.edtGioiHan.setError("Vui lòng nhập đúng định dạng số");
            return;
        }

        Date startAt;
        Date endAt;
        try {
            startAt = DateTimeUtil.convertStringToDate(binding.txtNgayBatDau.getText().toString().trim());
        } catch (ParseException e) {
            Toast.makeText(this,"Vui lòng chọn ngày bắt đầu", Toast.LENGTH_SHORT).show();
            binding.cardNgayBatDau.performClick();
            return;
        }

        try {
            endAt = DateTimeUtil.convertStringToDate(binding.txtNgayKetThuc.getText().toString().trim());
        } catch (ParseException e) {
            Toast.makeText(this,"Vui lòng chọn ngày kết thúc", Toast.LENGTH_SHORT).show();
            binding.cardNgayKetThuc.performClick();
            return;
        }
        
        if (startAt.compareTo(endAt) > 0) {
            Toast.makeText(this, "Ngày bắt đầu phải trước ngày kết thúc ít nhất 1 ngày", Toast.LENGTH_SHORT).show();
            binding.cardNgayBatDau.performClick();
            return;
        }

    }
}