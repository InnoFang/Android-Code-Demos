package com.innofang.viewsdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Inno Fang
 * Time: 2017/4/23 10:49
 * Description:
 */


public class SpiderNetView extends View {

    private Paint mPaint = new Paint();
    private int mWidth, mHeight, mNetLength, mNetSegment;

    public SpiderNetView(Context context) {
        this(context, null);
    }

    public SpiderNetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint.setStrokeWidth(8);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mNetLength = Math.min(mWidth, mHeight) - 50;
        mNetSegment = mNetLength / 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.scale(1, -1);
        drawNet(canvas);
    }

    private void drawNet(Canvas canvas) {
        canvas.save();
        for (int i = 0; i < 360; i+=45) {
            canvas.drawLine(0, 0, 0, mNetLength, mPaint);
            canvas.drawLine(0,mNetSegment - 20, mNetLength - 20, mNetLength - 20, mPaint);
            canvas.drawLine(0,mNetSegment, mNetLength, mNetLength, mPaint);
            canvas.drawLine(0,mNetSegment + 20, mNetLength + 20, mNetLength + 20, mPaint);
            canvas.rotate(45);
        }
        canvas.restore();
    }
}
