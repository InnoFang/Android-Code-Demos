package com.innofang.callbackdemo.call_back_demo_1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innofang.callbackdemo.R;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/3/8 14:20
 * Description:
 */
public class ClickAdapter extends RecyclerView.Adapter<ClickAdapter.ViewHolder>{
    /*上下文对象*/
    private Context mContext;
    /*列表*/
    private List<String> mStringList;
    /*接口实例*/
    private OnClickItemListener mOnClickItemListener;
    public ClickAdapter(Context context, List<String> strings) {
        mContext = context;
        mStringList = strings;
    }
    public void setStringList(List<String> stringList) {
        mStringList = stringList;
    }
    /*设置接口实例*/
    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        mOnClickItemListener = onClickItemListener;
    }
    /*加载RecyclrView的item视图*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                /*item_recycler_view中只有一个水平视图，一副图片和一段文字*/
                .inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }
    /*绑定视图*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindHolder(mStringList.get(position));
    }
    /*获得item个数*/
    @Override
    public int getItemCount() {
        return mStringList.size();
    }
    /*ViewHolder逻辑*/
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
        }
        /*绑定视图方法，在Adapter的onBindViewHolder中调用*/
        public void bindHolder(final String text) {
            mTextView.setText(text);
            if (null != mOnClickItemListener) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*回调方法*/
                        mOnClickItemListener.onClick(text);
                    }
                });
            }
        }
    }
    /*回调接口*/
    interface OnClickItemListener{
        void onClick(String text);
    }
}