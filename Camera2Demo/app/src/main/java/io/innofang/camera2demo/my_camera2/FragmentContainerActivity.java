package io.innofang.camera2demo.my_camera2;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Author: Inno Fang
 * Time: 2017/9/24 20:08
 * Description:
 */


/**
 * Author: Inno Fang
 * Description: Activity 托管 Fragment 代码封装
 */


public abstract class FragmentContainerActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    /**
     * 创建 Fragment 实例
     *
     * @return 需要托管的 Fragment 实例
     */
    protected abstract Fragment createFragment();

    /**
     * 获得托管 Fragment 的布局文件 Id
     *
     * @return 托管 Fragment 的布局文件 Id
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * 获得托管 Fragment 的 View 的 Id
     *
     * @return 托管 Fragment 的 View 的 Id
     */
    @IdRes
    protected abstract int getFragmentContainerId();


    /**
     * 托管单一 Fragment
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = mFragmentManager.findFragmentById(getFragmentContainerId());

        if (null == mCurrentFragment) {
            mCurrentFragment = createFragment();
            mFragmentManager.beginTransaction()
                    .add(getFragmentContainerId(), mCurrentFragment)
                    .commit();
        }
    }

    /**
     * 转换Fragment
     * 首先当前存在Fragment，并且当前Fragment不是要转换的Fragment，避免重复操作
     *
     * @param fragment 需要转换的Fargment
     */
    public void switchFragment(Fragment fragment) {
        if (mCurrentFragment == null
                || !fragment.getClass().getName().equals(mCurrentFragment.getClass().getName())) {
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in,
                            android.R.anim.fade_out);
            if (!fragment.isAdded()) { // 检查 fragment 是否被添加
                // 隐藏当前 mCurrentFragment，add fragment 到 Activity 中
                fragmentTransaction
                        .hide(mCurrentFragment)
                        .add(getFragmentContainerId(), fragment)
                        .commit();
                mCurrentFragment = fragment;
            } else {
                // 隐藏当前 mCurrentFragment，显示 fragment
                fragmentTransaction
                        .hide(mCurrentFragment)
                        .show(fragment)
                        .commit();
                mCurrentFragment = fragment;
            }
        }
    }

    /**
     * 转换 Fragment
     * 首先当前存在 Fragment，并且当前 Fragment 不是要转换的 Fragment，避免重复操作
     *
     * @param fragment 需要转换的Fargment
     */
    public void replaceFragment(Fragment fragment) {
        if (mCurrentFragment == null
                || !fragment.getClass().getName().equals(mCurrentFragment.getClass().getName())) {
            mFragmentManager.beginTransaction()
                    .replace(getFragmentContainerId(), fragment)
                    .commit();
            mCurrentFragment = fragment;
        }
    }
}