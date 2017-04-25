package io.innofang.basedemo;

import android.app.Application;

import io.innofang.basedemo.utils.CrashHandler;

/**
 * Author: Inno Fang
 * Time: 2017/4/25 10:44
 * Description: 全局管理
 * <strong>务必记住在 <code>AndroidManifest.xml</code>
 * 文件中给 application 添加 name 属性</strong>
 */


public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        CrashHandler.getInstance().init(this);
    }

    public static App getInstance() {
        return sInstance;
    }
}
