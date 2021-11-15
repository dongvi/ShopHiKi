package com.example.shophiki.cart;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.shophiki.R;
import com.example.shophiki.databinding.DialogWarningBinding;

public class DialogWnRemove extends DialogFragment {

    DialogWarningBinding binding;
    String description;
    FragmentCart fragmentCart;

    public DialogWnRemove(String description, FragmentCart fragmentCart) {
        this.description = description;
        this.fragmentCart = fragmentCart;
    }

    public DialogWnRemove newInstance() {

        Bundle args = new Bundle();

        DialogWnRemove fragment = new DialogWnRemove(description, fragmentCart);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_warning, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(true);

        binding.tvDescription.setText(description);

        binding.btnCancelWn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.btnOkWn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                fragmentCart.removeAllPro();
            }
        });

        return binding.getRoot();
    }
}
