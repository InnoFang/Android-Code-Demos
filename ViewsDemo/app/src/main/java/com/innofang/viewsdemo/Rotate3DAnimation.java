package com.innofang.viewsdemo;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Author: Inno Fang
 * Time: 2017/4/15 15:26
 * Description:
 */


public class Rotate3DAnimation extends Animation {

    private final float mFromDegrees;
    private final float mToDegrees;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final boolean mReverse;
    private Camera mCamera;
    float scale = 1; // <------- 像素密度

    public Rotate3DAnimation(Context context,
                             float fromDegrees, float toDegrees,
                             float centerX, float centerY,
                             float depthZ, boolean reverse) {
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = depthZ;
        mReverse = reverse;

        // 获取手机像素密度 （即dp与px的比例）
        scale = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();
        camera.save();

        // 调节深度
        if (mReverse)
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        else
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));

        // 绕y轴旋转
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();

        // 修正失真，主要修改 MPERSP_0 和 MPERSP_1
        float[] mValues = new float[9];
        matrix.getValues(mValues);              //获取数值
        mValues[6] = mValues[6] / scale;        //数值修正
        mValues[7] = mValues[7] / scale;        //数值修正
        matrix.setValues(mValues);              //重新赋值

        // 调节中心点
        matrix.preTranslate(-centerX, -centerY);
        matrix.postScale(centerX, centerY);

    }
}
