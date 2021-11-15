package com.example.shophiki.home.utilities_category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.R;
import com.example.shophiki.databinding.ItemUtiCategoryBinding;

import java.util.List;

public class AdapterUtiCategory extends RecyclerView.Adapter<AdapterUtiCategory.ViewHolder> {

    List<ItemUtiCategory> utiCategory;
    ItemUtiCategoryBinding binding;

    public AdapterUtiCategory(List<ItemUtiCategory> utiCategory) {
        this.utiCategory = utiCategory;
    }

    @Override
    public AdapterUtiCategory.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_uti_category, parent, false);

        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterUtiCategory.ViewHolder holder, int position) {
        ItemUtiCategory item = utiCategory.get(position);

        binding.imgItem.setImageResource(item.getImgItem());
        binding.tvNameItem.setText(item.getNameItem());
    }

    @Override
    public int getItemCount() {
        return utiCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
