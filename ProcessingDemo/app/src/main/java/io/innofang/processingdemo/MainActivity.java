package io.innofang.processingdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import processing.android.PFragment;
import processing.core.PApplet;

public class MainActivity extends AppCompatActivity {


    private PApplet sketch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);

        sketch = new Sketch();
        PFragment fragment = new PFragment(sketch);
        fragment.setView(frameLayout, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (null != sketch) {
            sketch.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
