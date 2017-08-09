package io.innofang.arouterdemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Author: Inno Fang
 * Time: 2017/8/9 14:19
 * Description:
 */


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
