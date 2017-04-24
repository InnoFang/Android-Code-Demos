package io.innofang.crashhandlerdemo.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/4/24 14:53
 * Description:
 */


public class ActivityCollector {

    private static final String TAG = "ActivityCollector";

    private static List<Activity> sActivityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        sActivityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivityList.remove(activity);
    }

    public static Activity getTopActivity() {
        if (sActivityList.isEmpty()) {
            return null;
        }
        /* 返回栈顶 Activity */
        return sActivityList.get(sActivityList.size() - 1);
    }
}
