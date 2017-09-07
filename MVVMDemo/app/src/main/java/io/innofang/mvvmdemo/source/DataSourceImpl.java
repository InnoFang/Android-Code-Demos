package io.innofang.mvvmdemo.source;

import android.content.Context;
import android.util.Log;

import io.innofang.mvvmdemo.bean.UserBean;
import io.innofang.mvvmdemo.event.Action;

/**
 * Author: Inno Fang
 * Time: 2017/9/7 17:26
 * Description:
 */


public class DataSourceImpl implements DataSource {

    private Context mContext;
    private static DataSourceImpl sInstance;

    private DataSourceImpl(Context context) {
        mContext = context;
    }

    public static DataSource getInstance(Context context) {
        if (null == sInstance) {
            sInstance = new DataSourceImpl(context);
        }
        return sInstance;
    }


    @Override
    public void signIn(UserBean userBean, Action<Integer> action) {
        if (null == action)
            return;

        if (null == userBean) {
            action.onAction(0);
            return;
        }

        Log.i("tag", "signIn: " + userBean.toString());
        if (userBean.getUsername().equals(userBean.getPassword())) {
            action.onAction(1);
        } else {
            action.onAction(0);
        }
    }
}
