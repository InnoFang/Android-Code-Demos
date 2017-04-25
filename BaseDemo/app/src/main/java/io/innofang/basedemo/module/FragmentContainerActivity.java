package io.innofang.basedemo.module;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Author: Inno Fang
 * Description: Activity 托管 Fragment 代码封装
 */


public abstract class FragmentContainerActivity extends BaseActivity {

    private static final String TAG = "FragmentContainerActivi";

    private FragmentManager mFragmentManager;
    private Fragment mFragment;

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
        mFragment = mFragmentManager.findFragmentById(getFragmentContainerId());

        if (null == mFragment) {
            mFragment = createFragment();
            mFragmentManager.beginTransaction()
                    .add(getFragmentContainerId(), mFragment)
                    .commit();
        }
    }

    /**
     * 切换需要托管的 Fragment
     *
     * @param fragment 需要托管的 Fragment
     */
    public void switchFragment(Fragment fragment) {
        if (mFragment == null
                || !fragment.getClass().getName().equals(mFragment.getClass().getName())) {
            mFragmentManager.beginTransaction()
                    .replace(getFragmentContainerId(), fragment)
                    .commit();
            mFragment = fragment;
        }
    }
}
