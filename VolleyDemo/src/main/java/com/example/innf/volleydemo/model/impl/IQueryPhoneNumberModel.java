package com.example.innf.volleydemo.model.impl;

import com.example.innf.volleydemo.presenter.impl.OnQueryPhoneNumberListener;

/**
 * Author: Inno Fang
 * Time: 2016/12/23 22:42
 * Description:
 */

public interface IQueryPhoneNumberModel {
    void loadPhoneNumberInfo(String phoneNumber, OnQueryPhoneNumberListener listener);
}
