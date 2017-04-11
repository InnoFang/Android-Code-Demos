package com.example.innf.volleydemo.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Author: Inno Fang
 * Time: 2016/12/23 17:09
 * Description:
 */

public class MyApplication extends Application{
    private static final String TAG = "MyApplication";

    public static RequestQueue sRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        sRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues(){
        return sRequestQueue;
    }
}
