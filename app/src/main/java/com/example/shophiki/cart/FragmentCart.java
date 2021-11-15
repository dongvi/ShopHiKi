package com.example.shophiki.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shophiki.DetailProduct;
import com.example.shophiki.Main;
import com.example.shophiki.R;
import com.example.shophiki.databinding.FragmentCartBinding;
import com.example.shophiki.home.IOnClickProduct;
import com.example.shophiki.home.products.Product;
import com.example.shophiki.notificationdialog.ReminderLogin;

import java.text.DecimalFormat;
import java.util.List;

public class FragmentCart extends DialogFragment {

    List<Product> products;
    FragmentCartBinding binding;
    Main main;
    AdapterProCart adapterProCart;
    DecimalFormat format = new DecimalFormat("0.###");

    public FragmentCart(List<Product> products) {
        this.products = products;
    }

    public FragmentCart newInstance() {

        Bundle args = new Bundle();

        FragmentCart fragment = new FragmentCart(products);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        main = (Main) getActivity();
        products = main.getProductsSelected();

        if(products.size() == 0){
            binding.btnOrderOff.setVisibility(View.VISIBLE);
            binding.btnOrderOn.setVisibility(View.GONE);
        }
        else{
            binding.btnOrderOff.setVisibility(View.GONE);
            binding.btnOrderOn.setVisibility(View.VISIBLE);
        }

        binding.btnBackCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.btnDeleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(products.size() > 0)
                btnRemoveAll();
            }
        });

        setAdapterCart(products);

        adapterProCart.setClickProCart(new IOnClickProduct() {
            @Override
            public void onClick(Product product) {
                FragmentManager fragmentManager = getFragmentManager();
                DetailProduct detailProduct = new DetailProduct(product);
                detailProduct.newInstance().show(fragmentManager, null);
            }
        });

        binding.btnOrderOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(main.isSignIn()){
                    setFV();
                }
                else{
                    FragmentManager fragmentManager = getFragmentManager();
                    ReminderLogin.newInstance().show(fragmentManager, null);
                }
            }
        });

        return binding.getRoot();
    }

    public void setAdapterCart(List<Product> products){
        RecyclerView.LayoutManager layoutItem = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        adapterProCart = new AdapterProCart(products, main, this);
        binding.containerProductSelected.setLayoutManager(layoutItem);
        binding.containerProductSelected.setAdapter(adapterProCart);

        binding.tvAmountInCart.setText(String.valueOf(products.size()));
        setPay(products);

        if(products.size() == 0){
            binding.btnOrderOff.setVisibility(View.VISIBLE);
            binding.btnOrderOn.setVisibility(View.GONE);
        }
        else{
            binding.btnOrderOff.setVisibility(View.GONE);
            binding.btnOrderOn.setVisibility(View.VISIBLE);
        }
    }

    public void setPay(List<Product> products){
        double totalPay = 0;
        for(Product x : products){
            double price = Double.parseDouble(format.format(x.getPrice() - x.getPrice() * x.getSale()/100));
            totalPay += price * x.getAmount();
        }
        binding.tvTotalCart.setText(String.format("%,.0f", totalPay) + " ₫");
    }

    public void removeAllPro(){
        main.getProductsSelected().removeAll(products);
        setAdapterCart(products);
    }

    public void btnRemoveAll(){
        FragmentManager fragmentManager = getFragmentManager();
        DialogWnRemove dialogWnRemove = new DialogWnRemove(getString(R.string.tv_description_wn_remove), this);
        dialogWnRemove.newInstance().show(fragmentManager, null);
    }

    public void setFV(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentVerification fragmentVerification = new FragmentVerification(this);
        fragmentVerification.newInstance().show(fragmentManager, null);
    }
}
