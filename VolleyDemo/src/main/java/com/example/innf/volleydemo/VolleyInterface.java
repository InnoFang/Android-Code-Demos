package com.example.innf.volleydemo;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Author: Inno Fang
 * Time: 2016/12/23 19:16
 * Description:
 */


public abstract class VolleyInterface {

    public Context mContext;
    public static Response.Listener sListener;
    public static Response.ErrorListener sErrorListener;


    public VolleyInterface(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this.mContext = context;
        this.sListener = listener;
        this.sErrorListener = errorListener;
    }



    public Response.Listener<String> loadingListener() {
        sListener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                onMySuccess(response.toString());
            }
        };
        return sListener;
    }

    public Response.ErrorListener errorListener(){
        sErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onMyError(error);
            }
        };
        return sErrorListener;
    }

    public abstract void onMySuccess(String result);
    public abstract void onMyError(VolleyError error);
}
