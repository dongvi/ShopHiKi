package com.example.shophiki.cart;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.Main;
import com.example.shophiki.R;
import com.example.shophiki.home.IOnClickProduct;
import com.example.shophiki.home.products.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterProCart extends RecyclerView.Adapter<AdapterProCart.ViewHolder> {

    List<Product> products;
    DecimalFormat format = new DecimalFormat("0.###");
    IOnClickProduct clickProCart;
    Main main;
    FragmentCart fragmentCart;

    public IOnClickProduct getClickProCart() {
        return clickProCart;
    }

    public void setClickProCart(IOnClickProduct clickProCart) {
        this.clickProCart = clickProCart;
    }

    public AdapterProCart(List<Product> products, Main main, FragmentCart fragmentCart) {
        this.products = products;
        this.main = main;
        this.fragmentCart = fragmentCart;
    }

    @NonNull
    @Override
    public AdapterProCart.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_pro_selected, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( AdapterProCart.ViewHolder holder, int position) {
        Product product = products.get(position);

        Picasso.get().load(product.getPic()[0]).into(holder.imgItem);

        holder.voteItem.setRating(Float.parseFloat(String.valueOf(product.getVoteStar())));

        double price = Double.parseDouble(format.format(product.getPrice() - product.getPrice() * product.getSale()/100));
        holder.priceItem.setText(String.format("%,.0f", price) + " ₫");

        double total = price * product.getAmount();
        holder.totalItem.setText(String.format("%,.0f", total) + " ₫");

        if(product.getSale() == 0) {
           holder.saleItem.setVisibility(View.GONE);
           holder.priceItem.setTextColor(Color.parseColor("#FF000000"));
        }
        else {
            holder.saleItem.setText("-" + format.format(product.getSale()) + "%");
            holder.saleItem.setVisibility(View.VISIBLE);
            holder.priceItem.setTextColor(Color.parseColor("#FF0066"));
        }

        holder.amoutItem.setText(String.valueOf(product.getAmount()));
        holder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(product.getAmount() > 1) {
                    product.setAmount(product.getAmount() - 1);
                    holder.amoutItem.setText(String.valueOf(product.getAmount()));
                }

                setTotalItems(holder, product, total, price);
            }
        });

        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.setAmount(product.getAmount() + 1);
                holder.amoutItem.setText(String.valueOf(product.getAmount()));

                setTotalItems(holder, product, total, price);
            }
        });
        
//        holder.amoutItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    setTotalItems(holder, product, total, price);
//                }
//                catch (Exception e){
//                    holder.amoutItem.setText(String.valueOf(product.getAmount()));
//                }
//            }
//        });

        holder.amoutItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setTotalItems(holder, product, total, price);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(holder.amoutItem.length() == 0 || Integer.parseInt(holder.amoutItem.getText().toString()) <= 0){
                    holder.amoutItem.setText(String.valueOf(product.getAmount()));
                }
            }
        });


        holder.deleteItem.setVisibility(View.GONE);

        String name;
        if(product.getName().length() < 23) {
            name = product.getName();
        }
        else {
            name = product.getName().substring(0, 21);
            name += "...";
        }
        holder.nameItem.setText(name);

        holder.hideR.setVisibility(View.GONE);

        holder.showR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.hideR.setVisibility(View.VISIBLE);
                holder.showR.setVisibility(View.GONE);
                holder.deleteItem.setVisibility(View.VISIBLE);
            }
        });

        holder.hideR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.hideR.setVisibility(View.GONE);
                holder.showR.setVisibility(View.VISIBLE);
                holder.deleteItem.setVisibility(View.GONE);
            }
        });

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.getProductsSelected().remove(product);
                fragmentCart.setAdapterCart(main.getProductsSelected());
            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickProCart.onClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View item;
        ImageView imgItem;
        TextView nameItem;
        RatingBar voteItem;
        TextView priceItem;
        TextView saleItem;
        View reduce;
        View increase;
        EditText amoutItem;
        TextView totalItem;
        View showR;
        View hideR;
        View deleteItem;

        public ViewHolder( View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_pro_cart);
            imgItem = itemView.findViewById(R.id.img_pro_cart);
            nameItem = itemView.findViewById(R.id.tv_name_pro_cart);
            voteItem = itemView.findViewById(R.id.vote_pro_cart);
            priceItem = itemView.findViewById(R.id.tv_price_item_cart);
            saleItem = itemView.findViewById(R.id.tv_sale_item_cart);
            reduce = itemView.findViewById(R.id.btn_reduce_amount_cart);
            increase = itemView.findViewById(R.id.btn_increase_amount_cart);
            amoutItem = itemView.findViewById(R.id.et_amount_cart);
            totalItem = itemView.findViewById(R.id.tv_total_item_cart);
            showR = itemView.findViewById(R.id.btn_show_remove_item_cart);
            hideR = itemView.findViewById(R.id.btn_hide_remove_item_cart);
            deleteItem = itemView.findViewById(R.id.btn_delete_item_cart);
        }
    }

    public void setTotalItems(AdapterProCart.ViewHolder holder, Product product, double total, double price){
        if(holder.amoutItem.length() != 0 && product.getAmount() != Integer.parseInt(holder.amoutItem.getText().toString()) && Integer.parseInt(holder.amoutItem.getText().toString()) > 0) {
            product.setAmount(Integer.parseInt(holder.amoutItem.getText().toString()));
        }

        total = price * product.getAmount();
        holder.totalItem.setText(String.format("%,.0f", total) + " ₫");

        fragmentCart.setPay(products);
    }
}
