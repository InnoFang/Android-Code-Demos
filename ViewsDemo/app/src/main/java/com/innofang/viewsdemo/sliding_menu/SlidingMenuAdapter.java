package com.innofang.viewsdemo.sliding_menu;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.innofang.viewsdemo.R;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/9/1 20:46
 * Description:
 */


public class SlidingMenuAdapter extends RecyclerView.Adapter<SlidingMenuAdapter.ViewHolder> {

    private List<String> mData;
    private Context mContext;

    private SlidingMenu mMenu;
    private OnMenuClickListener mOnMenuClickListener;

    public SlidingMenuAdapter(List<String> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_sliding_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.imageView.setBackgroundColor(Color.GREEN);
        holder.menuText.setText(mData.get(position));
        holder.menuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCloseMenu();
                boolean top;
                if (holder.menuText.getText().toString().equals("置顶")) {
                    holder.menuText.setText("取消置顶");
                    holder.imageView.setBackgroundColor(Color.YELLOW);
                    top = true;
                }else{
                    holder.menuText.setText("置顶");
                    holder.imageView.setBackgroundColor(Color.GREEN);
                    top = false;
                }
                if (mOnMenuClickListener != null) {
                    mOnMenuClickListener.onClick(position, top);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView menuText;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            menuText = (TextView) itemView.findViewById(R.id.menuText);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }

    }

    public interface OnMenuClickListener {
        void onClick(int position, boolean top);
    }

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        mOnMenuClickListener = onMenuClickListener;
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
