package com.innofang.recyclerviewdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innofang.recyclerviewdemo.R;
import com.innofang.recyclerviewdemo.helper.ItemTouchHelperAdapter;
import com.innofang.recyclerviewdemo.helper.ItemTouchHelperViewHolder;
import com.innofang.recyclerviewdemo.helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/3/13 15:55
 * Description:
 */


public class Adapter extends RecyclerView.Adapter<Adapter.GridHolder>
        implements ItemTouchHelperAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> mList = new ArrayList<>();
    private OnStartDragListener mDragListener;
    public Adapter(Context context, OnStartDragListener dragListener) {
        mDragListener = dragListener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void addAll(List<String> list) {
        mList = list;
    }

    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GridHolder(mLayoutInflater.inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(GridHolder holder, int position) {
        holder.bindHolder(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class GridHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        private TextView mTextView;

        public GridHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
        }

        public void bindHolder(String text) {
            mTextView.setText(text);
            mTextView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                            mDragListener.onStartDrag(GridHolder.this);
                    }
                    return false;
                }
            });
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
