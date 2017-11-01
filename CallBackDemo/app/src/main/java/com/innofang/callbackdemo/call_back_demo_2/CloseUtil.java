package com.innofang.callbackdemo.call_back_demo_2;

import java.io.Closeable;
import java.io.IOException;

/**
 * Author: Inno Fang
 * Time: 2017/3/7 21:16
 * Description:
 */


public class CloseUtil {

    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
