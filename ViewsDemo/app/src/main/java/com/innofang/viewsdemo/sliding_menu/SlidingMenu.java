package com.innofang.viewsdemo.sliding_menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Author: Inno Fang
 * Time: 2017/9/1 20:28
 * Description:
 */


public class SlidingMenu extends HorizontalScrollView {

    private static final float radio = 0.3f; // 菜单栈屏幕宽度
    private final int mScreenWidth;
    private final int mMenuWidth;
    private boolean once = true;
    private boolean isOpen;

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = getScreenWidth(context);
        mMenuWidth = (int) (mScreenWidth * radio);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (once) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            wrapper.getChildAt(0).getLayoutParams().width = mScreenWidth;
            wrapper.getChildAt(1).getLayoutParams().width = mMenuWidth;
            once = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onCloseMenu();
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (Math.abs(scrollX) > mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    onOpenMenu();
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    public void closeMenu() {
        this.smoothScrollTo(0, 0);
        isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    private void onOpenMenu() {
        View view = this;
        while (true) {
            view = (View) view.getParent();
            if (view instanceof RecyclerView) {
                break;
            }
        }
        ((SlidingMenuAdapter)((RecyclerView)view).getAdapter()).onOpenMenu(this);
        isOpen = true;
    }

    private void onCloseMenu() {
        if (!isOpen) {
            View view = this;
            while (true) {
                view = (View) view.getParent();
                if (view instanceof RecyclerView) {
                    break;
                }
            }
            ((SlidingMenuAdapter)((RecyclerView)view).getAdapter()).onCloseMenu();
        }
    }

    private int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
