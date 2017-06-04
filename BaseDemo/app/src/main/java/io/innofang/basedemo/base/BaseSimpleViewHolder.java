package io.innofang.basedemo.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Author: Inno Fang
 * Time: 2017/6/4 10:51
 * Description:
 */


public class BaseSimpleViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mItemView;

    public BaseSimpleViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        mViews = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public static <T extends BaseSimpleViewHolder> T getViewHolder(Context context, ViewGroup parent, @LayoutRes int layoutId) {
        return (T) new BaseSimpleViewHolder(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int id) {
        View child = mViews.get(id);
        if (null == child) {
            child = itemView.findViewById(id);
            mViews.put(id, child);
        }
        return (T) child;
    }

    public View getItemView() {
        return mItemView;
    }

    public void setText(@IdRes int id, String text) {
        if (getView(id) instanceof TextView) {
            ((TextView) getView(id)).setText(text);
        }
    }

    public void setTextColor(@IdRes int id, @ColorInt int color) {
        if (getView(id) instanceof TextView) {
            ((TextView) getView(id)).setTextColor(color);
        }
    }

    public void setImageResource(@IdRes int id, @DrawableRes int resId) {
        if (getView(id) instanceof ImageView) {
            ((ImageView) getView(id)).setImageResource(resId);
        }
    }

    public void setImageDrawable(@IdRes int id, Drawable drawable) {
        if (getView(id) instanceof ImageView) {
            ((ImageView) getView(id)).setImageDrawable(drawable);
        }
    }

    public void setBackgroundColor(@IdRes int id, @ColorInt int color) {
        getView(id).setBackgroundColor(color);
    }

    public void setVisible(@IdRes int id, boolean visible) {
        getView(id).setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
    }

    public void setOnTouchListener(int viewId, View.OnTouchListener listener) {
        getView(viewId).setOnTouchListener(listener);
    }

    public void setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        getView(viewId).setOnLongClickListener(listener);
    }

}