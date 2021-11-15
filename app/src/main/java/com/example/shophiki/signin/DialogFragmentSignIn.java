package com.example.shophiki.signin;


import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.shophiki.Main;
import com.example.shophiki.R;
import com.example.shophiki.sql.SQLHelper;
import com.example.shophiki.databinding.SignInActBinding;
import com.example.shophiki.notificationdialog.ErrorDialog;
import com.example.shophiki.notificationdialog.SuccessfulDialog;
import com.example.shophiki.signup.DialogFragmentSignUp;

public class DialogFragmentSignIn extends DialogFragment implements ISignIn {

    SignInActBinding binding;
    SignInPresenter presenter;
    Main main;
    SQLHelper sqlHelper;

    public DialogFragmentSignIn newInstance() {
        
        Bundle args = new Bundle();
        
        DialogFragmentSignIn fragment = new DialogFragmentSignIn();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        sqlHelper = new SQLHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.sign_in_act, container, false);
        presenter = new SignInPresenter(this, getContext());
        main = (Main) getActivity();

        binding.btnCancelSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                main.setSignIn(false);
            }
        });

        binding.btnShowPw.setVisibility(View.GONE);
        binding.btnHidePw.setVisibility(View.VISIBLE);

        binding.btnShowPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPw.setVisibility(View.GONE);
                binding.btnHidePw.setVisibility(View.VISIBLE);
                binding.etPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        binding.btnHidePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPw.setVisibility(View.VISIBLE);
                binding.btnHidePw.setVisibility(View.GONE);
                binding.etPassword.setTransformationMethod(null);
            }
        });

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSignIn(binding.etUsername.getText().toString(),
                                   binding.etPassword.getText().toString(),
                                   binding.cbRemember.isChecked(), sqlHelper);

                if(presenter.isOk()){
                    dismiss();
                    main.setSignIn(true);
                    main.setUser(presenter.getUser());
                }
            }
        });

        binding.btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                ForgotDialog1 forgotDialog1 = new ForgotDialog1(sqlHelper);
                forgotDialog1.newInstance().show(fragmentManager, null);
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                DialogFragmentSignUp dialogFragmentSignUp = new DialogFragmentSignUp();
                dialogFragmentSignUp.newInstance().show(fragmentManager, null);
            }
        });

        binding.btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnTermsOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return binding.getRoot();
    }

    @Override
    public void onSuccessful(String title, String description) {
        SuccessfulDialog successfulDialog = new SuccessfulDialog(title, description);
        successfulDialog.newInstance().show(getFragmentManager(), null);
    }

    @Override
    public void onError(String title, String description) {
        ErrorDialog errorDialog = new ErrorDialog(title, description);
        errorDialog.newInstance().show(getFragmentManager(), null);
    }
    
}
