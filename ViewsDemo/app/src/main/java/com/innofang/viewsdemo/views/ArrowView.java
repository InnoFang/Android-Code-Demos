package com.innofang.viewsdemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.innofang.viewsdemo.R;

/**
 * Author: Inno Fang
 * Time: 2017/4/13 11:31
 * Description:
 */


public class ArrowView extends View {

    private float mCurrentValue = 0; // 用于记录当前的位置，取值范围[0,1]映射Path的整个长度

    private float[] mPos;            // 当前点的实际位置
    private float[] mTan;            // 当前点的tangent值，用于计算图片所需旋转的角度
    private Bitmap mBitmap;         // 箭头图片
    private Matrix mMatrix;         // 矩阵，用于对图片进行一些操作
    private int mViewWidth, mViewHeight;
    private Paint mDefaultPaint = new Paint();

    public ArrowView(Context context) {
        this(context, null);
    }

    public ArrowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mDefaultPaint.setStrokeWidth(5);
        mDefaultPaint.setColor(Color.BLUE);
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setStyle(Paint.Style.STROKE);
        mPos = new float[2];
        mTan = new float[2];
  /*      BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2; // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_right_arrow, options);*/
        mBitmap = getBitmap(context, R.drawable.ic_right_arrow);
        mMatrix = new Matrix();
    }

    private static Bitmap getBitmap(Context context,int vectorDrawableId) {
        Bitmap bitmap=null;
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
            bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                    vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
        }else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), vectorDrawableId);
        }
        return bitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w / 2;
        mViewHeight = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);      // 平移坐标系
        Path path = new Path();                                 // 创建Path
        path.addCircle(0, 0, 200, Path.Direction.CW);           // 添加一个圆形

        PathMeasure pathMeasure = new PathMeasure(path, false); // 创建 PathMeasure

        mCurrentValue += 0.005;                                 // 计算当前的位置在总长度上的比例[0,1]
        if (mCurrentValue >= 1) {
            mCurrentValue = 0;
        }

        pathMeasure.getPosTan(pathMeasure.getLength() * mCurrentValue, mPos, mTan); // 获取当前位置的坐标以及趋势

        mMatrix.reset(); // 重置Matrix
        float degrees = (float) (Math.atan2(mTan[1], mTan[0]) * 180.0 / Math.PI);

        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2); // 旋转图片

        mMatrix.postTranslate(mPos[0] - mBitmap.getWidth() / 2, mPos[1] - mBitmap.getHeight() / 2); // 将图片绘制中心调整到与当前点重合

        canvas.drawPath(path, mDefaultPaint); // 绘制Path
        canvas.drawBitmap(mBitmap, mMatrix, mDefaultPaint); // 绘制箭头

        invalidate(); // 绘制页面
    }
}
