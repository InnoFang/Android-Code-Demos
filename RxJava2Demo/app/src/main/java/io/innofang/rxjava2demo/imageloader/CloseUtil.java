package io.innofang.rxjava2demo.imageloader;

import java.io.Closeable;
import java.io.IOException;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 20:02
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
