package com.example.innf.volleydemo.presenter;

import com.example.innf.volleydemo.bean.PhoneNumberInfo;
import com.example.innf.volleydemo.model.QueryPhoneNumberModel;
import com.example.innf.volleydemo.model.impl.IQueryPhoneNumberModel;
import com.example.innf.volleydemo.presenter.impl.IQueryPhoneNumberPresenter;
import com.example.innf.volleydemo.presenter.impl.OnQueryPhoneNumberListener;
import com.example.innf.volleydemo.view.impl.IQueryPhoneNumberView;

/**
 * Author: Inno Fang
 * Time: 2016/12/23 22:23
 * Description:
 */

public class QueryPhoneNumberPresenter implements OnQueryPhoneNumberListener, IQueryPhoneNumberPresenter{

    private IQueryPhoneNumberModel mQueryPhoneNumberModel;
    private IQueryPhoneNumberView mQueryPhoneNumberView;

    public QueryPhoneNumberPresenter(IQueryPhoneNumberView queryPhoneNumberView) {
        mQueryPhoneNumberView = queryPhoneNumberView;
        mQueryPhoneNumberModel = new QueryPhoneNumberModel();
    }

    @Override
    public void queryPhoneNumberInfo(String phoneNumber){
        mQueryPhoneNumberView.showLoading();
        mQueryPhoneNumberModel.loadPhoneNumberInfo(phoneNumber, this);
    }

    @Override
    public void onSuccess(PhoneNumberInfo phoneNumberInfo) {
        mQueryPhoneNumberView.hideLoading();
        mQueryPhoneNumberView.setPhoneNumberInfo(phoneNumberInfo);
    }

    @Override
    public void onError(String error) {
        mQueryPhoneNumberView.hideLoading();
        mQueryPhoneNumberView.showError(error);
    }
}
