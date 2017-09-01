package com.innofang.viewsdemo.sliding_menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.innofang.viewsdemo.R;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/9/1 20:46
 * Description:
 */


public class SlidingMenuAdapter extends RecyclerView.Adapter<SlidingMenuViewHolder> {

    private List<String> mData;
    private Context mContext;
    private SlidingMenu mMenu;

    public SlidingMenuAdapter(List<String> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public SlidingMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SlidingMenuViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_sliding_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(final SlidingMenuViewHolder holder, final int position) {
        holder.bindHolder(this, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void onOpenMenu(SlidingMenu menu) {
        mMenu = menu;
    }

    public void onCloseMenu() {
        if (null != mMenu && mMenu.isOpen()) {
            mMenu.closeMenu();
        }
    }

}
