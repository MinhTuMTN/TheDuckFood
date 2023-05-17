package com.theduckfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.activities.StoreDetailActivity;
import com.theduckfood.databinding.ItemFoodSearchPageBinding;
import com.theduckfood.model.respone.FoodSearchResponse;
import com.theduckfood.util.Constant;

import java.text.DecimalFormat;
import java.util.List;

public class FoodSearchAdapter extends RecyclerView.Adapter<FoodSearchAdapter.FoodSearchViewHolder> {
    Context context;
    List<FoodSearchResponse> foodSearchResponses;

    public FoodSearchAdapter(Context context, List<FoodSearchResponse> foodSearchResponses) {
        this.context = context;
        this.foodSearchResponses = foodSearchResponses;
    }

    @NonNull
    @Override
    public FoodSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodSearchViewHolder(ItemFoodSearchPageBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodSearchViewHolder holder, int position) {
        FoodSearchResponse response = foodSearchResponses.get(position);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedNumber = decimalFormat.format(response.getRate());
        holder.binding.txtDanhGia.setText(formattedNumber);
        holder.binding.ratingBar.setRating((response.getRate()) / 5);

        String reviewCount = "(" + response.getReviewCount() + "+)";
        holder.binding.txtSoLuotDanhGia.setText(reviewCount);
        holder.binding.txtTenQuan.setText(response.getStoreName());
        holder.binding.txtTenMonAn.setText(response.getFood().getFoodName());

        String urlImage = response.getFood().getImage().startsWith("http")
                ? response.getFood().getImage()
                : Constant.IMAGE_BASE_URL + response.getFood().getImage();
        Glide.with(context)
                .load(urlImage)
                .into(holder.binding.imgMonAn);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoreDetailActivity.class);
            intent.putExtra("store", response.getStoreId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodSearchResponses.size();
    }

    public boolean hasContent() {
        return foodSearchResponses != null && foodSearchResponses.size() > 0;
    }

    public void setFoodSearchResponses(List<FoodSearchResponse> foodSearchResponses) {
        this.foodSearchResponses.clear();
        this.foodSearchResponses = foodSearchResponses;
        notifyDataSetChanged();
    }

    static class FoodSearchViewHolder extends RecyclerView.ViewHolder {
        ItemFoodSearchPageBinding binding;

        public FoodSearchViewHolder(ItemFoodSearchPageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
