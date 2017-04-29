package io.innofang.rxjava2demo.imageloader;

import android.graphics.Bitmap;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 19:23
 * Description:
 */


public class Image {

    private String mUrl;

    private Bitmap mBitmap;

    public Image(String url, Bitmap bitmap) {
        mUrl = url;
        mBitmap = bitmap;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    @Override
    public String toString() {
        return "Image{" +
                "mUrl='" + mUrl + '\'' +
                ", mBitmap=" + mBitmap +
                '}';
    }
}