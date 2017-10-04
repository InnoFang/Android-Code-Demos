package io.innofang.xfyundemo;

import android.app.Application;

import io.innofang.xfyun.XFYun;

/**
 * Author: Inno Fang
 * Time: 2017/10/3 16:07
 * Description:
 */


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XFYun.init(this);
    }
}
