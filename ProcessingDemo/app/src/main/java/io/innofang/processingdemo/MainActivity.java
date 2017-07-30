package io.innofang.processingdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.FrameLayout;

import io.innofang.processingdemo.Box2D.Box2DSketch;
import processing.android.PFragment;
import processing.core.PApplet;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String EXTRA_MSG = "msg";

    private PApplet sketch;
    private FrameLayout frameLayout;

    public static Intent newIntent(Context context, String msg) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_MSG, msg);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.container);

        sketch = getSketch(getIntent().getStringExtra(EXTRA_MSG));
        PFragment fragment = new PFragment(sketch);
        fragment.setView(frameLayout, this);
    }

    private PApplet getSketch(String stringExtra) {
        if (TextUtils.isEmpty(stringExtra)) {
            return null;
        }

        if (getString(R.string.simple_sketch_one).equals(stringExtra)) {
            return new SimpleSketchOne(this);
        } else if (getString(R.string.simple_sketch_two).equals(stringExtra)) {
            return new SimpleSketchTwo(this);
        } else if (getString(R.string.sensor_sketch).equals(stringExtra)) {
            return new SensorSketch(this);
        } else if (getString(R.string.box2d_sketch).equals(stringExtra)) {
            return new Box2DSketch(this);
        } else if (getString(R.string.wallpapers_sketch).equals(stringExtra)) {
            return new WallpapersSketch(this);
        } else if (getString(R.string.sensor_wallpapers_sketch).equals(stringExtra)) {
            return new LocationSketch(this);
        }
        return null;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (null != sketch) {
            sketch.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
