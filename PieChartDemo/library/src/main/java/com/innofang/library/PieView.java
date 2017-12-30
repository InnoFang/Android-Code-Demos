package com.innofang.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * Author: Inno Fang
 * Time: 2017/4/9 20:12
 * Description:
 */


public class PieView extends View {

    private static final String TAG = "PieView";

    /* 颜色表 */
    private int[] mColors = {
            0xFFCCFF00, 0xFF6495ED, 0xFFE32636,
            0xFF800000, 0xFF808000, 0xFFFF8C69,
            0xFF808080, 0xFFE6B800, 0xFF7CFC00};

    /* 饼状图初识绘制角度 */
    private float mStartAngle = 0;

    /* 数据 */
    private ArrayList<PieData> mPieDataList;

    /* 宽高 */
    private int mWidth, mHeight;

    /* 画笔 */
    private Paint mPaint = new Paint();
    private Paint mTitlePaint = new Paint();

    /* 文字色块部分 */
    private PointF mStartPoint = new PointF(20, 20);
    private PointF mCurrentPoint = new PointF(mStartPoint.x, mStartPoint.y);
    private float mColorRectSideLength = 20;
    private float mTextInterval = 10;
    private float mRowMaxLength;
    private float mRadius;

    /* attrs */
    private String mTitle;
    private float mTextSize;
    private float mTitleSize;
    private int mTextColor;
    private int mTitleColor;
    private static final int TOP = 1;
    private static final int BOTTOM = 1 << 1;
    private static final int LEFT = 1 << 2;
    private static final int RIGHT = 1 << 3;
    private static final int DEFAULT_TITLE_GRAVITY = BOTTOM;
    public static final int[] TITLE_GRAVITY = {TOP, BOTTOM, LEFT, RIGHT};


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TOP, BOTTOM, LEFT, RIGHT})
    public @interface TitleGravity {
    }

    @TitleGravity
    private int mTitleGravity = DEFAULT_TITLE_GRAVITY;

    public PieView(Context context) {
        this(context, null, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    private void init(AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = getContext()
                .obtainStyledAttributes(attrs, R.styleable.PieView, defStyleAttr, 0);

        mTitle = typedArray.getString(R.styleable.PieView_title);
        mTextSize = typedArray.getDimension(R.styleable.PieView_textSize, 10);
        mTitleSize = typedArray.getDimension(R.styleable.PieView_titleSize, 10);
        mTextColor = typedArray.getColor(R.styleable.PieView_textColor, 0);
        mTitleColor = typedArray.getColor(R.styleable.PieView_titleColor, 0);
        mTitleGravity = TITLE_GRAVITY[typedArray.getInt(R.styleable.PieView_title_gravity, 1)];

        /* 调用 recycler() 方法避免重新创建时的错误*/
        typedArray.recycle();

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mTitlePaint.setAntiAlias(true);
        mTitlePaint.setTextSize(mTitleSize);
        mTitlePaint.setColor(mTitleColor);
        mTitlePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
            result = specSize;
        else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(650, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mPieDataList)
            return;
        float currentStartAngle = mStartAngle;                  // 当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);              // 将画布坐标原点移动到中心位置
        mRadius = (float) (Math.min(mWidth, mHeight) / 2 * 0.7);// 饼状图半径
        RectF rect = new RectF(-mRadius, -mRadius, mRadius, mRadius);                   // 饼状图绘制区域

        for (int i = 0; i < mPieDataList.size(); i++) {
            PieData pieData = mPieDataList.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rect, currentStartAngle, pieData.getAngle(), true, mPaint);
            currentStartAngle += pieData.getAngle();

            canvas.save();
            canvas.translate(-mWidth / 2, -mHeight / 2);
            RectF colorRect = new RectF(mCurrentPoint.x, mCurrentPoint.y,
                    mCurrentPoint.x + mColorRectSideLength,
                    mCurrentPoint.y + mColorRectSideLength);

            canvas.restore();
        }

        drawTitle(canvas);
    }

    @SuppressWarnings("deprecation")
    private void drawTitle(Canvas canvas) {
        float startX = mRadius - (mTitleSize * 3 / mHeight * 2) / 2;
        float startY = mRadius + mTitleSize;
        if (mTitleGravity == TOP) {
            canvas.drawText(mTitle, -startX, -startY, mTitlePaint);
        } else if (mTitleGravity == BOTTOM) {
            canvas.drawText(mTitle, -startX, startY, mTitlePaint);
        } else {
            canvas.save();
            if (mTitleGravity == LEFT) {
                canvas.rotate(-90);
            } else {
                canvas.rotate(90);
            }
            canvas.drawText(mTitle, -startX, -startY, mTitlePaint);
            canvas.restore();
        }
    }

    /* 设置起始角度 */
    public void setStartAngle(int startAngle) {
        mStartAngle = startAngle;
        invalidate(); // 刷新
    }

    /* 设置数据 */

    public void setPieDataList(ArrayList<PieData> pieDataList) {
        mPieDataList = pieDataList;
        initPieData(pieDataList);
        invalidate(); // 刷新
    }

    /* 初始化数据 */
    private void initPieData(ArrayList<PieData> pieDataList) {
        if (null == pieDataList || pieDataList.size() == 0)
            return;
        float sumValue = 0;
        for (int i = 0; i < pieDataList.size(); i++) {
            PieData pieData = pieDataList.get(i);
            sumValue += pieData.getValue(); // 计算数值和
            int j = i % mColors.length;     // 设置颜色
            pieData.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < pieDataList.size(); i++) {
            PieData pieData = pieDataList.get(i);

            float percentage = pieData.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                     // 对应角度

            pieData.setPercentage(percentage);                  // 记录百分比
            pieData.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;
        }
    }
}
