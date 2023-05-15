package com.theduckfood.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.R;

import com.theduckfood.databinding.ItemFoodBinding;
import com.theduckfood.databinding.PopupFoodDetailBinding;
import com.theduckfood.model.Food;
import com.theduckfood.util.Constant;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    ItemFoodBinding itemFoodBinding;
    PopupFoodDetailBinding popupFoodDetailBinding;
    private Activity context;
    private List<Food> foods;
    Dialog popUpFoodDetail;

    public FoodListAdapter(Activity context, List<Food> foods) {
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemFoodBinding = ItemFoodBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new FoodListViewHolder(itemFoodBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.FoodListViewHolder holder, int position) {
        Food food = foods.get(position);

        String urlImage = food.getImage().startsWith("http") ? food.getImage() : Constant.IMAGE_BASE_URL + food.getImage();
        Glide.with(context)
                .load(urlImage)
                .into(holder.itemFoodBinding.imgAvatarFood);
        holder.itemFoodBinding.txtFoodName.setText(food.getFoodName());
        holder.itemFoodBinding.txtPrice.setText(Math.round(food.getPrice()) + " đ");
        holder.itemView.setOnClickListener(v -> showPopUpFoodDetail(food));

    }

    public void showPopUpFoodDetail(Food food) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
//        View view = inflater.inflate(R.layout.popup_food_detail, null);
        popUpFoodDetail = new Dialog(
                context,
                R.style.MaterialDialogSheet);
        popupFoodDetailBinding = PopupFoodDetailBinding.inflate(LayoutInflater.from(context));
        popUpFoodDetail.setContentView(popupFoodDetailBinding.getRoot());

        popUpFoodDetail.setCancelable(true);
        popUpFoodDetail.getWindow().setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popUpFoodDetail.getWindow().setGravity(Gravity.BOTTOM);
        popUpFoodDetail.show();
        loadFoodData(food);
    }

    private void loadFoodData(Food food) {
        if (food == null) {
            Toast.makeText(context, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            return;
        }

//        ImageView imgAvatarFoodPopup = (ImageView) view.findViewById(R.id.imgAvatarFoodPopup);
//        TextView txtFoodNamePopup = (TextView) view.findViewById(R.id.txtFoodNamePopup);
//        TextView txtPricePopup = (TextView) view.findViewById(R.id.txtPricePopup);
//        TextView txtDescPopup = (TextView) view.findViewById(R.id.txtDescPopup);
//        Button btnAdd =(Button) view.findViewById(R.id.btnAdd);

        String urlImage = food.getImage().startsWith("http") ? food.getImage() : Constant.IMAGE_BASE_URL + food.getImage();
        Glide.with(context)
                .load(urlImage)
                .into(popupFoodDetailBinding.imgAvatarFoodPopup);
        popupFoodDetailBinding.txtFoodNamePopup.setText(food.getFoodName());
        popupFoodDetailBinding.txtDescPopup.setText(food.getDescription());
        popupFoodDetailBinding.txtPricePopup.setText(food.getPrice() + " đ");

        int amount = 1;
        popupFoodDetailBinding.btnIncrease.setOnClickListener(v -> increaseAmount(amount));
        popupFoodDetailBinding.btnDecrease.setOnClickListener(v -> decreaseAmount(amount));

//        popupFoodDetailBinding.btnAdd.setOnClickListener(v -> btnAddClick());

    }

    private void increaseAmount(int amount) {
        amount = Integer.parseInt(popupFoodDetailBinding.txtAmount.getText().toString());
        if (amount > 1) {
            amount -= 1;
            popupFoodDetailBinding.txtAmount.setText(String.valueOf(amount));
        }
    }

    private void decreaseAmount(int amount) {
        amount = Integer.parseInt(popupFoodDetailBinding.txtAmount.getText().toString());
        amount += 1;
        popupFoodDetailBinding.txtAmount.setText(String.valueOf(amount));

    }

    private void btnAddClick() {

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class FoodListViewHolder extends RecyclerView.ViewHolder {
        ItemFoodBinding itemFoodBinding;

        public FoodListViewHolder(ItemFoodBinding itemFoodBinding) {
            super(itemFoodBinding.getRoot());
            this.itemFoodBinding = itemFoodBinding;
        }
    }
}
