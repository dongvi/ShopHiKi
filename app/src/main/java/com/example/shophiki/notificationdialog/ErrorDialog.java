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

public class ErrorDialog extends DialogFragment {

    NotificationDialogBinding binding;
    String title = "", description = "";

    public ErrorDialog(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ErrorDialog newInstance() {

        Bundle args = new Bundle();

        ErrorDialog fragment = new ErrorDialog(title, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.notification_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(true);

        binding.tvTitle.setText(title);
        binding.tvDescription.setText(description);

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.btnCancel.setVisibility(View.GONE);

        return binding.getRoot();
    }
}
