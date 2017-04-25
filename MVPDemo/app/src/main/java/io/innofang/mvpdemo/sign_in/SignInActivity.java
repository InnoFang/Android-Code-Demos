package io.innofang.mvpdemo.sign_in;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import io.innofang.mvpdemo.R;
import io.innofang.mvpdemo.SingleFragmentActivity;


/**
 * 简化Activity托管Fragment
 * 只需要重写三个方法即可
 */

public class SignInActivity extends SingleFragmentActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这里将presenter的初始化移到了Fragment
        initActionBar();
    }

    @Override
    protected int getLayoutResId() {
        // 若子类有更好的容器视图则可以返回新的容器布局文件
         return R.layout.activity_sign_in;
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

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.tb_sign_in);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
    }


}
