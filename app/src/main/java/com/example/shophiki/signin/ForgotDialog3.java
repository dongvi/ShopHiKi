package com.example.shophiki.signin;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
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
import com.example.shophiki.databinding.DialogForgot3Binding;
import com.example.shophiki.notificationdialog.ErrorDialog;
import com.example.shophiki.notificationdialog.SuccessfulDialog;
import com.example.shophiki.sql.SQLHelper;

public class ForgotDialog3 extends DialogFragment {

    DialogForgot3Binding binding;
    SQLHelper sqlHelper;
    User user;

    public ForgotDialog3(User user) {
        this.user = user;
    }

    public ForgotDialog3 newInstance() {

        Bundle args = new Bundle();

        ForgotDialog3 fragment = new ForgotDialog3(user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_forgot_3, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);

        sqlHelper = new SQLHelper(getContext());

        binding.btnShowPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPw.setVisibility(View.GONE);
                binding.btnHidePw.setVisibility(View.VISIBLE);
                binding.etPasswordFg.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        binding.btnHidePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPw.setVisibility(View.VISIBLE);
                binding.btnHidePw.setVisibility(View.GONE);
                binding.etPasswordFg.setTransformationMethod(null);
            }
        });

        binding.btnShowPwCf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPwCf.setVisibility(View.GONE);
                binding.btnHidePwCf.setVisibility(View.VISIBLE);
                binding.etConfirmPasswordFg.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        binding.btnHidePwCf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPwCf.setVisibility(View.VISIBLE);
                binding.btnHidePwCf.setVisibility(View.GONE);
                binding.etConfirmPasswordFg.setTransformationMethod(null);
            }
        });

        binding.btnOkForgot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                if (binding.etPasswordFg.getText().toString().equals(binding.etConfirmPasswordFg.getText().toString())) {
                    SuccessfulDialog successfulDialog = new SuccessfulDialog(getString(R.string.tv_title_success_change_pw), getString(R.string.tv_description_success_change_pw));
                    successfulDialog.newInstance().show(fragmentManager, null);
                    sqlHelper.changePW(user.getUsername(), binding.etPasswordFg.getText().toString());
                    dismiss();
                }
                else{
                    ErrorDialog errorDialog = new ErrorDialog(getString(R.string.tv_title_error_fg), getString(R.string.tv_error_confirm_password_incorrect));
                    errorDialog.newInstance().show(fragmentManager, null);
                }
            }
        });

        return binding.getRoot();
    }
}
