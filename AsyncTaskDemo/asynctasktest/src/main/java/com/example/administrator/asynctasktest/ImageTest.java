package com.example.administrator.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.net.URLConnection;

/**
 * User: Administrator
 * Time: 2016/5/28 8:45 45
 * Annotation:
 */
public class ImageTest extends Activity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private static String URL = "http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.image);

        mImageView = (ImageView) findViewById (R.id.image);
        mProgressBar = (ProgressBar) findViewById (R.id.progressbar);
        //设置传递进去的参数
        new MyAsyncTask ().execute (URL);
    }

    class MyAsyncTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected void onPreExecute () {
            super.onPreExecute ();
            mProgressBar.setVisibility (View.VISIBLE);
        }

        @Override
        protected void onPostExecute (Bitmap bitmap) {
            super.onPostExecute (bitmap);
            //操作UI，设置图片
            mProgressBar.setVisibility (View.GONE);
            mImageView.setImageBitmap (bitmap);
        }

        @Override
        protected Bitmap doInBackground (String... params) {
            //获取传递进来的参数
            String url = params[0]; // Get the URL
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;

            try {
                connection = new URL(url).openConnection ();
                is = connection.getInputStream ();
                BufferedInputStream bis = new BufferedInputStream (is);
                Thread.sleep (3000);
                //通过decodeStream方法解析输入流
                bitmap = BitmapFactory.decodeStream (bis);
                bis.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            //将Bitmap作为返回值
            return bitmap;
        }
    }
}
