package com.example.innf.mvpdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.innf.mvpdemo.R;
import com.example.innf.mvpdemo.contract.LoginContract;
import com.example.innf.mvpdemo.presenter.LoginPresenter;
import com.example.innf.mvpdemo.util.OnLoginListener;

/**
 * Author: Inno Fang
 * Time: 2017/1/8 22:55
 * Description:
 */

public class LoginFragment extends Fragment implements View.OnClickListener, LoginContract.View{
    private static final String TAG = "LoginFragment";

    private EditText mUsernameEditText,
                    mPasswordEditText;

    private LoginContract.Presenter mLoginPresenter;
    private Button mLoginButton;
    public static Fragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mUsernameEditText = (EditText) view.findViewById(R.id.edit_text_username);
        mPasswordEditText = (EditText) view.findViewById(R.id.edit_text_password);
        mLoginButton = (Button) view.findViewById(R.id.button_login);
        mLoginPresenter = new LoginPresenter(this);
        initEvent();
        return view;
    }

    private void initEvent() {
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                mLoginPresenter.login(getUsername(), getPassword(), new OnLoginListener() {
                    @Override
                    public void onSuccess() {
                        showToast("login success");
                    }

                    @Override
                    public void onFailed() {
                        showToast("login failed");
                    }
                });
                break;
        }
    }

    @Override
    public String getUsername() {
        return mUsernameEditText.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPasswordEditText.getText().toString().trim();
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mLoginPresenter){
            mLoginPresenter.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mLoginPresenter){
            mLoginPresenter.destroy();
        }
    }
}
