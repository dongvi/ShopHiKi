package com.example.shophiki.signin;

import android.content.Context;

import com.example.shophiki.R;
import com.example.shophiki.sql.SQLHelper;
import com.example.shophiki.User;

import java.util.List;

public class SignInPresenter {
    ISignIn iSignIn;
    Context context;
    boolean ok;
    List<User> users;
    User user;

    public SignInPresenter(ISignIn iSignIn, Context context) {
        this.iSignIn = iSignIn;
        this.context = context;
    }


    public void onSignIn(String username, String password, boolean remember, SQLHelper sqlHelper){
        users = sqlHelper.getAllUser();
        for (User x : users)
            if(username.compareToIgnoreCase(x.getUsername()) == 0 && password.equals(x.getPassword())) {
                iSignIn.onSuccessful(context.getString(R.string.tv_title_successful_signin), context.getString(R.string.tv_description_successful_signin));
                ok = true;
                user = x;
            }

        if(ok != true) {
            iSignIn.onError(context.getString(R.string.tv_title_error_signin), context.getString(R.string.tv_description_error_signin));
            ok = false;
        }
    }


    public boolean isOk() {
        return ok;
    }

    public User getUser() {
        return user;
    }
}
