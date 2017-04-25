package io.innofang.basedemo.utils;

import java.util.List;

/**
 * Author: Inno Fang
 * Description: Runtime Permissions Listener
 */


public interface PermissionListener {

    /* 权限已授权 */
    void onGranted();

    /* 权限被拒绝 */
    void onDenied(List<String> deniedPermission);
}
