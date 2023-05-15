package com.theduckfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.theduckfood.activities.StoreDetailActivity;
import com.theduckfood.databinding.ItemShopHomePageBinding;
import com.theduckfood.model.Store;
import com.theduckfood.util.Constant;

import java.util.List;

public class HomeStoreAdapter extends RecyclerView.Adapter<HomeStoreAdapter.HomeStoreViewHolder> {
    Context context;
    List<Store> stores;

    public HomeStoreAdapter(Context context, List<Store> stores) {
        this.context = context;
        this.stores = stores;
    }

    @NonNull
    @Override
    public HomeStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeStoreViewHolder(ItemShopHomePageBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeStoreViewHolder holder, int position) {
        Store store = stores.get(position);

        String urlAvatar = store.getAvatar().startsWith("http")
                ? store.getAvatar()
                : Constant.IMAGE_BASE_URL + store.getAvatar();
        Glide.with(context)
                .load(urlAvatar)
                .into(holder.binding.imgMonAn);
        holder.binding.txtTenQuan.setText(store.getStoreName());
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

    static class HomeStoreViewHolder extends RecyclerView.ViewHolder {
        ItemShopHomePageBinding binding;

        public HomeStoreViewHolder(ItemShopHomePageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
