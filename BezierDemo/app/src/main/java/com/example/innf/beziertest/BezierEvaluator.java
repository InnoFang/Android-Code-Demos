package com.example.innf.beziertest;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Author: Inno Fang
 * Time: 2017/1/12 20:42
 * Description:
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private final PointF mFlagePoint; // 控制点

    public BezierEvaluator(PointF flagPoint) {
        mFlagePoint = flagPoint;
    }

    /**
     *
     * @param fraction 当前的完成比例，也就是我们之前控制曲线的一个长度的比例参数
     * @param startValue 起始点坐标
     * @param endValue  终点坐标
     * @return
     */
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        return BezierUtil.CalculateBezierPointForQuadratic(fraction, startValue, mFlagePoint, endValue);
    }
}
