package com.innofang.databingdemo;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Author: Inno Fang
 * Time: 2017/2/19 10:32
 * Description:
 */

public class BindingViewHolder <T extends ViewDataBinding> extends RecyclerView.ViewHolder{

    private T mBinding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
