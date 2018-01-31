package com.example.innf.pathmeasuretest.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Author: Inno Fang
 * Time: 2017/1/13 10:21
 * Description:
 */

public class PathPosTanView extends View implements View.OnClickListener {
    private static final String TAG = "PathPosTanView";

    private Path mPath;
    private float[] mPos; // 用于存放取出来的点的坐标
    private float[] mTan; // 用于取出当前点的运动趋势的坐标
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private ValueAnimator mValueAnimator;
    private float mCurrentValue;

    public PathPosTanView(Context context) {
        super(context);
    }

    public PathPosTanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath.addCircle(0, 0, 200, Path.Direction.CW);

        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(mPath, false);

        mPos = new float[2];
        mTan = new float[2];

        setOnClickListener(this);

        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setDuration(3000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    public PathPosTanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathMeasure.getPosTan(mCurrentValue * mPathMeasure.getLength(), mPos, mTan);
        float degree = (float) (Math.atan2(mTan[1], mTan[0]) * 180 / Math.PI);

        canvas.save();
        canvas.translate(400, 400); // 将画布平移到400，400的位置
        canvas.drawPath(mPath, mPaint);
        canvas.drawCircle(mPos[0], mPos[1], 10, mPaint);
        canvas.rotate(degree);
        canvas.drawLine(0, -200, 300, -200, mPaint);
        canvas.restore();
    }

    @Override
    public void onClick(View v) {
        mValueAnimator.start();
    }
}
