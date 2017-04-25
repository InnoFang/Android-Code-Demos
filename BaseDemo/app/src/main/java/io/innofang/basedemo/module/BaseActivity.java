package io.innofang.basedemo.module;

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

import io.innofang.basedemo.utils.ActivityCollector;
import io.innofang.basedemo.utils.event.PermissionListener;

/**
 * Author: Inno Fang
 * Description: Base Operation of Activity
 */


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private static final int PERMISSION_REQUEST_CODE = 1;

    private static PermissionListener mListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivty(this);
    }

    /**
     * 对运行时权限进行申请
     *
     * @param permissions 权限数组
     * @param listener    运行时权限监听器
     */
    public static void requestRuntimePermission(String[] permissions,
                                                PermissionListener listener) {
        Activity topActivity = ActivityCollector.getTopActivity();
        if (null == topActivity) {
            return;
        }
        mListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission)
                    != PackageManager.PERMISSION_GRANTED) {

                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(topActivity,
                    permissionList.toArray(new String[permissionList.size()]),
                    PERMISSION_REQUEST_CODE);
        } else {
            mListener.onGranted();
        }
    }

    /**
     * 对运行时权限请求结果进行处理
     *
     * @param requestCode  请求码
     * @param permissions  申请的运行时权限
     * @param grantResults 运行时权限申请结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
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
        }
    }
}
