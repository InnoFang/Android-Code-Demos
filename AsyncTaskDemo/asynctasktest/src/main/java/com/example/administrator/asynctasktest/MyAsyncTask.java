package com.example.administrator.asynctasktest;

import android.os.AsyncTask;
import android.util.Log;

/**
 * User: Administrator
 * Time: 2016/5/28 8:35 35
 * Annotation:
 */
public class MyAsyncTask extends AsyncTask<Void,Void,Void> {


    @Override
    protected Void doInBackground (Void... params) {
        Log.d("xys","doInBackground");
        publishProgress ();
        return null;
    }

    @Override
    protected void onPreExecute () {
        super.onPreExecute ();
        Log.d ("xys", "onPreExecute");
    }

    @Override
    protected void onPostExecute (Void aVoid) {
        super.onPostExecute (aVoid);
        Log.d ("xys", "onPostExecute");
    }

    @Override
    protected void onProgressUpdate (Void... values) {
        super.onProgressUpdate (values);
        Log.d ("xys", "onProgressUpdate");
    }
}
