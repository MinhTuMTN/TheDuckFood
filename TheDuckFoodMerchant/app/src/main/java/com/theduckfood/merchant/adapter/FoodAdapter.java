package com.theduckfood.merchant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.merchant.R;
import com.theduckfood.merchant.activities.EditFoodActivity;
import com.theduckfood.merchant.databinding.ItemMonAnBinding;
import com.theduckfood.merchant.model.Food;
import com.theduckfood.merchant.presenter.HomePresenter;
import com.theduckfood.merchant.presenter.MenuPresenter;
import com.theduckfood.merchant.util.Constant;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    Context context;
    List<Food> foods;
    MenuPresenter menuPresenter;
    boolean first;

    public FoodAdapter(Context context, MenuPresenter menuPresenter) {
        this.context = context;
        this.menuPresenter = menuPresenter;
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

        holder.binding.swtTrangThaiMonAn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!first) {
                if (isChecked)
                    menuPresenter.changeFoodStatus(food.getFoodId(), Constant.FOOD_STATUS_SELLING);
                else
                    menuPresenter.changeFoodStatus(food.getFoodId(), Constant.FOOD_STATUS_SOLD_OUT);
            }
        });

        holder.binding.txtChinhSua.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditFoodActivity.class);
            intent.putExtra("food", food);
            context.startActivity(intent);
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin17SDP = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._17sdp);
        int margin64SDP = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._64sdp);
        int margin8SDP = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._8sdp);

        if (position == foods.size() - 1) {
            first = false;
            layoutParams.setMargins(margin17SDP, 0, margin17SDP, margin64SDP);
        } else if (position == 0){
            layoutParams.setMargins(margin17SDP, margin8SDP, margin17SDP, margin8SDP);
        } else {
            layoutParams.setMargins(margin17SDP, 0, margin17SDP, margin8SDP);
        }
        holder.binding.getRoot().setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return foods == null ? 0 :foods.size();
    }

    public void setFoods(List<Food> foods) {
        if (this.foods != null)
            this.foods.clear();
        this.foods = foods;
        first = true;
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
