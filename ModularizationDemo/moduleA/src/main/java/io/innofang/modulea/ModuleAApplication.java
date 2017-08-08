package io.innofang.modulea;

import android.app.Application;

import io.innofang.library.App;

/**
 * Author: Inno Fang
 * Time: 2017/8/8 10:25
 * Description:
 */


public class ModuleAApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        App.init(getApplicationContext());
    }

}
