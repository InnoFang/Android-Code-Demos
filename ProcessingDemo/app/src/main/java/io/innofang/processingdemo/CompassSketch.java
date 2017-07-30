package io.innofang.processingdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Author: Inno Fang
 * Time: 2017/7/30 13:15
 * Description:
 */


public class CompassSketch extends Sketch {

    SensorListener listener;
    SensorManager manager;
    Sensor accelerometer;
    Sensor magnetometer;

    float easing = 0.01F;

    float azimuth;
    float pitch;
    float roll;


    public CompassSketch(Context context) {
        super(context);

        listener = new SensorListener();
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer  = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        manager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(listener, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void settings() {
        fullScreen(P2D);
    }

    public void setup() {
        orientation(PORTRAIT);
    }

    public void draw() {
        background(255);

        float cx = width * 0.5F;
        float cy = height * 0.4F;
        float radius = cx * 0.8F;

        translate(cx, cy);

        noFill();
        stroke(0);
        strokeWeight(2);
        ellipse(0, 0, radius * 2, radius * 2);
        line(0, -cy, 0, -radius);

        fill(192, 0, 0);
        noStroke();
        rotate(-azimuth);
        beginShape();
        vertex(-30, 40);
        vertex(0, 0);
        vertex(30, 40);
        vertex(0, -radius);
        endShape();
    }

    public void resume() {
        if (manager != null) {
            manager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            manager.registerListener(listener, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void pause() {
        if (manager != null) {
            manager.unregisterListener(listener);
        }
    }

    class SensorListener implements SensorEventListener {
        float[] gravity = new float[3];
        float[] geomagnetic = new float[3];
        float[] I = new float[16];
        float[] R = new float[16];
        float[] orientation = new float[3];

        public void onSensorChanged(SensorEvent event) {
            if (event.accuracy == SensorManager.SENSOR_STATUS_ACCURACY_LOW) return;

            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                arrayCopy(event.values, geomagnetic);
            }
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                arrayCopy(event.values, gravity);
            }
            if (SensorManager.getRotationMatrix(R, I, gravity, geomagnetic)) {
                SensorManager.getOrientation(R, orientation);
                azimuth += easing * (orientation[0] - azimuth);
                pitch += easing * (orientation[1] - pitch);
                roll += easing * (orientation[2] - roll);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }
}
