package com.innofang.viewsdemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

/**
 * Author: Inno Fang
 * Time: 2017/4/18 20:19
 * Description:
 */


public class ClockView extends SurfaceView
        implements SurfaceHolder.Callback, Runnable {

    private static final String TAG = "ClockView";

    private Paint mArcPaint = new Paint();
    private Paint mTextPaint = new Paint();
    private Paint mPointerPaint = new Paint();
    private Paint mPointPaint = new Paint();
    private Paint mPaint = new Paint();
    private SurfaceHolder mSurfaceHolder;
    private int mHeight, mWidth;
    private boolean mDrawing = true;
    private float mDialRadius = 400F;
    private float mLen = 20F;
    private float mHourPointerLen = 150F;
    private float mMinutesPointerLen = 200F;
    private float mSecondsPointerLen = 270F;
    private Bitmap mBitmapCache;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mArcPaint.setStrokeWidth(10);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.BLACK);

        mPointerPaint.setStrokeWidth(10);
        mPointerPaint.setStyle(Paint.Style.STROKE);
        mPointerPaint.setAntiAlias(true);

        mTextPaint.setTextSize(50);
        mTextPaint.setAntiAlias(true);

        mPointPaint.setAntiAlias(true);
        mPointPaint.setStrokeWidth(30);
        mPointPaint.setColor(Color.BLACK);
        mPointPaint.setStrokeCap(Paint.Cap.ROUND);

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mDrawing = false;
    }

    @Override
    public void run() {
        while (mDrawing) {
            Canvas canvas = mSurfaceHolder.lockCanvas();
            if (null == mBitmapCache) {
                mBitmapCache = Bitmap.createBitmap(canvas.getWidth(),
                        canvas.getHeight(),
                        Bitmap.Config.ARGB_4444);
            }
            Canvas canvas1 = new Canvas(mBitmapCache);
            canvas1.drawColor(Color.WHITE);
            canvas1.translate(mWidth / 2, mHeight / 2);
            drawCanvas(canvas1);
            canvas.drawBitmap(mBitmapCache, 0, 0, mPaint);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawCanvas(Canvas canvas) {
        if (null == canvas) return;
        /* 画刻度盘 */
        drawDial(canvas);
        /* 画指针 */
        drawPointer(canvas);
        /* 画中心点 */
        drawPoint(canvas);
    }

    private void drawPoint(Canvas canvas) {
        canvas.drawPoint(0, 0, mPointPaint);
    }

    private void drawPointer(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
//        Log.i(TAG, hour + " : " + minute + " : " + seconds);
        float rotateAngle = 0;

        /* draw hour pointer */
        canvas.save();
        rotateAngle = hour * 30.0F; // 一小时一大格
        int plusHour = minute / 12; // 12分钟一小格
        rotateAngle += plusHour * 6.0F;
        mPointerPaint.setColor(Color.RED);
        canvas.rotate(rotateAngle);
        canvas.drawLine(0, -20, 0, -mHourPointerLen, mPointerPaint);
        canvas.restore();

        /* draw minute pointer */
        canvas.save();
        rotateAngle = minute * 6.0F; // 一分钟一小格
        canvas.rotate(rotateAngle);
        mPointerPaint.setColor(Color.GREEN);
        canvas.drawLine(0, -20, 0, -mMinutesPointerLen, mPointerPaint);
        canvas.restore();

        /* draw second pointer */
        canvas.save();
        rotateAngle = seconds * 6.0F; // 一秒钟一小格
        canvas.rotate(rotateAngle);
        mPointerPaint.setColor(Color.BLUE);
        canvas.drawLine(0, -20, 0, -mSecondsPointerLen, mPointerPaint);
        canvas.restore();
    }

    private void drawDial(Canvas canvas) {
        canvas.save();
        int timeIndex = 1;
        String timeText;
        for (int i = 0; i < 360; i += 6) {
            if (i % 30 == 0) {
                if (i == 0) {
                    timeText = "12";
                } else {
                    timeText = "" + timeIndex++;
                }
                mArcPaint.setColor(Color.RED);
                mTextPaint.setColor(Color.BLACK);
                canvas.drawLine(0, -mDialRadius, 0, -(mDialRadius + 2 * mLen), mArcPaint);
                canvas.drawText(timeText, -15 /*让数字尽量居中*/, -(mDialRadius + mLen * 5 / 2), mTextPaint);
            } else {
                mArcPaint.setColor(Color.GREEN);
                canvas.drawLine(0, -mDialRadius, 0, -(mDialRadius + mLen), mArcPaint);
            }
            canvas.rotate(6);
        }
        canvas.restore();
    }

}
