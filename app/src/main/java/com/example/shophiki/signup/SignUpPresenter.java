package com.example.shophiki.signup;

import android.content.Context;

import com.example.shophiki.R;

import java.util.Calendar;
import java.util.regex.Pattern;

public class SignUpPresenter {
    ISignUp iSignUp;
    Context context;
    boolean ok;

    public SignUpPresenter(ISignUp iSignUp, Context context) {
        this.iSignUp = iSignUp;
        this.context = context;
    }

    public void onSignUp(String name, String gender, String birthday, String phoneNumber, String username, String password, String cfPassword, boolean agree){
        if(name.length() == 0)
            iSignUp.onError(context.getString(R.string.tv_error_name_empty), 1);
        else if(phoneNumber.length() == 0)
            iSignUp.onError(context.getString(R.string.tv_error_phone_empty), 2);
        else if(birthday.length() == 0)
            iSignUp.onError(context.getString(R.string.tv_error_birthday_empty), 3);
        else if(username.length() == 0)
            iSignUp.onError(context.getString(R.string.tv_error_username_empty), 4);
        else if(password.length() == 0)
            iSignUp.onError(context.getString(R.string.tv_error_password_empty), 5);
        else if(cfPassword.length() == 0)
            iSignUp.onError(context.getString(R.string.tv_error_confirm_password_empty), 6);
        else{
            if(checkName(name))
                if(checkPhoneNumber(phoneNumber))
                    if(checkBirthDay(birthday))
                        if(checkUsername(username))
                            if(checkPassword(password, cfPassword)){
                                if(agree){
                                    iSignUp.onSuccessful(context.getString(R.string.tv_title_successful_signup), context.getString(R.string.tv_description_successful_signup));
                                    ok = true;
                                }
                                else{
                                    iSignUp.onError(context.getString(R.string.tv_error_agree), 0);
                                    ok = false;
                                }
                            }
        }
    }


    public boolean checkName(String name){
        String regrexName = "[A-Za-z \\p{L}]+";
        if(name.length() < 5) {
            iSignUp.onError(context.getString(R.string.tv_error_name_length), 1);
            return false;
        }
        else if(!Pattern.matches(regrexName, name)) {
            iSignUp.onError(context.getString(R.string.tv_error_name_invalid), 1);
            return false;
        }
        else
            return true;
    }

    public boolean checkPhoneNumber(String phone){
        String regrexPhone = "[+]?[0-9]+";
        if(phone.length() < 9) {
            iSignUp.onError(context.getString(R.string.tv_error_phone_length), 2);
            return false;
        }
        else if(!Pattern.matches(regrexPhone, phone)){
            iSignUp.onError(context.getString(R.string.tv_error_phone_invalid), 2);
            return false;
        }
        else
            return true;
    }

    public boolean checkBirthDay(String birthDay){
        String year = birthDay.substring(10, 14);
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.YEAR) - Integer.parseInt(year) < 12){
            iSignUp.onError(context.getString(R.string.tv_error_birthday_invalid), 3);
            return false;
        }
        return true;
    }

    public boolean checkUsername(String username){
        String regrexUser = "[A-Za-z0-9]+";
        if(username.length() < 5) {
            iSignUp.onError(context.getString(R.string.tv_error_username_length), 4);
            return false;
        }
        else if(!Pattern.matches(regrexUser, username)) {
            iSignUp.onError(context.getString(R.string.tv_error_username_invalid), 4);
            return false;
        }
        else
            return true;
    }

    public boolean checkPassword(String password, String cfPassword){
        String regrexLowercase = "[^a-z]+";
        String regrexUppercase = "[^A-Z]+";
        String regrexNumber = "[^0-9]+";
        String regrexSpecials = "\\w+";
        String regrexSpaces = "[^ ]+";

        if(password.length() < 6){
            iSignUp.onError(context.getString(R.string.tv_error_password_length), 5);
            return false;
        }
        else if(Pattern.matches(regrexLowercase, password)) {
            iSignUp.onError(context.getString(R.string.tv_error_password_lowercase), 5);
            return false;
        }
        else if(Pattern.matches(regrexUppercase, password)){
            iSignUp.onError(context.getString(R.string.tv_error_password_uppercase), 5);
            return false;
        }
        else if(Pattern.matches(regrexNumber, password)){
            iSignUp.onError(context.getString(R.string.tv_error_password_number), 5);
            return false;
        }
        else if(Pattern.matches(regrexSpecials, password)){
            iSignUp.onError(context.getString(R.string.tv_error_password_special_character), 5);
            return false;
        }
        else if(!Pattern.matches(regrexSpaces, password)){
            iSignUp.onError(context.getString(R.string.tv_error_password_spaces), 5);
            return false;
        }
        else {
            if (password.equals(cfPassword))
                return true;
            else{
                iSignUp.onError(context.getString(R.string.tv_error_confirm_password_incorrect), 6);
                return false;
            }
        }

    }

    public boolean isOk() {
        return ok;
    }
}
