package io.innofang.mvvmdemo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Author: Inno Fang
 * Time: 2017/9/7 17:16
 * Description:
 */


public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

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
    }
}
