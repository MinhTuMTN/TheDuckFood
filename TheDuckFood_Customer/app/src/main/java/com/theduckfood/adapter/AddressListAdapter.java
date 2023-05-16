package com.theduckfood.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.databinding.ItemAddressBinding;
import com.theduckfood.databinding.ItemFoodBinding;
import com.theduckfood.model.UserAddress;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.model.respone.UserAddressResponse;
import com.theduckfood.presenter.UserAddressPresenter;
import com.theduckfood.presenter.contact.IUserAddressView;

import java.util.ArrayList;
import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> implements IUserAddressView {
    ItemAddressBinding itemAddressBinding;
    Context context;
    List<UserAddress> addresses;
    UserAddressPresenter userAddressPresenter;

    public AddressListAdapter(Context context, List<UserAddress> addresses) {
        this.context = context;
        this.addresses = addresses;
    }
    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.addresses.clear();
        this.addresses = userAddresses;
        notifyDataSetChanged();
    }
    public void addUserAddress(UserAddress userAddress) {
        this.addresses.add(userAddress);
        notifyDataSetChanged();
    }
    public void deleteUserAddress(int position) {
        this.addresses.remove(position);
        notifyItemRemoved(position);
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
        holder.itemAddressBinding.btnXoaAddress.setOnClickListener(v -> btnXoaClicked(userAddress.getUserAddressId(), position));
        holder.itemAddressBinding.btnSuDungAddress.setOnClickListener(v -> selectAddress(userAddress));
    }

    private void selectAddress(UserAddress userAddress) {
        Intent intent = new Intent();
        intent.putExtra("userAddress", userAddress);
        ((Activity)context).setResult(Activity.RESULT_OK, intent);
        ((Activity)context).finish();
    }

    private void btnXoaClicked(Long userAddressId, int position) {
        userAddressPresenter = new UserAddressPresenter(this, context);
        userAddressPresenter.deleteUserAddress(userAddressId);
        deleteUserAddress(position);
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    @Override
    public void addUserAddress(SimpleMessageResponse simpleMessageResponse) {

    }

    @Override
    public void getUserAddress(UserAddressResponse userAddressResponse) {

    }

    @Override
    public void deleteUserAddress(UserAddressResponse userAddressResponse) {
        if (userAddressResponse == null) {
            Toast.makeText(context, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(context, userAddressResponse.getMessage(), Toast.LENGTH_SHORT).show();

    }

    public class AddressListViewHolder extends RecyclerView.ViewHolder {
        ItemAddressBinding itemAddressBinding;

        public AddressListViewHolder(ItemAddressBinding itemAddressBinding) {
            super(itemAddressBinding.getRoot());
            this.itemAddressBinding = itemAddressBinding;
        }
    }

}
