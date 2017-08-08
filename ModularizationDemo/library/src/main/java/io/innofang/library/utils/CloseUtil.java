package io.innofang.library.utils;

import java.io.Closeable;
import java.io.IOException;

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
