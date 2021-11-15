package com.example.shophiki.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.shophiki.R;
import com.example.shophiki.databinding.ItemAddressBinding;

import java.util.List;

public class AdapterProvince extends BaseAdapter {

    List<Province> provinces;
    ItemAddressBinding binding;
    IOnClickProvince onClickProvince;
    FragmentVerification fragmentVerification;


    public AdapterProvince(List<Province> provinces, FragmentVerification fragmentVerification) {
        this.provinces = provinces;
        this.fragmentVerification = fragmentVerification;
    }

    public IOnClickProvince getOnClickProvince() {
        return onClickProvince;
    }

    public void setOnClickProvince(IOnClickProvince onClickProvince) {
        this.onClickProvince = onClickProvince;
    }

    @Override
    public int getCount() {
        return provinces.size();
    }

    @Override
    public Object getItem(int i) {
        return provinces.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_address, viewGroup, false);
        fragmentVerification.resetData();
        binding.tvNameOfItem.setText(provinces.get(i).getName());

        binding.itemAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentVerification.setProvince(provinces.get(i).getName());
                onClickProvince.onClickProvince(provinces.get(i).getDistricts());
            }
        });

        return binding.getRoot();
    }
}
