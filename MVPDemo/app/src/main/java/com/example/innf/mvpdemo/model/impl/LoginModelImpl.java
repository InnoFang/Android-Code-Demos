package com.example.innf.mvpdemo.model.impl;

import com.example.innf.mvpdemo.model.LoginModel;
import com.example.innf.mvpdemo.util.OnLoginListener;

/**
 * Author: Inno Fang
 * Time: 2017/1/8 23:47
 * Description:
 */

public class LoginModelImpl implements LoginModel{
    private static final String TAG = "LoginModelImpl";

    @Override
    public void login(String username, String password, OnLoginListener loginListener) {
        if (username.equals(password)) {
            loginListener.onSuccess();
        } else {
            loginListener.onFailed();
        }
    }
}
