package com.innofang.ndkdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/4/19 18:45
 * Description: RecyclerView适配器
 */


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mResultList;

    public ResultAdapter(Context context, List<String> resultList) {
        mContext = context;
        mResultList = resultList;
    }

    public void setResultList(List<String> resultList) {
        mResultList = resultList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindHolder(mResultList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.result_text_view);
        }

        public void bindHolder(String result, int position) {
            mTextView.setText(result);
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_item);
            animation.setStartOffset(position * 300);
            animation.setInterpolator(new AccelerateInterpolator());
            itemView.startAnimation(animation);
        }
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
