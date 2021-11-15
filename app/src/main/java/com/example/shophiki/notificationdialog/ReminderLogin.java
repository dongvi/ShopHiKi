package com.example.shophiki.notificationdialog;

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
import com.example.shophiki.databinding.NotificationDialogBinding;
import com.example.shophiki.signin.DialogFragmentSignIn;

public class ReminderLogin extends DialogFragment {

    NotificationDialogBinding binding;

    public static ReminderLogin newInstance() {
        
        Bundle args = new Bundle();
        
        ReminderLogin fragment = new ReminderLogin();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.notification_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(true);

        binding.tvTitle.setText(R.string.tv_title_reminder_login);
        binding.tvDescription.setText(R.string.tv_description_reminder_login);

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                DialogFragmentSignIn dialogFragmentSignIn = new DialogFragmentSignIn();
                dialogFragmentSignIn.newInstance().show(fragmentManager, null);
                dismiss();
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return binding.getRoot();
    }
}
