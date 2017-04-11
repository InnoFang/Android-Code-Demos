package com.example.innf.mvpdemo.presenter;

import android.util.Log;

import com.example.innf.mvpdemo.contract.LoginContract;
import com.example.innf.mvpdemo.model.LoginModel;
import com.example.innf.mvpdemo.model.impl.LoginModelImpl;
import com.example.innf.mvpdemo.util.OnLoginListener;

/**
 * Author: Inno Fang
 * Time: 2017/1/8 23:16
 * Description:
 */

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";

    private LoginContract.View mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginView.setPresenter(this);
        mLoginModel = new LoginModelImpl();
    }


    @Override
    public void login(String username, String password, OnLoginListener loginListener) {
        mLoginModel.login(username, password, loginListener);
    }

    @Override
    public void start() {
        Log.i(TAG, "start: ");
    }

    @Override
    public void destroy() {
        Log.i(TAG, "destroy: ");
    }
}
