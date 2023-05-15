package com.theduckfood.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.R;

import com.theduckfood.databinding.ItemFoodBinding;
import com.theduckfood.databinding.PopupFoodDetailBinding;
import com.theduckfood.databinding.PopupOrderBinding;
import com.theduckfood.model.Food;
import com.theduckfood.util.Constant;
import com.theduckfood.util.DateTimeUtil;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    ItemFoodBinding itemFoodBinding;
    PopupFoodDetailBinding popupFoodDetailBinding;
    PopupOrderBinding popupOrderBinding;
    boolean isOrdering = false;
    private Activity context;
    private List<Food> foods;
    Dialog popUpFoodDetail;
    PopupWindow popUpOrder;

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
        String price  = DateTimeUtil.formatCurrency(String.valueOf(food.getPrice())) + " đ";
        holder.itemFoodBinding.txtPrice.setText(price);
        holder.itemView.setOnClickListener(v -> showPopUpFoodDetail(food));

    }

    public void showPopUpFoodDetail(Food food) {
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

//        View anchor = findViewById(R.id.anchor_view);
//        popUpOrder.showAtLocation(popupOrderBinding.getRoot(), Gravity.BOTTOM, 0, 0);
    }

    private void loadFoodData(Food food) {
        if (food == null) {
            Toast.makeText(context, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            return;
        }

        String urlImage = food.getImage().startsWith("http") ? food.getImage() : Constant.IMAGE_BASE_URL + food.getImage();
        Glide.with(context)
                .load(urlImage)
                .into(popupFoodDetailBinding.imgAvatarFoodPopup);
        popupFoodDetailBinding.txtFoodNamePopup.setText(food.getFoodName());
        popupFoodDetailBinding.txtDescPopup.setText(food.getDescription());

        String price  = DateTimeUtil.formatCurrency(String.valueOf(food.getPrice())) + " đ";
        popupFoodDetailBinding.txtPricePopup.setText(price);

        int amount = 1;
        popupFoodDetailBinding.btnIncrease.setOnClickListener(v -> increaseAmount(amount));
        popupFoodDetailBinding.btnDecrease.setOnClickListener(v -> decreaseAmount(amount));

        popupFoodDetailBinding.btnAdd.setOnClickListener(v -> btnAddClick());

    }

    private void decreaseAmount(int amount) {
        amount = Integer.parseInt(popupFoodDetailBinding.txtAmount.getText().toString());
        if (amount > 1) {
            amount -= 1;
            popupFoodDetailBinding.txtAmount.setText(String.valueOf(amount));
        }
    }

    private void increaseAmount(int amount) {
        amount = Integer.parseInt(popupFoodDetailBinding.txtAmount.getText().toString());
        amount += 1;
        popupFoodDetailBinding.txtAmount.setText(String.valueOf(amount));

    }

    private void btnAddClick() {
        popUpFoodDetail.dismiss();

        showPopUpOrder();

    }

    public void showPopUpOrder() {
        if (isOrdering)
            return;

        isOrdering = true;
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_order, null);
        popUpOrder = new PopupWindow(popupView);
        popupOrderBinding = PopupOrderBinding.inflate(LayoutInflater.from(context));

        int margin300SDP = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._300sdp);
        int margin10SDP = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp);

        popUpOrder.setWidth(margin300SDP);
        popUpOrder.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popUpOrder.showAtLocation(popupView, Gravity.BOTTOM, 0, margin10SDP);
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
