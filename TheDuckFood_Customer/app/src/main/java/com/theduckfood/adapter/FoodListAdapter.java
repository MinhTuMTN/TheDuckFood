package com.theduckfood.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.R;
import com.theduckfood.model.Food;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    private Activity context;
    private List<Food> foods;

    public FoodListAdapter(Activity context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodListAdapter.FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = context.getLayoutInflater().inflate(R.layout.food_item, parent, false);
        return new FoodListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.FoodListViewHolder holder, int position) {
        Food food = foods.get(position);

        Glide.with(context).load(food.getImage()).into(holder.imgAvatar);
        holder.txtFoodName.setText(food.getFoodName());
        holder.txtPrice.setText(food.getPrice().toString() + " VNĐ");


    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class FoodListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView txtFoodName;
        TextView txtPrice;

        public FoodListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
