package io.innofang.processingdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Author: Inno Fang
 * Time: 2017/7/29 20:47
 * Description:
 */


public class SensorSketch extends Sketch {

    SensorManager mManager;
    Sensor mSensor;
    AccelerometerListener mListener;
    float ax, ay, az;

    public SensorSketch(Context context) {
        super(context);

        mManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mListener = new AccelerometerListener();
    }

    @Override
    public void settings() {
        super.settings();
        fullScreen();
    }


    public void setup() {
        textFont(createFont("SansSerif", 30 * displayDensity));
    }

    public void draw() {
        background(0);
        fill(0, 255, 255);
        text("X: " + ax + "\nY: " + ay + "\nZ: " + az,
                0,
                0,
                width,
                height);
    }

    class AccelerometerListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            ax = event.values[0];
            ay = event.values[1];
            az = event.values[2];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mManager) {
            mManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != mManager) {
            mManager.unregisterListener(mListener);
        }
    }
}
