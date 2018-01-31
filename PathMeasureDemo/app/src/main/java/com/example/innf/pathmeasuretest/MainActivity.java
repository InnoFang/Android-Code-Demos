package com.example.innf.pathmeasuretest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pathTracing(View view) {
        startActivity(new Intent(this, PathTracingActivity.class));
    }

    public void pathPaint(View view) {
        startActivity(new Intent(this, PathPaintActivity.class));
    }

    public void pathPosTan(View view) {
        startActivity(new Intent(this, PathPosTanActivity.class));
    }
}
