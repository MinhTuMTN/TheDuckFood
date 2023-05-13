package com.theduckfood.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.theduckfood.R;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderHoler> {

    private Context context;
    private ArrayList<Integer> arrayList;

    public SliderAdapter(Context context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public SliderHoler onCreateViewHolder(ViewGroup parent) {
        return new SliderHoler(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.images_slider_shoppe, parent, false));
    }

    @Override
    public void onBindViewHolder(SliderHoler viewHolder, int position) {
        Glide.with(context)
                .load(arrayList.get(position))
                .override(Resources.getSystem().getDisplayMetrics().widthPixels)
                .override(Resources.getSystem().getDisplayMetrics().heightPixels)
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public class SliderHoler extends ViewHolder {
        private ImageView imageView;

        public SliderHoler(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_auto_image_slider);
        }
    }
}
