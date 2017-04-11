package com.example.innf.mvpdemo.view.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.innf.mvpdemo.R;
import com.example.innf.mvpdemo.view.base.SingleFragmentActivity;
import com.example.innf.mvpdemo.view.fragment.LoginFragment;

public class LoginActivity extends SingleFragmentActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
    }

    @Override
    protected void init() {
        super.init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
    }
}
