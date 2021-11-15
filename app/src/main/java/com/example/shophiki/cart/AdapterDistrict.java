package com.example.shophiki.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.example.shophiki.R;
import com.example.shophiki.databinding.ItemAddressBinding;

public class AdapterDistrict extends BaseAdapter {

    District[] districts;
    ItemAddressBinding binding;
    FragmentVerification fragmentVerification;
    IOnClickDistrict onClickDistrict;

    public AdapterDistrict(District[] districts, FragmentVerification fragmentVerification) {
        this.districts = districts;
        this.fragmentVerification = fragmentVerification;
    }

    public IOnClickDistrict getOnClickDistrict() {
        return onClickDistrict;
    }

    public void setOnClickDistrict(IOnClickDistrict onClickDistrict) {
        this.onClickDistrict = onClickDistrict;
    }

    @Override
    public int getCount() {
        return districts.length;
    }

    @Override
    public Object getItem(int i) {
        return districts[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_address, viewGroup, false);

        binding.tvNameOfItem.setText(districts[i].getName());

        binding.itemAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentVerification.setDistrict(districts[i].getName());
                onClickDistrict.onClickDistrict(districts[i].getTowns());
            }
        });

        return binding.getRoot();
    }
}
