package com.example.shophiki;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.shophiki.databinding.FragmentUserBinding;
import com.example.shophiki.notificationdialog.ErrorDialog;
import com.example.shophiki.notificationdialog.SuccessfulDialog;
import com.example.shophiki.signin.ForgotDialog3;
import com.example.shophiki.sql.SQLHelper;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class FragmentUser extends Fragment {

    FragmentUserBinding binding;
    User user;
    SQLHelper sqlHelper;
    int day, month, year;
    Calendar calendar = Calendar.getInstance();

    public FragmentUser(User user) {
        this.user = user;
    }

    public FragmentUser newInstance() {

        Bundle args = new Bundle();

        FragmentUser fragment = new FragmentUser(user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
        sqlHelper = new SQLHelper(getContext());

        setIfo();

        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuUser();
            }
        });

        binding.parentCpw.setVisibility(View.GONE);

        binding.btnCloseCpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.parentCpw.setVisibility(View.GONE);
            }
        });

        setBtnCpw();

        binding.etPasswordOld.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setBtnCpw();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setBtnCpw();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setBtnCpw();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        showHidePW();

        binding.btnCpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                if(binding.etPasswordOld.length() != 0 && binding.etPassword.length() != 0 && binding.etConfirmPassword.length() != 0) {
                    if (user.getPassword().equals(binding.etPasswordOld.getText().toString())) {
                        if (binding.etPassword.getText().toString().equals(binding.etConfirmPassword.getText().toString())){
                            SuccessfulDialog successfulDialog = new SuccessfulDialog(getString(R.string.tv_title_success_change_pw), getString(R.string.tv_description_success_change_pw));
                            successfulDialog.newInstance().show(fragmentManager, null);
                            sqlHelper.changePW(user.getUsername(), binding.etPassword.getText().toString());
                            binding.parentCpw.setVisibility(View.GONE);
                        }
                    }
                    else {
                        ErrorDialog errorPWO = new ErrorDialog(getString(R.string.tv_title_error_fg), getString(R.string.tv_description_error_pw_old));
                        errorPWO.newInstance().show(fragmentManager, null);
                    }
                }
            }
        });

        binding.parentMe.setVisibility(View.GONE);

        binding.btnCloseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.parentMe.setVisibility(View.GONE);
            }
        });

        setIfoAllEdit();

        binding.btnEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIfoAllEdit();
                binding.tvNameUser.setVisibility(View.GONE);
                binding.etNameUser.setVisibility(View.VISIBLE);
                binding.etNameUser.requestFocus();
            }
        });

        binding.btnEditNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIfoAllEdit();
                binding.tvNicknameUser.setVisibility(View.GONE);
                binding.etNicknameUser.setVisibility(View.VISIBLE);
                binding.etNicknameUser.requestFocus();
            }
        });

        binding.btnEditGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIfoAllEdit();
                binding.tvGenderUser.setVisibility(View.GONE);
                binding.groupRdGender.setVisibility(View.VISIBLE);
            }
        });

        binding.btnEditOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIfoAllEdit();
                setDate();
            }
        });

        binding.btnEditPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIfoAllEdit();
                binding.tvPhoneUser.setVisibility(View.GONE);
                binding.etPhoneUser.setVisibility(View.VISIBLE);
                binding.etPhoneUser.requestFocus();
            }
        });

        binding.btnEditAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIfoAllEdit();
                binding.tvAddressUser.setVisibility(View.GONE);
                binding.etAddressUser.setVisibility(View.VISIBLE);
                binding.etAddressUser.requestFocus();
            }
        });

        binding.btnSaveIfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveIfo();
            }
        });

        return binding.getRoot();
    }


    private void menuUser(){
        PopupMenu popupMenu = new PopupMenu(getContext(), binding.btnSetting);
        popupMenu.inflate(R.menu.menu_user);

        Menu menu = popupMenu.getMenu();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_change_pw:
                        binding.parentCpw.setVisibility(View.VISIBLE);
                        binding.parentMe.setVisibility(View.GONE);
                        break;
                    default:
                        binding.parentCpw.setVisibility(View.GONE);
                        binding.parentMe.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });

        popupMenu.show();
    }

    public void setBtnCpw(){
        if(binding.etPasswordOld.length() != 0 && binding.etPassword.length() != 0 && binding.etConfirmPassword.length() != 0)
            binding.btnCpw.setBackgroundResource(R.drawable.button);
        else
            binding.btnCpw.setBackgroundResource(R.drawable.button_off);
    }

    public void setIfo(){
        binding.tvNameOfUser.setText(user.getFullname());
        if(user.getImgUser() != null)
            Picasso.get().load(user.getImgUser()).into(binding.imgUser);

        binding.tvNameUser.setText(user.getFullname());

        if(user.getNickname() != null)
            binding.tvNicknameUser.setText(user.getNickname());
        else
            binding.tvNicknameUser.setText(getString(R.string.tv_unknown));

        binding.tvGenderUser.setText(user.getGender());
        if(user.getGender().equals(binding.rdMale.getText().toString()))
            binding.rdMale.setChecked(true);
        else
            binding.rdFemale.setChecked(true);

        int yearNow = calendar.get(Calendar.YEAR);
        day = Integer.parseInt(user.getBirthday().substring(0, 2));
        month = Integer.parseInt(user.getBirthday().substring(5, 7)) - 1;
        year = Integer.parseInt(user.getBirthday().substring(10, 14));
        binding.tvOldUser.setText(String.valueOf(yearNow - year));

        if(user.getAddress() != null)
            binding.tvAddressUser.setText(user.getAddress());
        else
            binding.tvAddressUser.setText(getString(R.string.tv_unknown));

        binding.tvPhoneUser.setText(user.getPhoneNumber());
    }

    public void showHidePW(){
        binding.btnShowPwOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPwOld.setVisibility(View.GONE);
                binding.btnHidePwOld.setVisibility(View.VISIBLE);
                binding.etPasswordOld.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        binding.btnHidePwOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPwOld.setVisibility(View.VISIBLE);
                binding.btnHidePwOld.setVisibility(View.GONE);
                binding.etPasswordOld.setTransformationMethod(null);
            }
        });

        binding.btnShowPwNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPwNew.setVisibility(View.GONE);
                binding.btnHidePwNew.setVisibility(View.VISIBLE);
                binding.etPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        binding.btnHidePwNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btnShowPwNew.setVisibility(View.VISIBLE);
                binding.btnHidePwNew.setVisibility(View.GONE);
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
    }

    public void saveIfo(){
        setIfoAllEdit();
        user.setFullname(binding.tvNameUser.getText().toString());
        user.setGender(binding.tvGenderUser.getText().toString());
        user.setPhoneNumber(binding.tvPhoneUser.getText().toString());
        user.setAddress(binding.tvAddressUser.getText().toString());
        user.setNickname(binding.tvNicknameUser.getText().toString());
        user.setBirthday(String.valueOf(String.format("%02d", day)) + " / " + String.valueOf(String.format("%02d", month + 1)) + " / " + String.valueOf(String.format("%04d", year)));

        sqlHelper.update(user);
        setIfo();
    }

    public void setIfoAllEdit(){
        binding.etNameUser.setVisibility(View.GONE);
        binding.etNicknameUser.setVisibility(View.GONE);
        binding.etAddressUser.setVisibility(View.GONE);
        binding.etPhoneUser.setVisibility(View.GONE);
        binding.groupRdGender.setVisibility(View.GONE);

        setIfoEdit(binding.etNameUser, binding.tvNameUser);
        setIfoEdit(binding.etNicknameUser, binding.tvNicknameUser);
        setIfoEdit(binding.etAddressUser, binding.tvAddressUser);
        setIfoEdit(binding.etPhoneUser, binding.tvPhoneUser);

        if(binding.rdMale.isChecked())
            binding.tvGenderUser.setText(binding.rdMale.getText().toString());
        if(binding.rdFemale.isChecked())
            binding.tvGenderUser.setText(binding.rdFemale.getText().toString());

        binding.groupRdGender.setVisibility(View.GONE);

        int yearNow = calendar.get(Calendar.YEAR);
        binding.tvOldUser.setText(String.valueOf(yearNow - year));

        binding.tvGenderUser.setVisibility(View.VISIBLE);
        binding.tvOldUser.setVisibility(View.VISIBLE);

        setDataTvToEt();
    }

    public void setIfoEdit(EditText editText, TextView textView){
        if(editText.length() != 0){
           textView.setText(editText.getText().toString());
           textView.setVisibility(View.VISIBLE);
        }
    }

    public void setDataTvToEt(){
        binding.etNameUser.setText(binding.tvNameUser.getText().toString());
        binding.etNicknameUser.setText(binding.tvNicknameUser.getText().toString());
        binding.etAddressUser.setText(binding.tvAddressUser.getText().toString());
        binding.etPhoneUser.setText(binding.tvPhoneUser.getText().toString());
    }

    public void setDate(){

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int _year, int _month, int _day) {
                int yearNow = calendar.get(Calendar.YEAR);
                binding.tvOldUser.setText(String.valueOf(yearNow - year));
                day = _day;
                month = _month;
                year = _year;
                setIfoAllEdit();
            }
        };


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, dateSetListener, year, month, day);
        datePickerDialog.show();
    }
}
