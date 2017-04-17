package com.innofang.ndkdemo;

/**
 * Author: Inno Fang
 * Time: 2017/4/16 21:55
 * Description:
 */


public class JniUtil {

    public native int add (int a, int b);

}
//D:\Java\jdk1.8.0_91\bin\javah -classpath F:\repositories\Android-Code\NDKDemo\app\build\intermediates\classes\debug -d ./app/src/main/jni com.innofang.ndkdemo.JniUtil