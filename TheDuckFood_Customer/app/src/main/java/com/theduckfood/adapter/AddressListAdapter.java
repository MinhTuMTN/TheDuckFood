package com.theduckfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.databinding.ItemAddressBinding;
import com.theduckfood.databinding.ItemFoodBinding;
import com.theduckfood.model.UserAddress;

import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> {
    ItemAddressBinding itemAddressBinding;
    Context context;
    List<UserAddress> addresses;

    public AddressListAdapter(Context context, List<UserAddress> addresses) {
        this.context = context;
        this.addresses = addresses;
    }

    @NonNull
    @Override
    public AddressListAdapter.AddressListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemAddressBinding = ItemAddressBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new AddressListAdapter.AddressListViewHolder(itemAddressBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressListAdapter.AddressListViewHolder holder, int position) {
        UserAddress userAddress = addresses.get(position);
        holder.itemAddressBinding.txtStreetAddress.setText(userAddress.getStreetAddress());
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public class AddressListViewHolder extends RecyclerView.ViewHolder {
        ItemAddressBinding itemAddressBinding;

        public AddressListViewHolder(ItemAddressBinding itemAddressBinding) {
            super(itemAddressBinding.getRoot());
            this.itemAddressBinding = itemAddressBinding;
        }
    }

}
