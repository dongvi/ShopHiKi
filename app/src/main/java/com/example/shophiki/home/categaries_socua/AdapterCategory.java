package com.example.shophiki.home.categaries_socua;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.R;
import com.example.shophiki.databinding.ItemsCategoryBinding;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    List<String> categories;
    ItemsCategoryBinding binding;
    IOnClickItemCategory click;

    public AdapterCategory(List<String> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.items_category, parent, false);

        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterCategory.ViewHolder holder, int position) {
        String name = categories.get(position);

        binding.tvNameCategory.setText(name);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
