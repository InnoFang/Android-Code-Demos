package com.innofang.viewsdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Inno Fang
 * Time: 2017/4/12 14:18
 * Description:
 */


public class BezierHeartView extends View {

    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private Paint mPaint;
    private int mCenterX, mCenterY;

    private PointF mCenter = new PointF(0, 0);
    private float mCircleRadius = 200;                  // 圆的半径
    private float mDifference = mCircleRadius * C;      // 圆型的控制点与数据点的差值

    private float[] mData = new float[8];               // 顺时针记录绘制圆形的四个数据点
    private float[] mCtrl = new float[16];              // 顺时针记录绘制圆形的八个控制点

    private float mDuration = 1000;                     // 变化总时长
    private float mCurrent = 0;                         // 当前已进行时长
    private float mCount = 100;                         // 将时长总共划分多少份
    private float mPiece = mDuration / mCount;          // 每一份的时长

    private boolean mShowCoordinateSystem, mShowAuxiliaryLine;

    public BezierHeartView(Context context) {
        this(context, null);
    }

    public BezierHeartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
//        mPaint.setTextSize(60);

        // 初始化数据点
        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        // 初始化控制点
        mCtrl[0] = mData[0] + mDifference;
        mCtrl[1] = mData[1];

        mCtrl[2] = mData[2];
        mCtrl[3] = mData[3] + mDifference;

        mCtrl[4] = mData[2];
        mCtrl[5] = mData[3] - mDifference;

        mCtrl[6] = mData[4] + mDifference;
        mCtrl[7] = mData[5];

        mCtrl[8] = mData[4] - mDifference;
        mCtrl[9] = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShowCoordinateSystem)
            drawCoordinateSystem(canvas);           // 绘制坐标系

        canvas.translate(mCenterX, mCenterY);   // 将坐标系移至中央
        canvas.scale(1, -1);                    // 翻转Y轴

        if (mShowAuxiliaryLine)
            drawAuxiliaryLine(canvas);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(mData[0], mData[1]);

        path.cubicTo(mCtrl[0], mCtrl[1], mCtrl[2], mCtrl[3], mData[2], mData[3]);
        path.cubicTo(mCtrl[4], mCtrl[5], mCtrl[6], mCtrl[7], mData[4], mData[5]);
        path.cubicTo(mCtrl[8], mCtrl[9], mCtrl[10], mCtrl[11], mData[6], mData[7]);
        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15], mData[0], mData[1]);

        canvas.drawPath(path, mPaint);

        mCurrent += mPiece;
        if (mCurrent < mDuration) {
            /* 圆（爱心）顶部 x*/
            mData[1] -= 115 / mCount;
            /* 圆（爱心）控制点底部 x, y */
            mCtrl[7] += 80 / mCount;
            mCtrl[9] += 80 / mCount;

            /* 圆（爱心）两边 x */
            mData[2] -= 10 / mCount;
            mData[6] += 10 / mCount;

            /* 圆（爱心）两边下侧控制点 x */
            mCtrl[4] -= 30 / mCount;
            mCtrl[10] += 30 / mCount;

            postInvalidateDelayed((long) mPiece);
        }
    }

    /* 画辅助线 */
    private void drawAuxiliaryLine(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(10);

        // 描点
        for (int i = 0; i < mData.length; i += 2) {
            canvas.drawPoint(mData[i], mData[i + 1], mPaint);
        }

        for (int i = 0; i < mCtrl.length; i += 2) {
            canvas.drawPoint(mCtrl[i], mCtrl[i + 1], mPaint);
        }

        // 画辅助线
        mPaint.setStrokeWidth(6);

        for (int i = 2, j = 2; i < 8; i += 2, j += 4) {
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j], mCtrl[j + 1], mPaint);
            canvas.drawLine(mData[i], mData[i + 1], mCtrl[j + 2], mCtrl[j + 3], mPaint);
        }
        canvas.drawLine(mData[0], mData[1], mCtrl[0], mCtrl[1], mPaint);
        canvas.drawLine(mData[0], mData[1], mCtrl[14], mCtrl[15], mPaint);
    }

    private void drawCoordinateSystem(Canvas canvas) {
        canvas.save();                          // 绘制坐标系之前，保存当前画布状态

        canvas.translate(mCenterX, mCenterY);   // 将坐标系移至中央
        canvas.scale(1, -1);                    // 翻转Y轴

        // 绘制坐标系辅助线
        Paint coorPaint = new Paint();

        coorPaint.setColor(Color.BLACK);
        coorPaint.setStrokeWidth(5);
        coorPaint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(0, -500, 0, 500, coorPaint);
        canvas.drawLine(500, 0, -500, 0, coorPaint);

        canvas.restore();                       //  回滚到上一次保存的状态
    }

    public void setShowCoordinateSystem(boolean showCoordinateSystem) {
        mShowCoordinateSystem = showCoordinateSystem;
    }

    public void setShowAuxiliaryLine(boolean showAuxiliaryLine) {
        mShowAuxiliaryLine = showAuxiliaryLine;
    }
}
