package com.theduckfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theduckfood.R;
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
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.textView.setText(list.get(position).getName());
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
