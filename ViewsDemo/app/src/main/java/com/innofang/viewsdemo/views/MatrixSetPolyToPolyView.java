package com.innofang.viewsdemo.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.innofang.viewsdemo.R;

/**
 * Author: Inno Fang
 * Time: 2017/4/15 11:10
 * Description:
 */


public class MatrixSetPolyToPolyView extends View {

    private Bitmap mBitmap; // 要绘制的图片
    private Matrix mPolyMatrix; // 测试setPolyToPoly用的Matrix


    private Bitmap mUMRBitmap;
    private Matrix mUMRMatrix;
    public MatrixSetPolyToPolyView(Context context) {
        this(context, null);
    }

    public MatrixSetPolyToPolyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initBitmapAndMatrix();
        initUMR();
    }

    private void initUMR() {
        mUMRBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.umr);
        mUMRMatrix = new Matrix();

        float[] src = {
                0, 0,                                           // 左上
                mUMRBitmap.getWidth(), 0,                          // 右上
                mUMRBitmap.getWidth(), mUMRBitmap.getHeight(),        // 右下
                0, mUMRBitmap.getHeight(),                         // 左下
        };

        float[] dst = {
                0, 0,                                           // 左上
                mUMRBitmap.getWidth(), -200,                          // 右上
                mUMRBitmap.getWidth(), mUMRBitmap.getHeight() + 100,        // 右下
                0, mUMRBitmap.getHeight(),                         // 左下
        };

        mUMRMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);

        mUMRMatrix.postScale(0.5f, 0.5f);
        mUMRMatrix.postTranslate(100, 830);
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meizhi);

        mPolyMatrix = new Matrix();

        float[] src = {
                0, 0,                                           // 左上
                mBitmap.getWidth(), 0,                          // 右上
                mBitmap.getWidth(), mBitmap.getHeight(),        // 右下
                0, mBitmap.getHeight(),                         // 左下
        };

        float[] dst = {
                0, 0,                                           // 左上
                mBitmap.getWidth(), 200,                        // 右上
                mBitmap.getWidth(), mBitmap.getHeight() - 400,  // 右下
                0, mBitmap.getHeight(),                         // 左下
        };

        // 核心要点
        mPolyMatrix.setPolyToPoly(
                src,                /*原始数组，存储内容为一组点*/
                0,                  /*原始数组开始位置*/
                dst,                /*目标数组，存储内容为一组点*/
                0,                  /*目标数组开始位置*/
                src.length >> 1);   /*测控点的数量，取值范围是：0到4*/

        mPolyMatrix.postScale(0.26f, 0.26f);
        mPolyMatrix.postTranslate(50, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);
        canvas.drawBitmap(mUMRBitmap, mUMRMatrix, null);
    }
}
