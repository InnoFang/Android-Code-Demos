package io.innofang.rxjava2demo.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 19:39
 * Description:
 */


public class NetworkCacheObservable extends CacheObservable {
    @Override
    public Image getDataFromCache(String url) {
        Bitmap bitmap = downloadImage(url);
        if (null != bitmap)
            return new Image(url, bitmap);
        return null;
    }

    @Override
    public void putDataToCache(Image image) {

    }

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            final URLConnection conn = new URL(url).openConnection();
            inputStream = conn.getInputStream();
            bitmap =  BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(inputStream);
        }
        return bitmap;
    }
}
