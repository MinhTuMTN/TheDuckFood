package com.theduckfood.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
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

import com.theduckfood.activities.OrderPaymentActivity;
import com.theduckfood.databinding.ItemFoodBinding;
import com.theduckfood.databinding.PopupFoodDetailBinding;
import com.theduckfood.databinding.PopupOrderBinding;
import com.theduckfood.model.CartItem;
import com.theduckfood.model.Food;
import com.theduckfood.model.Store;
import com.theduckfood.model.respone.StoreResponse;
import com.theduckfood.util.Constant;
import com.theduckfood.util.DateTimeUtil;
import com.theduckfood.util.SharedPreferenceManager;

import java.util.List;
import java.util.Objects;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    ItemFoodBinding itemFoodBinding;
    PopupFoodDetailBinding popupFoodDetailBinding;
    PopupOrderBinding popupOrderBinding;
    boolean isOrdering = false;
    private Context context;
    private List<Food> foods;
    private Store store;
    Dialog popUpFoodDetail;
    PopupWindow popUpOrder;
    SharedPreferenceManager sharedPreferenceManager;

    public FoodListAdapter(Context context, List<Food> foods, Store store) {
        this.context = context;
        this.foods = foods;
        this.store = store;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemFoodBinding = ItemFoodBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        sharedPreferenceManager = new SharedPreferenceManager(context);
        if (!sharedPreferenceManager.getCartItems().isEmpty()) {
            showPopUpOrder();
        }
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
        String price = DateTimeUtil.formatCurrency(String.valueOf(food.getPrice())) + " đ";
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

        String price = DateTimeUtil.formatCurrency(String.valueOf(food.getPrice())) + " đ";
        popupFoodDetailBinding.txtPricePopup.setText(price);

        int amount = 0;

        List<CartItem> cartItems = sharedPreferenceManager.getCartItems();
        CartItem item;
        for (int i = 0; i < cartItems.size(); i++) {
            item = (CartItem) cartItems.get(i);
            if (Objects.equals(item.getFood().getFoodId(), food.getFoodId())) {
                amount = item.getAmount();
                break;
            }
        }

        int finalAmount = amount;
        popupFoodDetailBinding.txtAmount.setText(String.valueOf(finalAmount));
        popupFoodDetailBinding.btnIncrease.setOnClickListener(v -> increaseAmount());
        popupFoodDetailBinding.btnDecrease.setOnClickListener(v -> decreaseAmount());

        popupFoodDetailBinding.btnAdd.setOnClickListener(v -> btnAddClick(food));

    }

    private void decreaseAmount() {
        int amount =  Integer.parseInt(popupFoodDetailBinding.txtAmount.getText().toString());
        if (amount > 0) {
            amount -= 1;
            popupFoodDetailBinding.txtAmount.setText(String.valueOf(amount));
        }
    }

    private void increaseAmount() {
        int amount =  Integer.parseInt(popupFoodDetailBinding.txtAmount.getText().toString());
        amount += 1;
        popupFoodDetailBinding.txtAmount.setText(String.valueOf(amount));

    }

    private void btnAddClick(Food food) {
        int amountFood = Integer.parseInt(popupFoodDetailBinding.txtAmount.getText().toString());

        CartItem cartItem = new CartItem(food, amountFood);
        sharedPreferenceManager.addCartItem(cartItem, store.getStoreId());

        popUpFoodDetail.dismiss();

        showPopUpOrder();
    }

    public void showPopUpOrder() {
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_order, null);
        popUpOrder = new PopupWindow(popupView);
        popupOrderBinding = PopupOrderBinding.inflate(LayoutInflater.from(context));
        popUpOrder.setContentView(popupOrderBinding.getRoot());

        int margin300SDP = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._300sdp);
        int margin10SDP = context.getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp);

        popUpOrder.setOutsideTouchable(false);
        popUpOrder.setFocusable(false);
        popUpOrder.setTouchable(true);

        popUpOrder.setWidth(margin300SDP);
        popUpOrder.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        int amount = 0;
        int price = 0;
        for (CartItem cartItem : sharedPreferenceManager.getCartItems()) {
            amount += cartItem.getAmount();
            price += cartItem.getFood().getPrice() * cartItem.getAmount();
        }

        String amountCart = amount + " món";
        popupOrderBinding.txtAmountFoodPopup.setText(amountCart);

        popupOrderBinding.txtStoreNamePopup.setText(store.getStoreName());

        String priceCart = price + " đ";
        popupOrderBinding.txtPriceOrderPopup.setText(priceCart);
        popUpOrder.showAtLocation(popupView, Gravity.BOTTOM, 0, margin10SDP);

        popupOrderBinding.popupOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToOrderPaymentActivity(store);
            }
        });
    }

    private void switchToOrderPaymentActivity(Store store) {
        Intent intent = new Intent(context, OrderPaymentActivity.class);
        intent.putExtra("store", store);
        context.startActivity(intent);
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
