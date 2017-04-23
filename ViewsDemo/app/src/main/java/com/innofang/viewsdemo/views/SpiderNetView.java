package com.innofang.viewsdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Author: Inno Fang
 * Time: 2017/4/23 10:49
 * Description:
 */


public class SpiderNetView extends View {

    private static final String TAG = "SpiderNetView";

    private final float SQRT_2_DIV_2 = (float) (Math.sqrt(2) / 2.0); // 根号2 / 2
    private Paint mNetPaint = new Paint();      // 画网
    private Paint mPointPaint = new Paint();    // 画点
    private Paint mFillPaint = new Paint();     // 填充
    private Paint mTextPaint = new Paint();     // 画文字
    private int mWidth, mHeight, mNetLength, mNetSegment;

    private PointF[] mUnitPointFs = new PointF[8];  // 每根网上的单位坐标
    private float[] mOffsets = new float[8];        // 每根网上的坐标偏移

    public SpiderNetView(Context context) {
        this(context, null);
    }

    public SpiderNetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mNetPaint.setStrokeWidth(5);
        mNetPaint.setAntiAlias(true);
        mNetPaint.setStyle(Paint.Style.STROKE);
        mNetPaint.setColor(Color.GRAY);

        mPointPaint.setStrokeWidth(30);
        mPointPaint.setAntiAlias(true);
        mPointPaint.setStyle(Paint.Style.STROKE);
        mPointPaint.setColor(Color.BLUE);
        mPointPaint.setStrokeCap(Paint.Cap.ROUND);

        mFillPaint.setStrokeWidth(5);
        mFillPaint.setColor(Color.parseColor("#866793a2"));
        mFillPaint.setStyle(Paint.Style.FILL);

        mTextPaint.setTextSize(50);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(Color.BLACK);

        mUnitPointFs[0] = new PointF(0, -1);
        mUnitPointFs[1] = new PointF(SQRT_2_DIV_2, -SQRT_2_DIV_2);
        mUnitPointFs[2] = new PointF(1, 0);
        mUnitPointFs[3] = new PointF(SQRT_2_DIV_2, SQRT_2_DIV_2);
        mUnitPointFs[4] = new PointF(0, 1);
        mUnitPointFs[5] = new PointF(-SQRT_2_DIV_2, SQRT_2_DIV_2);
        mUnitPointFs[6] = new PointF(-1, 0);
        mUnitPointFs[7] = new PointF(-SQRT_2_DIV_2, -SQRT_2_DIV_2);

        initData();
    }

    private void initData() {
        Random random = new Random();
        for (int i = 0; i < mOffsets.length; i++) {
            mOffsets[i] = random.nextInt(100);
            Log.i(TAG, "initData: " + mOffsets[i]);
        }
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
        drawNet(canvas);
        drawPointsAndFill(canvas);
    }

    private void drawNet(Canvas canvas) {
        canvas.save();

        float seg1 = mNetSegment - 30;
        float seg2 = mNetSegment * 2 - 20;
        float seg3 = mNetSegment * 3 + 20;
        float seg4 = mNetSegment * 4 + 30;
        for (int i = 0; i < 360; i += 45) {
            canvas.drawLine(0, 0, 0, -mNetLength, mNetPaint);
            canvas.drawText(String.valueOf((char) ('A' + i / 45)), -10, -(mNetLength + 20), mTextPaint);
            canvas.drawLine(0, -seg1, -(seg1 * SQRT_2_DIV_2), -(seg1 * SQRT_2_DIV_2), mNetPaint);
            canvas.drawLine(0, -seg2, -(seg2 * SQRT_2_DIV_2), -(seg2 * SQRT_2_DIV_2), mNetPaint);
            canvas.drawLine(0, -seg3, -(seg3 * SQRT_2_DIV_2), -(seg3 * SQRT_2_DIV_2), mNetPaint);
            canvas.drawLine(0, -seg4, -(seg4 * SQRT_2_DIV_2), -(seg4 * SQRT_2_DIV_2), mNetPaint);
            canvas.rotate(45);
        }
        canvas.restore();
    }

    private void drawPointsAndFill(Canvas canvas) {
        Path path = new Path();

        path.moveTo(mUnitPointFs[0].x * mOffsets[0] / 100 * mNetLength,
                mUnitPointFs[0].y * mOffsets[0] / 100 * mNetLength);

        for (int i = 0; i < mUnitPointFs.length; i++) {
            /* draw point */
            canvas.drawPoint(mUnitPointFs[i].x * mOffsets[i] / 100 * mNetLength,
                    mUnitPointFs[i].y * mOffsets[i] / 100 * mNetLength,
                    mPointPaint);
            /* draw line */
            if (i + 1 != mUnitPointFs.length) {
                path.lineTo(mUnitPointFs[i + 1].x * mOffsets[i + 1] / 100 * mNetLength,
                        mUnitPointFs[i + 1].y * mOffsets[i + 1] / 100 * mNetLength);
            } else {
               path.lineTo(mUnitPointFs[0].x * mOffsets[0] / 100 * mNetLength,
                       mUnitPointFs[0].y * mOffsets[0] / 100 * mNetLength);
            }

        }
        path.close();
        canvas.drawPath(path, mFillPaint);
    }


}
