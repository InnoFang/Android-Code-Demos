package com.example.innf.permissiontest;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2016/12/27 21:17
 * Description:
 */


public interface PermissionListener {

    void onGranted();  //授权

    void onDenied(List<String> deniedPermission);   //拒绝 ,并传入被拒绝的权限
}
