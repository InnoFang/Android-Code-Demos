package com.juhezi.mvptest;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Author: Inno Fang
 * Time: 2017/1/9 00:02
 * Description:
 * Activity 托管 Fragment 模板
 */

public abstract class SingleFragmentActivity extends AppCompatActivity{

    protected abstract Fragment createFragment();

    protected void init(){}
    /**
     * 可以设置一个只有FrameLayout的xml文件作为默认的Fragment容器，
     * 若子类有更好的容器可以重写该方法，若没有则可以直接用默认的容器
     * 假设布局文件名字为activity_fragment.xml
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    @IdRes
    protected abstract int getFragmentContainerId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(getFragmentContainerId());

        if (null == fragment) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(getFragmentContainerId(), fragment)
                    .commit();
        }
        init();
    }
}
