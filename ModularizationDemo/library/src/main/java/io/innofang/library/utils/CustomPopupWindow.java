package io.innofang.library.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Author: Inno Fang
 * Time: 2017/7/13 20:27
 * Description: Custom PopupWindow
 */


public class CustomPopupWindow {

    private PopupWindow mPopupWindow;
    private View mContentView;
    private Context mContext;

    public static class Builder {

        private Context context;
        private int contentViewId;
        private int width;
        private int height;
        private boolean focus = false;
        private boolean outsideCancel = false;
        private int animStyle = 0;
        private float elevation = 0.0F;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setContentView(int contentViewId) {
            this.contentViewId = contentViewId;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setFocus(boolean focus) {
            this.focus = focus;
            return this;
        }

        public Builder setOutsideCancel(boolean outsideCancel) {
            this.outsideCancel = outsideCancel;
            return this;
        }

        public Builder setAnimStyle(int animStyle) {
            this.animStyle = animStyle;
            return this;
        }

        public Builder setElevation(float elevation) {
            this.elevation = elevation;
            return this;
        }

        public CustomPopupWindow build() {
            return new CustomPopupWindow(this);
        }
    }

    private CustomPopupWindow(Builder builder) {

        if (builder.contentViewId == 0 || builder.width == 0 || builder.height == 0) {
            throw new IllegalArgumentException("The parameter is incomplete, be sure to contain contentView, width and height.");
        }

        mContext = builder.context;
        mContentView = LayoutInflater.from(mContext).inflate(builder.contentViewId, null);
        mPopupWindow = new PopupWindow(mContentView, builder.width, builder.height, builder.focus);

        if (Build.VERSION.SDK_INT >= 21) mPopupWindow.setElevation(builder.elevation);
        mPopupWindow.setOutsideTouchable(builder.outsideCancel);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(builder.animStyle);
    }

    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public boolean isShowing(){
        if (null != mPopupWindow) {
           return mPopupWindow.isShowing();
        }
        return false;
    }

    public void dismiss() {
        if (null != mPopupWindow) {
            mPopupWindow.dismiss();
        }
    }


    public View findView(int viewId) {
        if (null != mPopupWindow) {
            return this.mContentView.findViewById(viewId);
        }
        return null;
    }

    public CustomPopupWindow showAtLocation(int rootViewId, int gravity, int x, int y) {
        if (null != mPopupWindow) {
            View rootView = LayoutInflater.from(mContext).inflate(rootViewId, null);
            mPopupWindow.showAtLocation(rootView, gravity, x, y);
        }
        return this;
    }

    public CustomPopupWindow showAsDropDown(int targetViewId, int gravity, int offX, int offY) {
        if (null != mPopupWindow && Build.VERSION.SDK_INT >= 19) {
            View targetView = LayoutInflater.from(mContext).inflate(targetViewId, null);
            mPopupWindow.showAsDropDown(targetView, gravity, offX, offY);
        }
        return this;
    }

    public CustomPopupWindow showAsDropDown(View targetView, int gravity, int offX, int offY) {
        if (null != mPopupWindow && Build.VERSION.SDK_INT >= 19) {
            mPopupWindow.showAsDropDown(targetView, gravity, offX, offY);
        }
        return this;
    }

    public void setOnFocusListener(int viewId, View.OnFocusChangeListener listener) {
        View view = findView(viewId);
        view.setOnFocusChangeListener(listener);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = findView(viewId);
        view.setOnClickListener(listener);
    }

    public void setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = findView(viewId);
        view.setOnLongClickListener(listener);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        if (null != mPopupWindow) {
            mPopupWindow.setOnDismissListener(listener);
        }
    }

}
