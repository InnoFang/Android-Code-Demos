package io.innofang.library;

import android.app.Application;
import android.content.Context;

/**
 * Author: Inno Fang
 * Time: 2017/8/8 09:51
 * Description:
 */


public class App extends Application {

    private static Context mContext;

    public static Context getContext() {
        if (null == mContext) throw new NullPointerException("mContext haven't be initialize.");
        return mContext;
    }

    public static void init(Context context) {
        mContext = context;
    }

}
