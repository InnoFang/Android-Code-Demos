package com.innofang.viewsdemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Inno Fang
 * Time: 2017/4/11 20:04
 * Description:
 */


public class Sketch extends View {

    private Paint mPaint = new Paint();
    private int mWidth, mHeight;

    public Sketch(Context context) {
        this(context, null);
    }

    public Sketch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);

        Path path = new Path();
/*      // path close test
        path.lineTo(100, 100);
        path.moveTo(200, 200);
        path.lineTo(200, 0);

        path.close();*/
/*
        // Clockwise and Counter-Clockwise test
        path.addRect(-200,-200,200,200, Path.Direction.CW);

        path.setLastPoint(-300,300);                // <-- 重置最后一个点的位置
*/

       /*
        // add path test
        canvas.scale(1, -1);
        Path src = new Path();

        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);

        path.addPath(src, 0, 200);
        mPaint.setColor(Color.BLACK);
*/

        canvas.scale(1, -1);
        path.lineTo(100, 100);
        RectF oval = new RectF(0, 0, 300, 300);
        path.addArc(oval, 0, 270);

        canvas.drawPath(path, mPaint);
    }
}
