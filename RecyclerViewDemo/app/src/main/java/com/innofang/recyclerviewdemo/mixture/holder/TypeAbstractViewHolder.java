package com.innofang.recyclerviewdemo.mixture.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author: Inno Fang
 * Time: 2017/3/13 14:06
 * Description:
 */


public abstract class TypeAbstractViewHolder<T> extends RecyclerView.ViewHolder {
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(T model);
}
