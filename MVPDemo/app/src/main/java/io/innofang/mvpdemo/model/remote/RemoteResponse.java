package io.innofang.mvpdemo.model.remote;

import android.util.Log;

import io.innofang.mvpdemo.model.Response;
import io.innofang.mvpdemo.util.Action;


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
