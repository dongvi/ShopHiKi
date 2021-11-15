package com.example.shophiki.home.trends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.R;
import com.example.shophiki.databinding.ItemTrendBinding;
import com.example.shophiki.databinding.ItemUtiCategoryBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterTrend extends RecyclerView.Adapter<AdapterTrend.ViewHolder> {

    ItemTrendBinding binding;
    List<Trend> trends;

    public AdapterTrend(List<Trend> trends) {
        this.trends = trends;
    }

    @NonNull
    @Override
    public AdapterTrend.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_trend, parent, false);

        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterTrend.ViewHolder holder, int position) {
        Trend trend = trends.get(position);

        Picasso.get().load(trend.getImgTrend()).into(binding.imgItem);
        binding.tvNameItem.setText(trend.getTrendName());
    }

    @Override
    public int getItemCount() {
        return trends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
