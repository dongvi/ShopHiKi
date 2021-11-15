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

import com.example.shophiki.R;
import com.example.shophiki.databinding.NotificationDialogBinding;

public class SuccessfulDialog extends DialogFragment {

    NotificationDialogBinding binding;
    String title = "", description = "";

    public SuccessfulDialog (String title, String description) {
        this.title = title;
        this.description = description;
    }

    public SuccessfulDialog newInstance() {

        Bundle args = new Bundle();

        SuccessfulDialog fragment = new SuccessfulDialog(title, description);
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
        binding.tvTitle.setTextColor(getResources().getColor(R.color.green));

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
