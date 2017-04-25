package io.innofang.basedemo.utils;

import android.util.Log;

/**
 * Author: Inno Fang
 * Description: Log 管理类
 */


public class L {
    private static final String TAG = "Log";

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /* 是否需要打印bug，可以在application的onCreate函数里面初始化 */
    public static boolean sDebug = true;

    public static void v(String msg) {
        if (sDebug)
            Log.v(TAG, msg);
    }

    public static void d(String msg) {
        if (sDebug)
            Log.d(TAG, msg);
    }

    /* 下面四个是默认tag的函数 */
    public static void i(String msg) {
        if (sDebug)
            Log.i(TAG, msg);
    }

    public static void e(String msg) {
        if (sDebug)
            Log.e(TAG, msg);
    }

    public static void w(String msg) {
        if (sDebug)
            Log.w(TAG, msg);
    }

    /* 下面是传入自定义tag的函数 */
    public static void v(String tag, String msg) {
        if (sDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (sDebug)
            Log.i(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (sDebug)
            Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (sDebug)
            Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (sDebug)
            Log.i(tag, msg);
    }
}
