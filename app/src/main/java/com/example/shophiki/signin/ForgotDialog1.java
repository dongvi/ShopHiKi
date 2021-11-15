package com.example.shophiki.signin;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.shophiki.R;
import com.example.shophiki.User;
import com.example.shophiki.databinding.DialogForgot1Binding;
import com.example.shophiki.notificationdialog.ErrorDialog;
import com.example.shophiki.sql.SQLHelper;

import java.util.List;

public class ForgotDialog1 extends DialogFragment {
    
    DialogForgot1Binding binding;
    SQLHelper sqlHelper;
    List<User> users;
    boolean ok;

    public ForgotDialog1(SQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

    public ForgotDialog1 newInstance() {
        
        Bundle args = new Bundle();
        
        ForgotDialog1 fragment = new ForgotDialog1(sqlHelper);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_forgot_1, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        
        binding.btnCancelForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.btnOkForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users = sqlHelper.getAllUser();
                for (User x : users)
                    if(binding.etUsernameForgot.getText().toString().compareToIgnoreCase(x.getUsername()) == 0 &&
                            binding.etPhoneNumberForgot.getText().toString().equals(x.getPhoneNumber())) {
                        FragmentManager fragmentManager = getFragmentManager();
                        ForgotDialog2 forgotDialog2 = new ForgotDialog2(x);
                        forgotDialog2.newInstance().show(fragmentManager, null);
                        ok = true;
                        dismiss();
                    }

                if(ok != true) {
                    FragmentManager fragmentManager = getFragmentManager();
                    ErrorDialog errorDialog = new ErrorDialog(getString(R.string.tv_title_error_fg), getString(R.string.tv_description_error_fg));
                    errorDialog.newInstance().show(fragmentManager, null);
                    ok = false;
                }
            }
        });
        
        return binding.getRoot();
    }
}
