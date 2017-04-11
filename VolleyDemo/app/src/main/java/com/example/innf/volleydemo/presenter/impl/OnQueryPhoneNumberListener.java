package com.example.innf.volleydemo.presenter.impl;

import com.example.innf.volleydemo.bean.PhoneNumberInfo;

/**
 * Author: Inno Fang
 * Time: 2016/12/24 09:45
 * Description:
 */

public interface OnQueryPhoneNumberListener {

    void onSuccess(PhoneNumberInfo phoneNumberInfo);

    void onError(String error);
}
