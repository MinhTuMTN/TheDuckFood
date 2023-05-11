package com.theduckfood.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.R;
import com.theduckfood.activities.StoreDetailActivity;
import com.theduckfood.databinding.ActivityShopDetailBinding;
import com.theduckfood.databinding.FoodItemBinding;
import com.theduckfood.model.Food;
import com.theduckfood.util.Constant;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    FoodItemBinding binding;
    private Activity context;
    private List<Food> foods;

    public FoodListAdapter(Activity context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FoodItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new FoodListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.FoodListViewHolder holder, int position) {
        Food food = foods.get(position);

        String urlImage = food.getImage().startsWith("http") ? food.getImage() : Constant.IMAGE_BASE_URL + food.getImage();
        Glide.with(context)
                .load(urlImage)
                .into(holder.binding.imgAvatarFood);
        holder.binding.txtFoodName.setText(food.getFoodName());
        holder.binding.txtPrice.setText(Math.round(food.getPrice()) + " VNĐ");


    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class FoodListViewHolder extends RecyclerView.ViewHolder {
        FoodItemBinding binding;
        ImageView imgAvatar;
        TextView txtFoodName;
        TextView txtPrice;

        public FoodListViewHolder(FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
