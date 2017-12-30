package com.innofang.library;

import android.support.annotation.NonNull;

/**
 * Author: Inno Fang
 * Time: 2017/4/9 20:09
 * Description:
 */


public class PieData {
    private String mName;
    private float mValue;
    private float mPercentage;

    private int mColor = 0;
    private float mAngle = 0;

    public PieData(@NonNull String name, @NonNull float value) {
        mName = name;
        mValue = value;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float value) {
        mValue = value;
    }

    public float getPercentage() {
        return mPercentage;
    }

    public void setPercentage(float percentage) {
        mPercentage = percentage;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }

    @Override
    public String toString() {
        return "PieData{" +
                "mName='" + mName + '\'' +
                ", mValue=" + mValue +
                ", mPercentage=" + mPercentage +
                ", mColor=" + mColor +
                ", mAngle=" + mAngle +
                '}';
    }
}
