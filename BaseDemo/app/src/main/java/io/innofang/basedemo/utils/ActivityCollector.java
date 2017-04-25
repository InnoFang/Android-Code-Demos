package io.innofang.basedemo.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Inno Fang
 * Description: Activity 栈管理器
 */


public class ActivityCollector {

    private static final String TAG = "ActivityCollector";

    private static List<Activity> sActivityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        sActivityList.add(activity);
    }

    public static void removeActivty(Activity activity) {
        sActivityList.remove(activity);
    }

    public static Activity getTopActivity() {
        if (sActivityList.isEmpty()) {
            return null;
        }
        return sActivityList.get(sActivityList.size() - 1);
    }

}
