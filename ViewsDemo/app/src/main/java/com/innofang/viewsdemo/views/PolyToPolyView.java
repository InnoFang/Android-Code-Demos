package com.innofang.viewsdemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.innofang.viewsdemo.R;

/**
 * Author: Inno Fang
 * Time: 2017/4/15 13:10
 * Description:
 */


public class PolyToPolyView extends View {

    private int mPointCount = 0;
    private int triggerRadius = 180;

    private Bitmap mBitmap;
    private Matrix mMatrix;

    private float[] mSrc = new float[8];
    private float[] mDst = new float[8];

    private Paint mPointPaint;

    public PolyToPolyView(Context context) {
        this(context, null, 0);
    }

    public PolyToPolyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PolyToPolyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.umr);
        float[] src = {
                0, 0,
                mBitmap.getWidth(), 0,
                mBitmap.getWidth(), mBitmap.getHeight(),
                0, mBitmap.getHeight()
        };
        mSrc = src.clone();
        mDst = src.clone();

        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setStrokeWidth(50);
        mPointPaint.setColor(Color.RED);
        mPointPaint.setStrokeCap(Paint.Cap.ROUND);

        mMatrix = new Matrix();
        mMatrix.setPolyToPoly(mSrc, 0, mDst, 0, 4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float tempX = event.getX();
                float tempY = event.getY();

                // 根据触控位置修改mDst
                for (int i = 0; i < mPointCount * 2; i += 2) {
                    if (Math.abs(tempX - mDst[i]) <= triggerRadius
                            && Math.abs(tempY - mDst[i + 1]) <= triggerRadius) {
                        mDst[i] = tempX - 100;
                        mDst[i + 1] = tempY - 100;
                        break; // 防止两点位置重合
                    }
                }
                resetPolyMatrix(mPointCount);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);

        // 根据Matrix绘制一个变换后的图片
        mMatrix.postScale(0.5f, 0.5f);
        canvas.drawBitmap(mBitmap, mMatrix, null);

        float[] dst = new float[8];
        mMatrix.mapPoints(dst, mSrc);

        for (int i = 0; i < mPointCount * 2; i += 2) {
            canvas.drawPoint(dst[i], dst[i + 1], mPointPaint);
        }
    }

    private void resetPolyMatrix(int pointCount) {
        mMatrix.reset();
        mMatrix.setPolyToPoly(mSrc, 0, mDst, 0, pointCount);
    }

    public void setPointCount(int pointCount) {
        mPointCount = pointCount > 4 || pointCount < 0 ? 4 : pointCount;
        mDst = mSrc.clone();
        resetPolyMatrix(mPointCount);
        invalidate();
    }
}
