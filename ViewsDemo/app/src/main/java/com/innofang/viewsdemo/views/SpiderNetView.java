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

    private Paint mNetPaint = new Paint();
    private int mWidth, mHeight, mNetLength, mNetSegment;

    public SpiderNetView(Context context) {
        this(context, null);
    }

    public SpiderNetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mNetPaint.setStrokeWidth(5);
        mNetPaint.setAntiAlias(true);
        mNetPaint.setStyle(Paint.Style.STROKE);
        mNetPaint.setColor(Color.GRAY);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mNetLength = Math.min(mWidth, mHeight) / 2 - 120;
        mNetSegment = mNetLength / 4 - 8;
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
        float sqrt2div2 = (float) (Math.sqrt(2) / 2.0);
        float seg1 = mNetSegment - 30;
        float seg2 = mNetSegment * 2 - 20;
        float seg3 = mNetSegment * 3 + 20;
        float seg4 = mNetSegment * 4 + 30;
        for (int i = 0; i < 360; i += 45) {
            canvas.drawLine(0, 0, 0, mNetLength, mNetPaint);
            canvas.drawLine(0, seg1, seg1 * sqrt2div2, seg1 * sqrt2div2, mNetPaint);
            canvas.drawLine(0, seg2, seg2 * sqrt2div2, seg2 * sqrt2div2, mNetPaint);
            canvas.drawLine(0, seg3, seg3 * sqrt2div2, seg3 * sqrt2div2, mNetPaint);
            canvas.drawLine(0, seg4, seg4 * sqrt2div2, seg4 * sqrt2div2, mNetPaint);
            canvas.rotate(45);
        }
        canvas.restore();
    }
}
