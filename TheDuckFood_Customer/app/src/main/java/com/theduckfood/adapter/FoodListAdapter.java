package com.theduckfood.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.R;

import com.theduckfood.databinding.FoodItemBinding;
import com.theduckfood.databinding.PopupFoodDetailBinding;
import com.theduckfood.model.Food;
import com.theduckfood.model.respone.FoodDetailResponse;
import com.theduckfood.presenter.contact.IGetFoodDetailView;
import com.theduckfood.util.Constant;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> implements IGetFoodDetailView {
    FoodItemBinding foodItemBinding;
    PopupFoodDetailBinding foodDetailBinding;
    private Activity context;
    private List<Food> foods;

    public FoodListAdapter(Activity context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        foodItemBinding = FoodItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new FoodListViewHolder(foodItemBinding);
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
        holder.itemView.setOnClickListener(v -> showPopUpFoodDetail());

    }

    public void showPopUpFoodDetail() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        foodDetailBinding = PopupFoodDetailBinding.inflate(LayoutInflater.from(context));

        View view = inflater.inflate(R.layout.popup_food_detail, null);

        final Dialog popUpFoodDetail = new Dialog(
                view.getContext(),
                R.style.MaterialDialogSheet);
        popUpFoodDetail.setContentView(view);
        popUpFoodDetail.setCancelable(true);
        popUpFoodDetail.getWindow().setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popUpFoodDetail.getWindow().setGravity(Gravity.BOTTOM);
        popUpFoodDetail.show();
    }
    @Override
    public int getItemCount() {
        return foods.size();
    }

    @Override
    public void getFoodDetail(FoodDetailResponse foodDetailResponse) {
        if(foodDetailResponse == null){
            Toast.makeText(context, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
        }
        String urlImage = foodDetailResponse.getFood().getImage().startsWith("http") ? foodDetailResponse.getFood().getImage() : Constant.IMAGE_BASE_URL + foodDetailResponse.getFood().getImage();
        Glide.with(context)
                .load(urlImage)
                .into(foodDetailBinding.imgAvatarFood);
        foodDetailBinding.txtFoodName.setText(foodDetailResponse.getFood().getFoodName());
        foodDetailBinding.txtPrice.setText(Math.round(foodDetailResponse.getFood().getPrice()) + "VNĐ");
        foodDetailBinding.txtDesc.setText(foodDetailResponse.getFood().getDescription());
    }

    public class FoodListViewHolder extends RecyclerView.ViewHolder {
        FoodItemBinding binding;

        public FoodListViewHolder(FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
