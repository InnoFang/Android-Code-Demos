package io.innofang.moduleb;

import android.app.Application;

import io.innofang.library.App;

/**
 * Author: Inno Fang
 * Time: 2017/8/8 20:37
 * Description:
 */


public class ModuleBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        App.init(getApplicationContext());
    }
}
