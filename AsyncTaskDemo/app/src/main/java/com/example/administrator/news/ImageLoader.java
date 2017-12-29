package com.example.administrator.news;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: Administrator
 * Time: 2016/5/28 13:12 12
 * Annotation:
 */
public class ImageLoader {

    private ImageView mImageView;
    private String mUrl;
    //创建Cache
    private LruCache<String,Bitmap> mCaches;

    public ImageLoader(){
        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime ().maxMemory ();
        int cacheSize = maxMemory / 4;
        mCaches = new LruCache<String,Bitmap> (cacheSize){
            @Override
            protected int sizeOf (String key, Bitmap value) {
                //在每次存入缓存的时候调用
                return value.getByteCount ();
            }
        };
    }

    //增加到缓存
    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache (url) == null) {
            mCaches.put (url,bitmap);
        }
    }
    //从缓存中获取数据
    public Bitmap getBitmapFromCache(String url) {
        return mCaches.get (url);
    }

    private Handler handler = new Handler () {
        @Override
        public void handleMessage (Message msg) {
            super.handleMessage (msg);
            if (mImageView.getTag ().equals (mUrl)) {
                mImageView.setImageBitmap ((Bitmap) msg.obj);
            }
        }
    };

    public void showImageByThread(ImageView imageView, final String url) {
        mImageView = imageView;
        mUrl = url;
        new Thread () {
            @Override
            public void run () {
                super.run ();
                Bitmap bitmap = getBitmapFromURL (url);
                Message message = Message.obtain ();
                message.obj = bitmap;
                handler.sendMessage (message);
            }
        }.start ();
    }

    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL (urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection ();
            is = new BufferedInputStream (connection.getInputStream ());
            bitmap = BitmapFactory.decodeStream (is);
            connection.disconnect ();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace ();
        }finally {
            try {
                is.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return null;
    }

    public void showImageByAsyncTask(ImageView imageView, String url) {
        //从缓存中取出图片
        Bitmap bitmap = getBitmapFromCache (url);
        //如果缓存中没有，那么就必须去下载
        if (bitmap == null) {

            new NewsAsyncTask (imageView,url).execute (url);
        }else {
            imageView.setImageBitmap (bitmap);
        }
    }

    private class NewsAsyncTask extends AsyncTask<String,Void,Bitmap> {

        private ImageView mImageView;
        private String mUrl;

        public NewsAsyncTask(ImageView imageView, String url) {
            mImageView = imageView;
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground (String... params) {
            String url = params[0];
            //从网络获取图片
            Bitmap bitmap = getBitmapFromURL (url);
            if(bitmap != null) {
                //将不再缓存的图片加入缓存
                addBitmapToCache (url,bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute (Bitmap bitmap) {
            super.onPostExecute (bitmap);
            if (mImageView.getTag ().equals (mUrl)) {

                mImageView.setImageBitmap (bitmap);
            }
        }
    }
}
