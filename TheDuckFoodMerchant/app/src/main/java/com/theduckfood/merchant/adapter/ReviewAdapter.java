package com.theduckfood.merchant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.merchant.databinding.ItemMonAnBinding;
import com.theduckfood.merchant.databinding.ItemReviewBinding;
import com.theduckfood.merchant.model.response.ReviewItemResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    Context context;
    List<ReviewItemResponse> reviewItemResponses;

    public ReviewAdapter(Context context, List<ReviewItemResponse> reviewItemResponses) {
        this.context = context;
        this.reviewItemResponses = reviewItemResponses;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewHolder(
                ItemReviewBinding
                        .inflate(
                                LayoutInflater.from(context),
                                parent,
                                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        ReviewItemResponse response = reviewItemResponses.get(position);

        holder.binding.ratingBar.setRating(response.getReview().getRate());
        holder.binding.txtUserName.setText(response.getUserName());

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = new Date();
            date = inputFormat.parse(response.getCreatedAt());
            assert date != null;
            String outputDate = outputFormat.format(date);
            holder.binding.txtDate.setText(outputDate);
        } catch (Exception ignored) {

        }
        holder.binding.txtContent.setText(response.getReview().getReviewContent());
    }

    @Override
    public int getItemCount() {
        return reviewItemResponses.size();
    }

    static class ReviewHolder extends RecyclerView.ViewHolder {
        ItemReviewBinding binding;

        public ReviewHolder(ItemReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
