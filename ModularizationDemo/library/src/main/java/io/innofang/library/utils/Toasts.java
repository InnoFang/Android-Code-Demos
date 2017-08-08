package io.innofang.library.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import io.innofang.library.App;

// import io.innofang.myapplication.App; // 更改为自己的Application子类

/**
 * Toast统一管理类
 */
public class Toasts {

    private Toasts() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, @StringRes int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, @StringRes int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, @StringRes int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
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
            mToast = Toast.makeText(App.getContext(), content, Toast.LENGTH_SHORT);
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
            mToast = Toast.makeText(App.getContext(), resId, Toast.LENGTH_SHORT);
        }
        mToast.setText(resId);
        if (temp - time < 2000) {
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        time = temp;
        mToast.show();
    }
}
