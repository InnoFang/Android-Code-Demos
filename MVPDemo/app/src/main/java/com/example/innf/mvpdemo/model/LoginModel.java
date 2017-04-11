package com.example.innf.mvpdemo.model;

import com.example.innf.mvpdemo.util.OnLoginListener;

/**
 * Author: Inno Fang
 * Time: 2017/1/8 23:39
 * Description:
 */

public interface LoginModel {
    void login(String username, String password, OnLoginListener loginListener);
}
