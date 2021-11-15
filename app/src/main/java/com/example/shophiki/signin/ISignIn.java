package com.example.shophiki.signin;

public interface ISignIn {
    void onSuccessful(String title, String description);
    void onError(String title, String description);
}
