package com.example.innf.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2016/7/17 15:43
 * Description:
 */

public class BoxDrawingView extends View {
    private static final String TAG = "BoxDrawingView";
    private static final String PARENT_STATE_KEY = "com.example.innf.draganddraw.save_parcelable";

    private Box mCurrentBox;
    private List<Box> mBoxen = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    /**
     *这里添加两个构造方法，是因为视图可从代码或者布局文件实例化。
     */
    //Used when creating the view in code
    public BoxDrawingView(Context context) {
        super(context,null);
    }

    /**
     *从布局文件中实例化的视图可收到一个AttributeSet实例，该实例包含了XML布局文件中指定的XML属性。
     */
    //Used when inflating the view from XML
    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Paint the boxes a nice semitransparent red (ARGB)
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        //Paint the background off-white
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }



    /**
     *即使不打算使用构造方法，按照习惯也应该添加它们
     */


    @Override
    protected void onDraw(Canvas canvas) {
        //fill the background
        canvas.drawPaint(mBackgroundPaint);

        for (Box box : mBoxen) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left, top, right, bottom, mBoxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF current = new PointF(event.getX(), event.getY());
        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                //reset drawing state
                mCurrentBox = new Box(current);
                mBoxen.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if (null != mCurrentBox){
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                mCurrentBox = null;
                break;
        }

        Log.i(TAG, action + "at x=" + current.x + ", y=" + current.y);
        return true;
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        // We must save the parent class state first.
        Parcelable parentState = super.onSaveInstanceState();

        //Bundle used to save the state.
        Bundle bundle = new Bundle();
        bundle.putParcelable(PARENT_STATE_KEY, parentState);

        // Saved the state of the draw boxes
        // the order of the points are origin.x, origin.y,
        // current.x, current,y.
        int boxNumber = 1;
        for (Box box : mBoxen) {
            float[] pointsArray = {
                    box.getOrigin().x,
                    box.getOrigin().y,
                    box.getCurrent().x,
                    box.getCurrent().y,
            };

            bundle.putFloatArray("box" + boxNumber, pointsArray);
            boxNumber++;
        }
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // Recover the state (boxes) that this view has.
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(PARENT_STATE_KEY));

            // We recover the names of the boxes as keys.
            String prefixName = "box";
            int boxCount = 1;

            //while we found a key we will create boxes
            while(bundle.containsKey(prefixName + boxCount)){
                // Get the x and y values from the bundle.
                float[] pointsArray = bundle.getFloatArray(prefixName + boxCount);

                // Create the boxes from the saved array.
                PointF origin = new PointF(pointsArray[0], pointsArray[1]);
                PointF current = new PointF(pointsArray[2], pointsArray[3]);
                Box box = new Box(origin);
                box.setCurrent(current);

                mBoxen.add(box);
                boxCount++;
            }
        }else{
            super.onRestoreInstanceState(state);
        }
    }
}
