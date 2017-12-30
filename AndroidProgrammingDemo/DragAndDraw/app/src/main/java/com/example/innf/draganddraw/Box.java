package com.example.innf.draganddraw;

import android.graphics.PointF;

/**
 * Author: Inno Fang
 * Time: 2016/7/17 16:06
 * Description: 用于表示矩形框的数据
 */

public class Box {
    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF origin) {
        mOrigin = origin;
        mCurrent = origin;
    }

    public PointF getOrigin() {
        return mOrigin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }
}
