package com.example.shophiki.signup;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.shophiki.R;
import com.example.shophiki.sql.SQLHelper;
import com.example.shophiki.User;
import com.example.shophiki.databinding.SignUpActBinding;
import com.example.shophiki.notificationdialog.SuccessfulDialog;

import java.util.Calendar;
import java.util.List;

public class DialogFragmentSignUp extends DialogFragment implements ISignUp {

    int day, month, year;

    SignUpActBinding binding;
    SignUpPresenter presenter;
    SQLHelper sqlHelper;

    public static DialogFragmentSignUp newInstance() {

        Bundle args = new Bundle();

        DialogFragmentSignUp fragment = new DialogFragmentSignUp();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        sqlHelper = new SQLHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.sign_up_act, container, false);
        presenter = new SignUpPresenter(this, getContext());

        binding.btnBackToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.btnNotePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSuccessful(getString(R.string.tv_title_note_phone), getString(R.string.tv_description_note_phone));
            }
        });


        binding.btnShowPw.setVisibility(View.GONE);
        binding.btnHidePw.setVisibility(View.VISIBLE);
        binding.btnShowPwCf.setVisibility(View.GONE);
        binding.btnHidePwCf.setVisibility(View.VISIBLE);

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

        binding.btnShowPwCf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPwCf.setVisibility(View.GONE);
                binding.btnHidePwCf.setVisibility(View.VISIBLE);
                binding.etConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        binding.btnHidePwCf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPwCf.setVisibility(View.VISIBLE);
                binding.btnHidePwCf.setVisibility(View.GONE);
                binding.etConfirmPassword.setTransformationMethod(null);
            }
        });

        binding.btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regrexRemoveSpace = "\\s\\s+";
                String fullName = binding.etFullname.getText().toString().replaceAll(regrexRemoveSpace, " ").trim();
                String username = binding.etUsername.getText().toString();
                String birthday = binding.tvBirthDay.getText().toString();
                String phoneNumber = binding.etPhoneNumber.getText().toString();
                String password = binding.etPassword.getText().toString();
                String cfPassword = binding.etConfirmPassword.getText().toString();
                String gender = "";
                if(binding.rdMale.isChecked())
                    gender = "Male";
                if(binding.rdFemale.isChecked())
                    gender = "Female";
                boolean isAgree = binding.cbAgree.isChecked();

                if(!isExistAcc(username))
                    presenter.onSignUp(fullName, gender, birthday, phoneNumber, username, password, cfPassword, isAgree);

                if(presenter.isOk()){
                    sqlHelper.add(username, password, fullName, gender, birthday, phoneNumber, null);
                    dismiss();
                }
            }
        });

        goneAllTVError();

        return binding.getRoot();
    }

    public void setDate(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int _year, int _month, int _day) {
                binding.tvBirthDay.setText(String.format("%02d / %02d / %4d", _day, _month + 1, _year));
                day = _day;
                month = _month;
                year = _year;
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, dateSetListener, year, month, day);
        datePickerDialog.show();
    }


    @Override
    public void onSuccessful(String title, String description) {
        SuccessfulDialog successfulDialog = new SuccessfulDialog(title, description);
        successfulDialog.newInstance().show(getFragmentManager(), null);
    }

    @Override
    public void onError(String description, int type) {
        switch (type){
            case 1:
                goneAllTVError();
                binding.tvErrorName.setVisibility(View.VISIBLE);
                binding.tvErrorName.setText(description);
                break;
            case 2:
                goneAllTVError();
                binding.tvErrorPhone.setVisibility(View.VISIBLE);
                binding.tvErrorPhone.setText(description);
                break;
            case 3:
                goneAllTVError();
                binding.tvErrorBirthday.setVisibility(View.VISIBLE);
                binding.tvErrorBirthday.setText(description);
                break;
            case 4:
                goneAllTVError();
                binding.tvErrorUsername.setVisibility(View.VISIBLE);
                binding.tvErrorUsername.setText(description);
                break;
            case 5:
                goneAllTVError();
                binding.tvErrorPassword.setVisibility(View.VISIBLE);
                binding.tvErrorPassword.setText(description);
                break;
            case 6:
                goneAllTVError();
                binding.tvErrorCfpassword.setVisibility(View.VISIBLE);
                binding.tvErrorCfpassword.setText(description);
                break;
            default:
                goneAllTVError();
                binding.tvErrorAgree.setVisibility(View.VISIBLE);
                binding.tvErrorAgree.setText(description);
                break;
        }
    }

    @Override
    public void onBack(boolean ok) {

    }

    public void goneAllTVError(){
        binding.tvErrorName.setVisibility(View.GONE);
        binding.tvErrorPhone.setVisibility(View.GONE);
        binding.tvErrorBirthday.setVisibility(View.GONE);
        binding.tvErrorUsername.setVisibility(View.GONE);
        binding.tvErrorPassword.setVisibility(View.GONE);
        binding.tvErrorCfpassword.setVisibility(View.GONE);
        binding.tvErrorAgree.setVisibility(View.GONE);
    }

    public boolean isExistAcc(String username){
        List<User> users = sqlHelper.getAllUser();
        for(User x : users)
            if(username.compareToIgnoreCase(x.getUsername()) == 0) {
                onError(getString(R.string.tv_error_username_exists), 4);
                return true;
            }
        return false;
    }
}
