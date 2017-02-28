package com.juhezi.mvptest.model.local;

import android.util.Log;

import com.juhezi.mvptest.model.Response;
import com.juhezi.mvptest.util.Action;

/**
 * Created by qiao1 on 2017/1/7.
 */
public class LocalResponse implements Response {
    private static String TAG = "LocalResponse";

    @Override
    public void signIn(String username, String passwd, Action<Integer> action) {
        Log.i(TAG, "signIn: 查询本地数据库");
        if (username.equals(passwd)) {
            action.onAction(0);
        } else {
            action.onAction(1);
        }
    }
}
