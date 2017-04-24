package com.example.innf.permissiontest;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2016/12/27 21:35
 * Description:
 */

public class Util {
    private static final String TAG = "Util";

    public void test(){
        BaseActivity.requestRuntimePermission(new String[]{}, new PermissionListener() {
            @Override
            public void onGranted() {

            }

            @Override
            public void onDenied(List<String> deniedPermission) {

            }
        });
    }
}
