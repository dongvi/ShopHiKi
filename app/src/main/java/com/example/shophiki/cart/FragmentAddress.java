package com.example.shophiki.cart;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.shophiki.R;
import com.example.shophiki.databinding.FragmentAddressBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentAddress extends DialogFragment {

    List<Province> provinces;
    FragmentAddressBinding binding;
    AdapterProvince adapterProvinces;
    AdapterDistrict adapterDistrict;
    AdapterTown adapterTown;
    FragmentVerification fragmentVerification;


    public FragmentAddress(List<Province> provinces, FragmentVerification fragmentVerification) {
        this.provinces = provinces;
        this.fragmentVerification = fragmentVerification;
    }

    public FragmentAddress newInstance() {

        Bundle args = new Bundle();

        FragmentAddress fragment = new FragmentAddress(provinces, fragmentVerification);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false);

        btnProvince();


        binding.btnProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnProvince();
            }
        });

        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                fragmentVerification.btnOrder();
            }
        });


        return binding.getRoot();
    }

    public void btnProvince(){
        binding.btnProvince.setBackgroundResource(R.drawable.button);
        binding.btnDistrict.setBackgroundResource(R.drawable.button_off);
        binding.btnTown.setBackgroundResource(R.drawable.button_off);

        adapterProvinces = new AdapterProvince(provinces, fragmentVerification);
        binding.containerListAddress.setAdapter(adapterProvinces);

        adapterProvinces.setOnClickProvince(new IOnClickProvince() {
            @Override
            public void onClickProvince(District[] districts) {
                btnDistrict(districts);
                adapterDistrict.setOnClickDistrict(new IOnClickDistrict() {
                    @Override
                    public void onClickDistrict(String[] towns) {
                        btnTown(towns);
                    }
                });
            }
        });
    }

    public void btnDistrict(District[] districts){
        binding.btnProvince.setBackgroundResource(R.drawable.button_off);
        binding.btnDistrict.setBackgroundResource(R.drawable.button);
        binding.btnTown.setBackgroundResource(R.drawable.button_off);

        adapterDistrict = new AdapterDistrict(districts, fragmentVerification);
        binding.containerListAddress.setAdapter(adapterDistrict);
    }

    public void btnTown(String[] towns){
        binding.btnProvince.setBackgroundResource(R.drawable.button_off);
        binding.btnDistrict.setBackgroundResource(R.drawable.button_off);
        binding.btnTown.setBackgroundResource(R.drawable.button);

        adapterTown = new AdapterTown(towns, fragmentVerification, this);
        binding.containerListAddress.setAdapter(adapterTown);
    }
}
