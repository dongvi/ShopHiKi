package com.example.shophiki.signup;

public interface ISignUp {
    void onSuccessful(String title, String description);
    void onError(String description, int type);
    void onBack(boolean ok);
}
