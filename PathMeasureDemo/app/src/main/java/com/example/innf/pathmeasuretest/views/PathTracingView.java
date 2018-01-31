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
 * Time: 2017/1/12 21:40
 * Description:
 */

public class PathTracingView extends View {
    private static final String TAG = "PathTracingView";

    private Path mDst;
    private Path mPath;
    private Paint mPaint;
    private float mLength;
    private float mAnimValue;
    private PathMeasure mPathMeasure;

    public PathTracingView(Context context) {
        super(context);
    }

    public PathTracingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();
        mDst = new Path();

        mPath.addCircle(400, 400, 100, Path.Direction.CW);
        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(mPath, true);

        mLength = mPathMeasure.getLength();

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    public PathTracingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDst.reset();
        mDst.lineTo(0, 0); // 若去掉，geSegment方法可能会失效
        float stop = mLength * mAnimValue;
        float start = (float) (stop - ((0.5 - Math.abs(mAnimValue - 0.5)) * mLength));

        mPathMeasure.getSegment(start, stop, mDst, true);
        canvas.drawPath(mDst, mPaint);
    }
}
