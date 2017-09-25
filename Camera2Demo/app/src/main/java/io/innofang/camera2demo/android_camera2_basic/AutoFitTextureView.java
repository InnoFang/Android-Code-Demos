package io.innofang.camera2demo.android_camera2_basic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Author: Inno Fang
 * Time: 2017/9/24 20:37
 * Description:
 */


public class AutoFitTextureView extends TextureView {

    private int mRatioWidth = 0;
    private int mRatioHeight = 0;

    public AutoFitTextureView(Context context) {
        super(context, null);
    }

    public AutoFitTextureView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public AutoFitTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAspectRatio(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }

        mRatioHeight = height;
        mRatioWidth = width;
        requestLayout();
    }

    /**
     * 根据设置的长宽比重新定义长宽
     *        实际长     比率长
     * 公式 ：------ = ------
     *        实际宽     比率宽
     *
     *                  比率长
     * 实际长 = 实际宽 * ------
     *                  比率宽
     *
     *                  比率宽
     * 实际宽 = 实际长 * ------
     *                  比率长
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (0 == mRatioWidth || 0 == mRatioHeight) {
            setMeasuredDimension(width, height);
        } else {
            if (width < height * mRatioWidth / mRatioHeight) { // height 值过高
                setMeasuredDimension(width, width * mRatioHeight / mRatioWidth);
            } else {
                setMeasuredDimension(height * mRatioWidth / mRatioHeight, height);
            }
        }
    }
}
