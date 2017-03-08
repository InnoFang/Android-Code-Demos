package com.innofang.apps.call_back_demo_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author: Inno Fang
 * Time: 2017/3/7 20:59
 * Description:
 */


public class ImageUtil {

    public static void loadeIamge(final String url, final LoadeImageListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                BufferedInputStream bis = null;
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setConnectTimeout(5000);
                    connection.connect();
                    is = connection.getInputStream();
                    bis = new BufferedInputStream(is);
                    listener.onLoadeImageListener(BitmapFactory.decodeStream(bis));
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    CloseUtil.closeQuietly(is);
                    CloseUtil.closeQuietly(bis);
                }
            }
        }).start();
    }

    public interface LoadeImageListener {
         void onLoadeImageListener(Bitmap bitmap);
     }
}
