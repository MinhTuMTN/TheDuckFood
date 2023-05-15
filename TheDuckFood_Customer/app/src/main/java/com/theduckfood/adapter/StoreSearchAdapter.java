package com.theduckfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.activities.StoreDetailActivity;
import com.theduckfood.databinding.ItemShopSearchPageBinding;
import com.theduckfood.model.Store;
import com.theduckfood.util.Constant;

import java.text.DecimalFormat;
import java.util.List;

public class StoreSearchAdapter extends RecyclerView.Adapter<StoreSearchAdapter.StoreSearchViewHolder> {
    Context context;
    List<Store> stores;

    public StoreSearchAdapter(Context context, List<Store> stores) {
        this.context = context;
        this.stores = stores;
    }

    @NonNull
    @Override
    public StoreSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoreSearchViewHolder(ItemShopSearchPageBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreSearchViewHolder holder, int position) {
        Store store = stores.get(position);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedNumber = decimalFormat.format(store.getRate());
        holder.binding.txtRate.setText(formattedNumber);
        holder.binding.ratingBar.setRating(store.getRate() / 5);

        holder.binding.txtTenQuan.setText(store.getStoreName());
        String urlImage = store.getAvatar().startsWith("http")
                ? store.getAvatar()
                : Constant.IMAGE_BASE_URL + store.getAvatar();
        Glide.with(context)
                .load(urlImage)
                .into(holder.binding.imgMonAn);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoreDetailActivity.class);
            intent.putExtra("store", store.getStoreId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public boolean hasContent() {
        return stores != null && stores.size() > 0;
    }

    public void setStores(List<Store> stores) {
        this.stores.clear();
        this.stores = stores;
        notifyDataSetChanged();
    }

    static class StoreSearchViewHolder extends RecyclerView.ViewHolder {
        ItemShopSearchPageBinding binding;

        public StoreSearchViewHolder(ItemShopSearchPageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
