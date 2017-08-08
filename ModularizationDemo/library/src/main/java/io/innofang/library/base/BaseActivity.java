package io.innofang.library.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.innofang.library.utils.ActivityCollector;
import io.innofang.library.utils.RequestPermissions;


/**
 * Author: Inno Fang
 * Description: Base Operation of Activity
 */


public class BaseActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;

    private static RequestPermissions.OnRequestPermissionsListener mListener;

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
     * 对运行时权限请求结果进行处理
     *
     * @param requestCode  请求码
     * @param permissions  申请的运行时权限
     * @param grantResults 运行时权限申请结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
