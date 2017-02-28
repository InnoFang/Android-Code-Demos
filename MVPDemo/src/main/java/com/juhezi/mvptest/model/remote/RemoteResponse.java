package com.juhezi.mvptest.model.remote;

import android.util.Log;

import com.juhezi.mvptest.model.Response;
import com.juhezi.mvptest.util.Action;

/**
 * Created by qiao1 on 2017/1/7.
 */
public class RemoteResponse implements Response {
    private static String TAG = "RemoteResponse";

    @Override
    public void signIn(String username, String passwd, Action<Integer> action) {
        Log.i(TAG, "signIn: 查询服务器");
        action.onAction(-1);
    }
}
