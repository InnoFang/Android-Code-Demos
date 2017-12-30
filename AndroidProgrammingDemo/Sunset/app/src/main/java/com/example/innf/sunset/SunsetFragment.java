package com.example.innf.sunset;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Author: Inno Fang
 * Time: 2016/7/18 13:16
 * Description:
 */

public class SunsetFragment extends android.support.v4.app.Fragment {

    private View mSceneView;
    private View mSunView;
    private View mShadowView;
    private View mSkyView;

    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;

    private int mHeatSunColor;
    private int mColdSunColor;

    private boolean mSunset = true;

    public static final int DURATION = 3000;

    private float mSunYCurrent = Float.NaN;
    private float mShadowYCurrent = Float.NaN;

    private int mSunsetSkyColorCurrent;
    private int mNightSkyColorCurrent;

    private AnimatorSet mSunsetAnimatorSet;
    private AnimatorSet mSunriseAnimatorSet;

    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sunset, container, false);

        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mShadowView = view.findViewById(R.id.shadow);
        mSkyView = view.findViewById(R.id.sky);

        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSunset) {
                    startSunsetAnimation();
                    if (mSunriseAnimatorSet != null) {
                        mSunriseAnimatorSet.end();
                        mSunriseAnimatorSet = null;
                    }
                } else {
                    startSunriseAnimation();
                    if (mSunsetAnimatorSet != null) {
                        mSunsetAnimatorSet.end();
                        mSunsetAnimatorSet = null;
                    }
                }
                mSunset = !mSunset;
                startSunPulsateAnimation();
            }
        });

        mBlueSkyColor = getResources().getColor(R.color.blue_sky);
        mSunsetSkyColor = getResources().getColor(R.color.sun_set_sky);
        mNightSkyColor = getResources().getColor(R.color.night_sky);

        mHeatSunColor = getResources().getColor(R.color.heat_sun);
        mColdSunColor = getResources().getColor(R.color.cold_sun);

        return view;
    }

    private void startSunriseAnimation() {
        float sunYStart = (Float.valueOf(mSunYCurrent).isNaN() ?
                mSkyView.getHeight() : mSunYCurrent);
        float sunYEnd = mSunView.getTop();

        float shadowYStart = (Float.valueOf(mShadowYCurrent).isNaN() ?
                -mShadowView.getHeight() : mShadowYCurrent);
        float shadowYEnd = mShadowView.getTop();

        int sunsetSkyColorStart = (Float.valueOf(mSunYCurrent).isNaN() ?
                mSunsetSkyColor : mSunsetSkyColorCurrent);

        long duration = (Float.valueOf(mSunYCurrent).isNaN() ?
                DURATION : (long) (DURATION / (mSunView.getTop() - mSkyView.getHeight())
                * (mSunView.getTop() - mSunYCurrent)));

        int nightSkyColorStart = (mNightSkyColorCurrent == 0 ?
                mNightSkyColor : mNightSkyColorCurrent);

        ObjectAnimator sunHeightAnimator = ObjectAnimator.ofFloat(mSunView, "y",
                sunYStart, sunYEnd)
                .setDuration(duration);

        sunHeightAnimator.setInterpolator(new DecelerateInterpolator());

        sunHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSunYCurrent = (float) animation.getAnimatedValue();
            }
        });

        ObjectAnimator shadowHeightAnimator = ObjectAnimator.ofFloat(mShadowView, "y",
                shadowYStart, shadowYEnd)
                .setDuration(duration);

        shadowHeightAnimator.setInterpolator(new DecelerateInterpolator());

        shadowHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mShadowYCurrent = (float) animation.getAnimatedValue();
            }
        });

        ObjectAnimator sunriseSkyAnimator = ObjectAnimator.ofObject(mSkyView, "backgroundColor",
                new ArgbEvaluator(), sunsetSkyColorStart, mBlueSkyColor)
                .setDuration(duration);

        sunriseSkyAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSunsetSkyColorCurrent = (int) animation.getAnimatedValue();
            }
        });

        ObjectAnimator nightSkyAnimator = ObjectAnimator.ofObject(mSkyView, "backgroundColor",
                new ArgbEvaluator(), nightSkyColorStart, mSunsetSkyColor)
                .setDuration(mSunYCurrent ==  mSkyView.getHeight() ? DURATION : 0);

        nightSkyAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mNightSkyColorCurrent = (int) animation.getAnimatedValue();
            }
        });

        mSunriseAnimatorSet = new AnimatorSet();
        mSunriseAnimatorSet
                .play(sunHeightAnimator)
                .with(shadowHeightAnimator)
                .with(sunriseSkyAnimator)
                .after(nightSkyAnimator);

        mSunriseAnimatorSet.start();
    }

    private void startSunsetAnimation() {
        float sunYStart = (Float.valueOf(mSunYCurrent).isNaN() ?
                mSunView.getTop() : mSunYCurrent);
        float sunYEnd = mSkyView.getHeight();

        float shadowYStart = (Float.valueOf(mShadowYCurrent).isNaN() ?
                mShadowView.getTop() : mShadowYCurrent);
        float shadowYEnd = -mShadowView.getHeight();

        int sunsetSkyColorStart = (mSunsetSkyColorCurrent == 0 ?
                mBlueSkyColor : mSunsetSkyColorCurrent);

        long duration = (Float.valueOf(mSunYCurrent).isNaN() ?
                DURATION : (long) (DURATION / (mSkyView.getHeight() - mSunView.getTop())
                * (mSkyView.getHeight() - mSunYCurrent)));

        int nightSkyColorStart = (mNightSkyColorCurrent == 0 ?
                mSunsetSkyColor : mNightSkyColorCurrent);

        ObjectAnimator sunHeightAnimator = ObjectAnimator.ofFloat(mSunView, "y",
                sunYStart, sunYEnd)
                .setDuration(duration);

        sunHeightAnimator.setInterpolator(new AccelerateInterpolator());

        sunHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSunYCurrent = (float) animation.getAnimatedValue();
            }
        });

        ObjectAnimator shadowHeightAnimator = ObjectAnimator.ofFloat(mShadowView, "y",
                shadowYStart, shadowYEnd)
                .setDuration(duration);

        shadowHeightAnimator.setInterpolator(new AccelerateInterpolator());

        shadowHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mShadowYCurrent = (float) animation.getAnimatedValue();
            }
        });

        ObjectAnimator sunsetSkyAnimator = ObjectAnimator.ofObject(mSkyView, "backgroundColor",
                new ArgbEvaluator(), sunsetSkyColorStart, mSunsetSkyColor)
                .setDuration(duration);

        sunsetSkyAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSunsetSkyColorCurrent = (int) animation.getAnimatedValue();
            }
        });

        ObjectAnimator nightSkyAnimator = ObjectAnimator.ofObject(mSkyView, "backgroundColor",
                new ArgbEvaluator(), nightSkyColorStart, mNightSkyColor)
                .setDuration(DURATION);

        nightSkyAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mNightSkyColorCurrent = (int) animation.getAnimatedValue();
            }
        });

        mSunsetAnimatorSet = new AnimatorSet();
        mSunsetAnimatorSet
                .play(sunHeightAnimator)
                .with(shadowHeightAnimator)
                .with(sunsetSkyAnimator)
                .before(nightSkyAnimator);

        mSunsetAnimatorSet.start();
    }

    private void startSunPulsateAnimation() {
        ObjectAnimator sunPulsateAnimator = ObjectAnimator.ofObject(mSunView, "backgroundColor",
                new ArgbEvaluator(), mColdSunColor, mHeatSunColor)
                .setDuration(1000);

        sunPulsateAnimator.setRepeatMode(ValueAnimator.REVERSE);
        sunPulsateAnimator.setRepeatCount(ValueAnimator.INFINITE);

        sunPulsateAnimator.start();
    }
}
