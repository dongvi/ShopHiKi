package com.example.shophiki.home.genuine_product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.R;
import com.example.shophiki.databinding.ItemGpBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterGP extends RecyclerView.Adapter<AdapterGP.ViewHolder> {

    ItemGpBinding binding;
    List<GP> gps;

    public AdapterGP(List<GP> gps) {
        this.gps = gps;
    }

    @NonNull
    @Override
    public AdapterGP.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_gp, parent, false);

        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterGP.ViewHolder holder, int position) {
        GP gp = gps.get(position);

        Picasso.get().load(gp.getImgFeaturedProducts()).into(binding.imgItem);
        Picasso.get().load(gp.getImgBrand()).into(binding.imgBrand);
        binding.tvEndowItem.setText(gp.getEndow());
    }

    @Override
    public int getItemCount() {
        return gps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
