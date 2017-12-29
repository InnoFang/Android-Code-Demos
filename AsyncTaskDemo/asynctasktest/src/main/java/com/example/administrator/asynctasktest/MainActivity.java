package com.example.administrator.asynctasktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        MyAsyncTask task = new MyAsyncTask ();
        task.execute ();
    }

    public void loadImage(View view) {
        startActivity (new Intent (this,ImageTest.class));
    }
    public void loadProgress(View view) {
        startActivity (new Intent (this,ProgressBarTest.class));
    }
}
