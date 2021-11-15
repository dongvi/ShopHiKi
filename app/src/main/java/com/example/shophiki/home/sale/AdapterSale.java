package com.example.shophiki.home.sale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.home.IOnClickProduct;
import com.example.shophiki.home.products.Product;
import com.example.shophiki.R;
import com.example.shophiki.databinding.ItemSpSaleBinding;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterSale extends RecyclerView.Adapter<AdapterSale.ViewHolder> {

    List<Product> products;
    DecimalFormat format = new DecimalFormat("0.###");
    ItemSpSaleBinding binding;
    IOnClickProduct click;

    public IOnClickProduct getClick() {
        return click;
    }

    public void setClick(IOnClickProduct click) {
        this.click = click;
    }

    public AdapterSale(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public AdapterSale.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_sp_sale, parent, false);

        AdapterSale.ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterSale.ViewHolder holder, int position) {
        Product product = products.get(position);
        binding.imgFlame.setVisibility(View.GONE);

        binding.tvRateSale.setText("-" + String.valueOf(format.format(product.getSale()) + "%"));
        double priceSaled = Double.parseDouble(format.format(product.getPrice() - product.getPrice() * product.getSale()/100));
        binding.tvPriceSale.setText(String.format("%,.0f", priceSaled) + " ₫");
        int sold = product.getOriginalAmount() - product.getCurrentAmount();
        int progress = (int) ( (double) sold / (double) product.getOriginalAmount() * 100);
        binding.progressSell.setProgress(progress);
        if(progress < 10)
            binding.statusSale.setText(R.string.tv_process1);
        else if(progress < 75) {
            binding.statusSale.setText(R.string.tv_sold);
            binding.statusSale.setText(binding.statusSale.getText().toString() + " " + (product.getOriginalAmount() - product.getCurrentAmount()));
        }
        else if(progress < 100){
            binding.statusSale.setText(R.string.tv_process2);
            binding.imgFlame.setVisibility(View.VISIBLE);
        }
        else  {
            binding.statusSale.setText(R.string.tv_process3);
            binding.imgFlame.setVisibility(View.GONE);
        }

        Picasso.get().load(product.getPic()[0]).into(binding.imgSale);

        binding.itemSale.setOnClickListener(new View.OnClickListener() {
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
