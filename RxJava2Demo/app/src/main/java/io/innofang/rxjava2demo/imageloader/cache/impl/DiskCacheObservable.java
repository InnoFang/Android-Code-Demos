package io.innofang.rxjava2demo.imageloader.cache.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.innofang.rxjava2demo.imageloader.bean.Image;
import io.innofang.rxjava2demo.imageloader.cache.CacheObservable;
import io.innofang.rxjava2demo.imageloader.utils.CloseUtil;
import io.innofang.rxjava2demo.imageloader.utils.DiskCacheUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 19:38
 * Description:
 */


public class DiskCacheObservable extends CacheObservable {

    private DiskLruCache mDiskLruCache;

    private Context mContext;
    /* DiskLruCache 中对于图片的最大缓存值 */
    private int maxSize = 20 * 1024 * 1024;

    public DiskCacheObservable(Context context) {
        mContext = context;
        initDiskLruCache();
    }

    @Override
    public Image getDataFromCache(String url) {
        Bitmap bitmap = getDataFromDiskLruCache(url);
        if (null != bitmap) {
            Log.i("cache", "getDataFromDisk: is called");
            return new Image(url, bitmap);
        }
        return null;
    }

    @Override
    public void putDataToCache(final Image image) {

        Observable.create(new ObservableOnSubscribe<Image>() {
            @Override
            public void subscribe(ObservableEmitter<Image> e) throws Exception {
                putDataToDiskLruCache(image);
            }
        }).subscribeOn(Schedulers.io()).subscribe();


    }

    /**
     * 初始化 DiskLruCache
     */
    private void initDiskLruCache() {
        try {
            File cacheDir = DiskCacheUtil.getDiskCacheDir(mContext, "image_cache");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            int versionCode = DiskCacheUtil.getAppVersionCode(mContext);
            mDiskLruCache = DiskLruCache.open(cacheDir, versionCode, 1, maxSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getDataFromDiskLruCache(String url) {
        FileDescriptor fileDescriptor = null;
        FileInputStream fileInputStream = null;
        DiskLruCache.Snapshot snapShot = null;

        try {
            /* 生成图片对应的 MD5 值 */
            final String key = DiskCacheUtil.getMD5String(url);
            snapShot = mDiskLruCache.get(key);
            if (null != snapShot) {
                fileInputStream = (FileInputStream) snapShot.getInputStream(0);
                fileDescriptor = fileInputStream.getFD();
            }
            /* 将缓存数据解析成 Bitmap 对象 */
            Bitmap bitmap = null;
            if (null != fileDescriptor) {
                bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            }
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeQuietly(fileInputStream);
        }
        return null;
    }

    private void putDataToDiskLruCache(Image image) {

        try {
            /* 第一步：获取将要缓存的图片的对应唯一 key 值 */
            String key = DiskCacheUtil.getMD5String(image.getUrl());
        /* 第二步：获取 DiskLruCache 的 Editor */
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);

            if (null != editor) {
                /* 第三步：从 Editor 中获取 OutputStream */
                OutputStream outputStream = editor.newOutputStream(0);
                /* 第四步：下载网络图片且保存至 DiskLruCache 图片中 */
                boolean isSuccessful = download(image.getUrl(), outputStream);
                if (isSuccessful) {
                    editor.commit();
                } else {
                    editor.abort();
                }
                mDiskLruCache.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean download(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {

            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            CloseUtil.closeQuietly(out);
            CloseUtil.closeQuietly(in);
        }
        return false;
    }
}
