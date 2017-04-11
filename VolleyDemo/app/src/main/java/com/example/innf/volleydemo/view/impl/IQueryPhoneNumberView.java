package com.example.innf.volleydemo.view.impl;

import com.example.innf.volleydemo.bean.PhoneNumberInfo;

/**
 * Author: Inno Fang
 * Time: 2016/12/23 22:38
 * Description:
 */

public interface IQueryPhoneNumberView {

    void showLoading();

    void hideLoading();

    void showError(String error);

    void setPhoneNumberInfo(PhoneNumberInfo phoneNumberInfo);

}
