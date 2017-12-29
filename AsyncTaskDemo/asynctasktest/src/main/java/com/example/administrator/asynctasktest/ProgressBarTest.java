package com.example.administrator.asynctasktest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * User: Administrator
 * Time: 2016/5/28 9:08 08
 * Annotation:
 */
public class ProgressBarTest extends Activity {

    private ProgressBar mProgressBar;
    private MyAsyncTask mTask;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.progressbar);

        mProgressBar = (ProgressBar) findViewById (R.id.pg);
        mTask = new MyAsyncTask ();
        mTask.execute ();
    }


    //让AsyncTask的生命周期跟Activity一样
    @Override
    protected void onPause () {
        super.onPause ();
        if(mTask != null && mTask.getStatus () == AsyncTask.Status.RUNNING) {
           //cancle方法只是将对应的AsyncTask标记为cancle状态，并不是真正的取消线程的执行
            mTask.cancel (true);
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground (Void... params) {
            //模拟进度更新
            for (int i = 0; i < 100; i++)
            {
                if (isCancelled ()) {
                    break;
                }
                publishProgress (i);
                //延缓更新进度
                try {
                    Thread.sleep (300);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate (Integer... values) {
            super.onProgressUpdate (values);
            if (isCancelled ()) {
                return;
            }
            //获取进度更新值
            mProgressBar.setProgress (values[0]);
        }
    }
}
