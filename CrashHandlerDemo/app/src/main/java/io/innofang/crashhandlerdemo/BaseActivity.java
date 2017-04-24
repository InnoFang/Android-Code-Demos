package io.innofang.crashhandlerdemo;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.innofang.crashhandlerdemo.util.ActivityCollector;
import io.innofang.crashhandlerdemo.util.PermissionListener;

/**
 * Author: Inno Fang
 * Time: 2017/4/24 14:42
 * Description:
 */


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    private static PermissionListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public static void requestRuntimePermission(String[] permissions,
                                                PermissionListener listener) {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (null == topActivity)
            return;
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) !=
                    PackageManager.PERMISSION_GRANTED){
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(topActivity,
                    permissionList.toArray(new String[permissionList.size()]),
                    PERMISSIONS_REQUEST_CODE);
        } else {
            /* 执行授权成功操作 */
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }
}
