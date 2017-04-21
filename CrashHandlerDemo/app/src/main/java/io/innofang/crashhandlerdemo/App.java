package io.innofang.crashhandlerdemo;

import android.app.Application;

/**
 * Author: Inno Fang
 * Time: 2017/4/20 22:14
 * Description: Application
 */


public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        /* 在这里为应用设置异常处理，然后程序才能获取未处理的异常 */
        CrashHandler.getInstance()
                .init(getApplicationContext());
    }

    public static App getInstance() {
        return sInstance;
    }
}
