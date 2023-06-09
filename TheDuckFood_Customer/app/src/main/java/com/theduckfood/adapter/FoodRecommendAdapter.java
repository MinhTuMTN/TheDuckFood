package com.theduckfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.R;
import com.theduckfood.activities.SearchActivity;
import com.theduckfood.model.respone.FoodRecommend;

import java.util.List;

public class FoodRecommendAdapter extends RecyclerView.Adapter<FoodRecommendAdapter.ViewHolder> {
    Context context;
    List<FoodRecommend> list;

    public FoodRecommendAdapter(Context context, List<FoodRecommend> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public FoodRecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_food,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecommendAdapter.ViewHolder holder, int position) {
        FoodRecommend foodRecommend = list.get(position);

        holder.imageView.setImageResource(foodRecommend.getImage());
        holder.textView.setText(foodRecommend.getName());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SearchActivity.class);
            String searchParam = null;
            switch (foodRecommend.getName().trim().toLowerCase()) {
                case "phở/bún":
                    searchParam = "Bún";
                    break;
                case "fast food":
                    searchParam = "Pizza";
                    break;
                case "cơm việt":
                    searchParam = "Cơm";
                    break;
                default:
                    searchParam = foodRecommend.getName();
                    break;
            }
            intent.putExtra("search", searchParam);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgFoodRecommend);
            textView = itemView.findViewById(R.id.txtFoodRecommend);

        }
    }
}
