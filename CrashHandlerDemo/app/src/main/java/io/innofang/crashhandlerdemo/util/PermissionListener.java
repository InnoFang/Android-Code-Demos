package io.innofang.crashhandlerdemo.util;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/4/24 14:52
 * Description:
 */


public interface PermissionListener {

    /* 授权 */
    void onGranted();

    /* 拒绝，并传入被拒绝的权限 */
    void onDenied(List<String>deniedPermission);

}
