package io.innofang.basedemo.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.innofang.basedemo.App;

/**
 * Author: Inno Fang
 * Time: 2017/6/4 10:37
 * Description:
 */



public abstract class BaseAdapter<VH extends BaseViewHolder<L>, L extends Object>
        extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    protected List<L> mList;

    public BaseAdapter(Context context) {
        this(context, new ArrayList<L>(0));
    }

    public BaseAdapter(Context context, List<L> list) {
        mContext = context;
        mList = list;
    }

    @LayoutRes
    public abstract int getItemLayoutResId();

    @NonNull
    public abstract RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType, View view);

    public List<L> getList() {
        return mList;
    }

    public void setList(List<L> list) {
        mList = list;
    }

    public void addItem(L l) {
        mList.add(l);
        notifyItemInserted(mList.size() - 1);
    }

    public void delItem(L l) {
        int position = mList.indexOf(l);
        if (-1 != position) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getInstance())
                .inflate(getItemLayoutResId(), parent, false);
        return (VH) createViewHolder(parent, viewType, view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindHolder(getList().get(position));
    }

    @Override
    public int getItemCount() {
        return getList() == null ? 0 : getList().size();
    }

}