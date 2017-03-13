package com.example.innf.beziertest.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author: Inno Fang
 * Time: 2017/1/12 18:24
 * Description:
 */

public class DrawPadView extends View {
    private static final String TAG = "DrawPadView";

    private Path mPath;
    private Paint mPaint;
    private float mX;
    private float mY;

    public DrawPadView(Context context) {
        super(context);
    }

    public DrawPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();
    }

    public DrawPadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mX = event.getX();
                mY = event.getY();
                mPath.moveTo(mX, mY);
                break;
            case MotionEvent.ACTION_MOVE:
                float x1 = event.getX();
                float y1 = event.getY();
                float cx = (x1 + mX) / 2;
                float cy = (y1 + mY) / 2;
                mPath.quadTo(mX, mY, cx, cy);
                mX = x1;
                mY = y1;
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
