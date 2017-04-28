package io.innofang.basedemo.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Inno Fang
 * Description: Base Operation of Fragment
 */


public abstract class BaseFragment extends Fragment {

    protected View mView;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void createView(View view, Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        createView(mView, savedInstanceState);
        return mView;
    }

    public View find(@IdRes int id) {
        return mView.findViewById(id);
    }
}
