package com.example.shophiki.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.shophiki.R;
import com.example.shophiki.databinding.ItemAddressBinding;

public class AdapterTown extends BaseAdapter {

    String[] towns;
    ItemAddressBinding binding;
    FragmentVerification fragmentVerification;
    FragmentAddress fragmentAddress;

    public AdapterTown(String[] towns, FragmentVerification fragmentVerification, FragmentAddress fragmentAddress) {
        this.towns = towns;
        this.fragmentVerification = fragmentVerification;
        this.fragmentAddress = fragmentAddress;
    }

    @Override
    public int getCount() {
        return towns.length;
    }

    @Override
    public Object getItem(int i) {
        return towns[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_address, viewGroup, false);

        binding.tvNameOfItem.setText(towns[i]);

        binding.itemAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentVerification.setTown(towns[i]);
                fragmentAddress.dismiss();
                fragmentVerification.btnOrder();
            }
        });

        return binding.getRoot();
    }
}
