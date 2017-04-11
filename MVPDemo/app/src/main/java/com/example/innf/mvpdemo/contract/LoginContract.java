package com.example.innf.mvpdemo.contract;

import com.example.innf.mvpdemo.BasePresenter;
import com.example.innf.mvpdemo.BaseView;
import com.example.innf.mvpdemo.util.OnLoginListener;

/**
 * Author: Inno Fang
 * Time: 2017/1/8 22:44
 * Description:
 */

public class LoginContract {

    public interface Presenter extends BasePresenter{

        void login(String username, String password, OnLoginListener loginListener);

    }

    public interface View extends BaseView<LoginContract.Presenter>{

        String getUsername();

        String getPassword();

        void showToast(String content);

    }
}
