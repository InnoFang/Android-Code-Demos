package com.example.innf.beziertest.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Author: Inno Fang
 * Time: 2017/1/12 15:57
 * Description:
 */

public class PathMorphingBezierView extends View implements View.OnClickListener{
    private static final String TAG = "SecondBezierView";

    //起始点坐标
    private float mStartPointX;
    private float mStartPointY;

    //终点坐标
    private float mEndPointX;
    private float mEndPointY;

    //控制点坐标
    private float mFlagPointOneX;
    private float mFlagPointOneY;
    private float mFlagPointTwoX;
    private float mFlagPointTwoY;

    private Path mPath;

    private Paint mPaintBezier;
    private Paint mPaintFlag;
    private Paint mPaintFlagText;

    private ValueAnimator mValueAnimator;

    private boolean mIsSecondPoint = false;
    public PathMorphingBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathMorphingBezierView(Context context) {
        super(context);
    }

    public PathMorphingBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setStyle(Paint.Style.STROKE);

        mPaintFlag = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setStrokeWidth(3);
        mPaintBezier.setStyle(Paint.Style.STROKE);

        mPaintFlagText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFlagText.setStyle(Paint.Style.STROKE);
        mPaintFlagText.setTextSize(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartPointX = w / 4;
        mStartPointY = h / 2 - 200;

        mEndPointX = 3 * w / 4;
        mEndPointY = h / 2 - 200;

        mFlagPointOneX = mStartPointX;
        mFlagPointOneY = mStartPointY;

        mFlagPointTwoX = mEndPointX;
        mFlagPointTwoY = mEndPointY;

        mPath = new Path();

        mValueAnimator = ValueAnimator.ofFloat(mStartPointY, h);
        mValueAnimator.setInterpolator(new BounceInterpolator());
        mValueAnimator.setDuration(1000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFlagPointOneY = (float) animation.getAnimatedValue();
                mFlagPointTwoY = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);
        mPath.cubicTo(mFlagPointOneX, mFlagPointOneY, mFlagPointTwoX, mFlagPointTwoY, mEndPointX, mEndPointY); // 没r的方法是绝对坐标，有r的是相对坐标


        canvas.drawPoint(mStartPointX, mStartPointY, mPaintFlag);
        canvas.drawText("起点", mStartPointX, mStartPointY, mPaintFlagText);

        canvas.drawPoint(mEndPointX, mEndPointY, mPaintFlag);
        canvas.drawText("终点", mEndPointX, mEndPointY, mPaintFlagText);

        canvas.drawPoint(mFlagPointOneX, mFlagPointOneY, mPaintFlag);
        canvas.drawText("控制点1", mFlagPointOneX, mFlagPointOneY, mPaintFlagText);

        canvas.drawPoint(mFlagPointOneX, mFlagPointOneY, mPaintFlag);
        canvas.drawText("控制点2", mFlagPointTwoX, mFlagPointTwoY, mPaintFlagText);

        canvas.drawLine(mFlagPointOneX, mFlagPointOneY, mFlagPointTwoX, mFlagPointTwoY, mPaintFlag);

        canvas.drawLine(mStartPointX, mStartPointY, mFlagPointOneX, mFlagPointOneY, mPaintFlag);
        canvas.drawLine(mEndPointX, mEndPointY, mFlagPointTwoX, mFlagPointTwoY, mPaintFlag);

        canvas.drawPath(mPath, mPaintBezier);
    }

    @Override
    public void onClick(View v) {
        mValueAnimator.start();
    }
}
