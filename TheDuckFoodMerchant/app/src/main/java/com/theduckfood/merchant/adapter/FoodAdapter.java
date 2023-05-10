package com.theduckfood.merchant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.merchant.databinding.ItemMonAnBinding;
import com.theduckfood.merchant.model.Food;
import com.theduckfood.merchant.util.Constant;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    Context context;
    List<Food> foods;

    public FoodAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodViewHolder(
                ItemMonAnBinding
                        .inflate(
                                LayoutInflater.from(context),
                                parent,
                                false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foods.get(position);

        holder.binding.txtTenMonAn.setText(food.getFoodName());
        holder.binding.swtTrangThaiMonAn.setChecked(food.getStatus().equals(Constant.FOOD_STATUS_SELLING));

        NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));

        String price = formatter.format(food.getPricePromotion()) + " đ";
        holder.binding.txtGiaTien.setText(price);

        String urlImage = food.getImage().startsWith("http") ? food.getImage() : Constant.IMAGE_BASE_URL + food.getImage();
        Glide.with(context)
                .load(urlImage)
                .into(holder.binding.imgMonAn);
    }

    @Override
    public int getItemCount() {
        return foods == null ? 0 :foods.size();
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
        notifyDataSetChanged();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ItemMonAnBinding binding;

        public FoodViewHolder(ItemMonAnBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
