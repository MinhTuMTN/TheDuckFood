package com.theduckfood.merchant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.merchant.databinding.ItemChiTietCouponBinding;
import com.theduckfood.merchant.model.Coupon;
import com.theduckfood.merchant.util.DateTimeUtil;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponViewHolder> {
    Context context;
    List<Coupon> coupons;

    public CouponAdapter(Context context, List<Coupon> coupons) {
        this.context = context;
        this.coupons = coupons;
    }

    @NonNull
    @Override
    public CouponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CouponViewHolder(ItemChiTietCouponBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull CouponViewHolder holder, int position) {
        Coupon coupon = coupons.get(position);
        holder.binding.txtTenCoupon.setText(coupon.getCouponCode());

        String phanTramGiam = "Giảm " + (int)(coupon.getDiscount()*100) + "% trên tổng hóa đơn";
        holder.binding.txtPhanTramGiam.setText(phanTramGiam);

        String giamToiDa = "Giảm tối đa " + DateTimeUtil.formatCurrency(String.valueOf(coupon.getMaxDiscount())) + "đ";
        holder.binding.txtGiamToiTa.setText(giamToiDa);

        String donToiThieu = "Đơn tối thiểu " + DateTimeUtil.formatCurrency(String.valueOf(coupon.getMinPrice())) + "đ";
        holder.binding.txtDonToiThieu.setText(donToiThieu);

        String hanDung = DateTimeUtil.formatDate(coupon.getStartAt()) + " - " + DateTimeUtil.formatDate(coupon.getExpiredAt());
        holder.binding.txtHanDungCoupon.setText(hanDung);

        holder.binding.txtSoLuongCoupon.setText(String.valueOf(coupon.getAmount()));
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    static class CouponViewHolder extends RecyclerView.ViewHolder {
        ItemChiTietCouponBinding binding;

        public CouponViewHolder(ItemChiTietCouponBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
