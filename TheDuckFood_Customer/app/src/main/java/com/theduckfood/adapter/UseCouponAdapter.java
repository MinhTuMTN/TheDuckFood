package com.theduckfood.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.databinding.ItemCouponBinding;
import com.theduckfood.model.Coupon;
import com.theduckfood.util.DateTimeUtil;
import com.theduckfood.util.SharedPreferenceManager;

import java.text.SimpleDateFormat;
import java.util.List;

public class UseCouponAdapter extends RecyclerView.Adapter<UseCouponAdapter.UseCouponViewHolder> {
    ItemCouponBinding itemCouponBinding;
    Context context;
    List<Coupon> coupons;

    public UseCouponAdapter(Context context, List<Coupon> coupons) {
        this.context = context;
        this.coupons = coupons;
    }

    @NonNull
    @Override
    public UseCouponAdapter.UseCouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemCouponBinding = ItemCouponBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new UseCouponAdapter.UseCouponViewHolder(itemCouponBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UseCouponAdapter.UseCouponViewHolder holder, int position) {
        Coupon coupon = coupons.get(position);
        holder.itemCouponBinding.txtCouponCode.setText(coupon.getCouponCode());

        int discount = (int)(coupon.getDiscount()*100);
        String discountCoupon = "Giảm " + discount + "% trên tổng hoá đơn";
        holder.itemCouponBinding.txtPhanTramGiam.setText(discountCoupon);

        String maxDiscount = DateTimeUtil.formatCurrency(coupon.getMaxDiscount().toString());
        String maxDiscountCoupon = "Giảm tối đa " + maxDiscount + "đ";
        holder.itemCouponBinding.txtGiamToiTa.setText(maxDiscountCoupon);

        String minPrice = DateTimeUtil.formatCurrency(coupon.getMinPrice().toString());
        String minPriceCoupon = "Đơn tối thiểu " + minPrice + "đ";
        holder.itemCouponBinding.txtDonToiThieu.setText(minPriceCoupon);

        String startAt = DateTimeUtil.formatDate(coupon.getStartAt());
        String expiredAt = DateTimeUtil.formatDate(coupon.getExpiredAt());
        String hanSuDungCoupon = startAt + " - " + expiredAt;
        holder.itemCouponBinding.txtHanDungCoupon.setText(hanSuDungCoupon);

        holder.itemCouponBinding.btnSuDungCoupon.setOnClickListener(v -> selectCoupon(coupon));
    }

    private void selectCoupon(Coupon coupon) {
        Intent intent = new Intent();
        intent.putExtra("coupon", coupon);
        ((Activity)context).setResult(Activity.RESULT_OK, intent);
        ((Activity)context).finish();
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    public class UseCouponViewHolder extends RecyclerView.ViewHolder{
        ItemCouponBinding itemCouponBinding;

        public UseCouponViewHolder(ItemCouponBinding itemCouponBinding) {
            super(itemCouponBinding.getRoot());
            this.itemCouponBinding = itemCouponBinding;
        }
    }
}
