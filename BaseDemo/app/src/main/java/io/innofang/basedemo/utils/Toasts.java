package io.innofang.basedemo.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import io.innofang.basedemo.App;

/**
 * Author: Inno Fang
 * Time: 2017/4/25 10:56
 * Description:
 */


public class Toasts {

    private Toasts() {}

    public static boolean sShow = true;

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (sShow)
            Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort( @StringRes int message) {
        if (sShow)
            Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (sShow)
            Toast.makeText(App.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(@StringRes int message) {
        if (sShow)
            Toast.makeText(App.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        if (sShow)
            Toast.makeText(App.getInstance(), message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(@StringRes int message, int duration) {
        if (sShow)
            Toast.makeText(App.getInstance(), message, duration).show();
    }

    private static Toast mToast;
    private static long time = 0;

    /**
     * 显示Toast
     * 点一次显示内容并且为短时间显示，若在两秒内点击两次，则更改为长时间显示
     * 不会重复创建Toast
     * <strong>App为Application子类</strong>
     * @param content
     */
    public static void showToast(String content) {
        long temp = System.currentTimeMillis();
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), content, Toast.LENGTH_SHORT);
        }
        mToast.setText(content);
        if (temp - time < 2000) {
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        time = temp;
        mToast.show();
    }


    public static void showToast(@StringRes int resId) {
        long temp = System.currentTimeMillis();
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), resId, Toast.LENGTH_SHORT);
        }
        mToast.setText(resId);
        if (temp - time < 2000) {
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        time = temp;
        mToast.show();
    }
}
