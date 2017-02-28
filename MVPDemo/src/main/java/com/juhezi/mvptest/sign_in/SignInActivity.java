package com.juhezi.mvptest.sign_in;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.juhezi.mvptest.R;
import com.juhezi.mvptest.SingleFragmentActivity;

/**
 * 简化Activity托管Fragment
 * 只需要重写三个方法即可
 */

public class SignInActivity extends SingleFragmentActivity{

    private Toolbar mToolbar;
    private ActionBar mActionBar;

    @Override
    protected int getLayoutResId() {
        // 若子类有更好的容器视图则可以返回新的容器布局文件
         return R.layout.sign_in_act;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected Fragment createFragment() {
        // 创建指定Fragment
        return SignInFragment.newInstance();
    }

    @Override
    protected void init() {
        super.init();
        // 这里将presenter的初始化移到了Fragment
        initActionBar();

    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.tb_sign_in);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
    }


}
