package com.example.shophiki.home.products;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.DetailProduct;
import com.example.shophiki.R;
import com.example.shophiki.databinding.ItemProductBinding;
import com.example.shophiki.home.IOnClickProduct;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

public class AdapterProductNormal extends RecyclerView.Adapter<AdapterProductNormal.ViewHolder>{

    ItemProductBinding binding;
    List<Product> products;
    DecimalFormat format = new DecimalFormat("0.###");
    IOnClickProduct click;

    public IOnClickProduct getClick() {
        return click;
    }

    public void setClick(IOnClickProduct click) {
        this.click = click;
    }

    public AdapterProductNormal(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public AdapterProductNormal.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_product, parent, false);

        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterProductNormal.ViewHolder holder, int position) {
        Product product = products.get(position);

        binding.tvName.setText(product.getName());
        if(product.getBrand() == null || Pattern.matches("[A-Za-z0-9, .\\p{L}]+", product.getBrand()))
            binding.parentBrand.setVisibility(View.GONE);
        else {
            binding.parentBrand.setVisibility(View.VISIBLE);
            Picasso.get().load(product.getBrand()).into(binding.imgBrand);
        }
        Picasso.get().load(product.getPic()[0]).into(binding.imgItem);

        int sold = product.getOriginalAmount() - product.getCurrentAmount();
        if(sold == 0) {
            binding.tvSold.setVisibility(View.GONE);
        }
        else{
            binding.tvSold.setVisibility(View.VISIBLE);
            binding.tvSold.setText(R.string.tv_sold);
            if(sold < 10000)
                binding.tvSold.setText(binding.tvSold.getText().toString()  + String.valueOf(product.getOriginalAmount() - product.getCurrentAmount()));
            else if (sold < 1000000) {
                binding.tvSold.setText(binding.tvSold.getText().toString()  + String.valueOf(sold/1000) + "N+");
            }
            else if (sold < 1000000000) {
                binding.tvSold.setText(binding.tvSold.getText().toString()  + String.format("%.1f",sold/1000000.0) + "Tr+");
            }
            else {
                binding.tvSold.setText(binding.tvSold.getText().toString() + String.format("%.1f",sold/1000000000.0) + "T+");
            }
        }

        double price = Double.parseDouble(format.format(product.getPrice() - product.getPrice() * product.getSale()/100));
        binding.tvPrice.setText(String.format("%,.0f", price) + " ₫");

        if(product.getSale() == 0) {
            binding.tvRateSale.setVisibility(View.GONE);
            binding.tvPrice.setTextColor(Color.parseColor("#FF000000"));
        }
        else {
            binding.tvRateSale.setText("-" + format.format(product.getSale()) + "%");
            binding.tvRateSale.setVisibility(View.VISIBLE);
            binding.tvPrice.setTextColor(Color.parseColor("#FF0066"));
        }




        if(product.getVoteStar() == 0) {
            binding.vote.setVisibility(View.GONE);
            binding.tvVote.setText(R.string.tv_vote);
        }
        else {
            binding.vote.setVisibility(View.VISIBLE);
            binding.vote.setRating(Float.parseFloat(String.valueOf(product.getVoteStar())));
        }

        binding.itemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onClick(product);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
