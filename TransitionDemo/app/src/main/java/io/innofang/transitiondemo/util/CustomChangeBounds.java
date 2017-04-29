package io.innofang.transitiondemo.util;

import android.animation.Animator;
import android.transition.ChangeBounds;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

/**
 * Author: Inno Fang
 * Time: 2017/4/29 15:29
 * Description:
 */


public class CustomChangeBounds extends ChangeBounds {

    /**
     * @param sceneRoot   屏幕根View，即DecorView，第二个Activity的DecorView。
     * @param startValues 属性动画的起始属性值，TransitionValues 对象内部有各Map类型的属性values，
     *                    用于保存需要执行属性动画的属性。这个里面的属性值是在函数captureStartValues
     *                    里放置，因此你可以重写captureStartValues函数，并把你自定义的属性动画中的属
     *                    性放进去。
     * @param endValues   与startValues类似，表示属性动画结束时的属性值。可以通过重写captureEndValues
     *                    函数，并把你自定义的属性动画里面的最终属性值放进去。
     * @return
     */
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {

        Animator changeBounds = super.createAnimator(sceneRoot, startValues, endValues);
        if (startValues == null || endValues == null || changeBounds == null)
            return null;


        if (endValues.view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) endValues.view;
            float offset = vg.getHeight() / 3;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View v = vg.getChildAt(i);
                v.setTranslationY(offset);
                v.setAlpha(0f);
                v.animate()
                        .alpha(1f)
                        .translationY(0f)
                        .setDuration(150)
                        .setStartDelay(150)
                        .setInterpolator(AnimationUtils.loadInterpolator(vg.getContext(),
                                android.R.interpolator.fast_out_slow_in));
                offset *= 1.8f;
            }
        }

        changeBounds.setDuration(300);
        /* 设置插值器 - 慢进快出 */
        changeBounds.setInterpolator(AnimationUtils.loadInterpolator(sceneRoot.getContext(),
                android.R.interpolator.fast_out_slow_in));
        return changeBounds;
    }
}
