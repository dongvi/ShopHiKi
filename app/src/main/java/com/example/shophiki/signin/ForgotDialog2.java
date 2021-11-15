package com.example.shophiki.signin;

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
import androidx.fragment.app.FragmentManager;

import com.example.shophiki.R;
import com.example.shophiki.User;
import com.example.shophiki.databinding.DialogForgot2Binding;
import com.example.shophiki.notificationdialog.ErrorDialog;

public class ForgotDialog2 extends DialogFragment {

    DialogForgot2Binding binding;
    User user;
    int code = 999999;
    int times = 3;

    public ForgotDialog2(User user) {
        this.user = user;
    }

    public ForgotDialog2 newInstance() {

        Bundle args = new Bundle();

        ForgotDialog2 fragment = new ForgotDialog2(user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_forgot_2, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);

        binding.btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.tvTimes.setText(String.valueOf(times));

        binding.btnOkForgot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(binding.etCode.getText().toString()) == code){
                    dismiss();
                    FragmentManager fragmentManager = getFragmentManager();
                    ForgotDialog3 forgotDialog3 = new ForgotDialog3(user);
                    forgotDialog3.newInstance().show(fragmentManager, null);
                }
                else {
                    FragmentManager fragmentManager = getFragmentManager();
                    if(times > 1) {
                        ErrorDialog errorDialog = new ErrorDialog(getString(R.string.tv_title_error_fg), getString(R.string.tv_description_error_fg2));
                        errorDialog.newInstance().show(fragmentManager, null);
                        times--;
                        binding.tvTimes.setText(String.valueOf(times));
                    }
                    else{
                        ErrorDialog errorDialog2 = new ErrorDialog(getString(R.string.tv_title_error_fg), getString(R.string.tv_description_error_fg2_1));
                        errorDialog2.newInstance().show(fragmentManager, null);
                        dismiss();
                    }
                }
            }
        });

        return binding.getRoot();
    }
}
