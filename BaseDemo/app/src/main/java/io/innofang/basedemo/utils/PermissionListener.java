package io.innofang.basedemo.utils;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/4/25 09:50
 * Description: Runtime Permissions Listener
 */


public interface PermissionListener {

    /* 权限已授权 */
    void onGranted();

    /* 权限被拒绝 */
    void onDenied(List<String> deniedPermission);
}
