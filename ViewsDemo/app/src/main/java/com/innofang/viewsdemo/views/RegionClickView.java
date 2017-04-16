package com.innofang.viewsdemo.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Author: Inno Fang
 * Time: 2017/4/16 13:39
 * Description:
 */


public class RegionClickView extends CustomView {

    private Region mCircleRegion;
    private Path mCirclePath;

    public RegionClickView(Context context) {
        this(context, null);
    }

    public RegionClickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDefaultPaint.setColor(0xFF4E5268);
        mCirclePath = new Path();
        mCircleRegion = new Region();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCirclePath.addCircle(w / 2, h / 2, 300, Path.Direction.CW);
        Region globalRegion = new Region(-w, -h, w, h);
        mCircleRegion.setPath(mCirclePath, globalRegion);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();

                if (mCircleRegion.contains(x, y)) {
                    Toast.makeText(mCurrentContext, "Click", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path circle = mCirclePath;
        canvas.drawPath(circle, mDefaultPaint);
    }
}
