package com.innofang.viewsdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;

/**
 * Author: Inno Fang
 * Time: 2017/4/18 20:19
 * Description:
 */


public class ClockView extends View
        implements SurfaceHolder.Callback, Runnable {

    private Paint mArcPaint = new Paint();
    private Paint mTextPaint = new Paint();
    private int mHeight, mWidth;
    private boolean mDrawing = true;
    private SurfaceHolder mSurfaceHolder;
    private float mDailRadius = 400F;
    private float len = 20F;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mArcPaint.setStrokeWidth(10);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(50);
//        mSurfaceHolder = getHolder();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mDrawing = true;
//        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mDrawing = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        drawDial(canvas);
    }

    @Override
    public void run() {
        while (mDrawing) {
            Canvas canvas = mSurfaceHolder.lockCanvas();

            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawDial(Canvas canvas) {
        canvas.save();
        int timeIndex = 1;
        String timeText;
        for (int i = 0; i < 360; i += 6) {
            if (i % 30 == 0) {
                if (i == 0) {
                    timeText = "12";
                } else {
                    timeText = "" + timeIndex++;
                }
                mArcPaint.setColor(Color.RED);
                mTextPaint.setColor(Color.BLACK);
                canvas.drawLine(0, -mDailRadius, 0, -(mDailRadius + 2 * len), mArcPaint);
                canvas.drawText(timeText, -15 /*让数子尽量居中*/, -(mDailRadius + len * 5 / 2), mTextPaint);
            } else {
                mArcPaint.setColor(Color.GREEN);
                canvas.drawLine(0, -mDailRadius, 0, -(mDailRadius + len), mArcPaint);
            }
            canvas.rotate(6);
        }
        canvas.restore();
    }

}
