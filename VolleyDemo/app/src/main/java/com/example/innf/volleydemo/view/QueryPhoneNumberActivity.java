package com.example.innf.volleydemo.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.innf.volleydemo.R;
import com.example.innf.volleydemo.app.MyApplication;
import com.example.innf.volleydemo.bean.PhoneNumberInfo;
import com.example.innf.volleydemo.constant.Constant;
import com.example.innf.volleydemo.presenter.QueryPhoneNumberPresenter;
import com.example.innf.volleydemo.view.impl.IQueryPhoneNumberView;

public class QueryPhoneNumberActivity extends AppCompatActivity implements View.OnClickListener, IQueryPhoneNumberView{

    private TextView mProvinceTextView,
            mCityTextView,
            mAreaCodeTextView,
            mZipTextView,
            mCompanyTextView;
    private EditText mInputPhoneNumberEditText;
    private Button mQueryPhoneNumberButton;
    private Dialog mLoadingDialog;
    private QueryPhoneNumberPresenter mPhoneNumberPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_phone_number_acitivity);
        initView();
    }

    private void initView() {
        mProvinceTextView = (TextView) findViewById(R.id.text_view_province);
        mCityTextView = (TextView) findViewById(R.id.text_view_city);
        mAreaCodeTextView = (TextView) findViewById(R.id.text_view_area_code);
        mZipTextView = (TextView) findViewById(R.id.text_view_zip);
        mCompanyTextView = (TextView) findViewById(R.id.text_view_company);
        mInputPhoneNumberEditText = (EditText) findViewById(R.id.input_phone_number_edit_text);
        mQueryPhoneNumberButton = (Button) findViewById(R.id.query_phone_number_button);
        mPhoneNumberPresenter = new QueryPhoneNumberPresenter(this);

        mLoadingDialog = new ProgressDialog(this);
        mLoadingDialog.setTitle("正在查询...");

        mQueryPhoneNumberButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.query_phone_number_button:
                mPhoneNumberPresenter.queryPhoneNumberInfo(mInputPhoneNumberEditText.getText().toString());
                break;
        }
    }

    /*
    Volley与Activity联动
     */
    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getHttpQueues().cancelAll(Constant.VOLLEY_TAG);
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPhoneNumberInfo(PhoneNumberInfo phoneNumberInfo) {
        mProvinceTextView.setText(phoneNumberInfo.getProvince());
        mCityTextView.setText(phoneNumberInfo.getCity());
        mAreaCodeTextView.setText(phoneNumberInfo.getAreacode());
        mZipTextView.setText(phoneNumberInfo.getZip());
        mCompanyTextView.setText(phoneNumberInfo.getCompany());
    }
}
