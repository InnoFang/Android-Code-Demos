package com.example.innf.volleydemo;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.innf.volleydemo.VolleyInterface;
import com.example.innf.volleydemo.app.MyApplication;

import java.util.Map;

/**
 * Author: Inno Fang
 * Time: 2016/12/23 18:59
 * Description:
 */

public class VolleyRequest {
    private static final String TAG = "VolleyRequest";

    public static StringRequest sStringRequest;

    public static Context sContext;

    public static void RequestGet(Context context, String url, String tag, VolleyInterface volleyInterface) {
        MyApplication.getHttpQueues().cancelAll(tag); // 先清空请求队列，防止出现重复的请求消耗内存
        sStringRequest = new StringRequest(Request.Method.GET, url, volleyInterface.loadingListener(), volleyInterface.errorListener());
        sStringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(sStringRequest);
        MyApplication.getHttpQueues().start();
    }
    public static void RequestPost(Context context, String url, String tag, Map<String, String> params, VolleyInterface volleyInterface) {
        MyApplication.getHttpQueues().cancelAll(tag);
        sStringRequest = new StringRequest(url, volleyInterface.loadingListener(), volleyInterface.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        sStringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(sStringRequest);
        MyApplication.getHttpQueues().start();
    }
}
