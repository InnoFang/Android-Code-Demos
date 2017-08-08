package io.innofang.modularizationdemo;

import android.app.Application;

import io.innofang.library.App;

/**
 * Author: Inno Fang
 * Time: 2017/8/8 10:35
 * Description:
 */


public class MDApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        App.init(getApplicationContext());
    }
}
