package com.example.shophiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.example.shophiki.cart.FragmentCart;
import com.example.shophiki.databinding.MainActBinding;
import com.example.shophiki.home.FragmentHome;
import com.example.shophiki.home.products.Product;
import com.example.shophiki.notificationdialog.ReminderLogin;
import com.example.shophiki.sql.SQLHelper;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    MainActBinding binding;
    FragmentManager fragmentManager;
    boolean signIn;
    SQLHelper sqlHelper;
    User user;
    List<Product> productsSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.main_act);
        fragmentManager = getSupportFragmentManager();

        binding.btnHomeOn.setVisibility(View.VISIBLE);
        binding.btnHomeOff.setVisibility(View.GONE);
        binding.btnCategoryOff.setVisibility(View.VISIBLE);
        binding.btnCategoryOn.setVisibility(View.GONE);
        binding.btnUserOff.setVisibility(View.VISIBLE);
        binding.btnUserOn.setVisibility(View.GONE);
        //binding.btnNotificationOn.setVisibility(View.GONE);

        onFragment(FragmentHome.newInstance());

        binding.btnHomeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnHomeOff.setVisibility(View.GONE);
                binding.btnHomeOn.setVisibility(View.VISIBLE);
                binding.btnCategoryOn.setVisibility(View.GONE);
                binding.btnCategoryOff.setVisibility(View.VISIBLE);
                binding.btnUserOff.setVisibility(View.VISIBLE);
                binding.btnUserOn.setVisibility(View.GONE);

                onFragment(FragmentHome.newInstance());
            }
        });

        binding.btnCategoryOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnHomeOff.setVisibility(View.VISIBLE);
                binding.btnHomeOn.setVisibility(View.GONE);
                binding.btnCategoryOn.setVisibility(View.VISIBLE);
                binding.btnCategoryOff.setVisibility(View.GONE);
                binding.btnUserOff.setVisibility(View.VISIBLE);
                binding.btnUserOn.setVisibility(View.GONE);

                onFragment(FragmentCategory.newInstance());
            }
        });

        binding.btnUserOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(signIn) {
                    binding.btnHomeOff.setVisibility(View.VISIBLE);
                    binding.btnHomeOn.setVisibility(View.GONE);
                    binding.btnCategoryOff.setVisibility(View.VISIBLE);
                    binding.btnCategoryOn.setVisibility(View.GONE);
                    binding.btnUserOn.setVisibility(View.VISIBLE);
                    binding.btnUserOff.setVisibility(View.GONE);

                    onFragment((new FragmentUser(user).newInstance()));
                }
                else {
                    ReminderLogin.newInstance().show(fragmentManager, null);
                }
            }
        });

        productsSelected = new ArrayList<>();

        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCart fragmentCart = new FragmentCart(productsSelected);
                fragmentCart.newInstance().show(fragmentManager, null);
            }
        });


    }

    public void onFragment(Fragment fragment){
        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main, fragment).commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setSignIn(boolean signIn) {
        this.signIn = signIn;
    }

    public boolean isSignIn() {
        return signIn;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductsSelected() {
        return productsSelected;
    }

    public void setProductsSelected(List<Product> productsSelected) {
        this.productsSelected = productsSelected;
    }

}