package io.innofang.processingdemo.Box2D;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;

import io.innofang.processingdemo.Sketch;
import shiffman.box2d.Box2DProcessing;

/**
 * Author: Inno Fang
 * Time: 2017/7/29 21:56
 * Description:
 */


public class Box2DSketch extends Sketch {

    SensorManager manager;
    Sensor sensor;
    AccelerometerListener listener;
    float ax, ay, az;

    Box2DProcessing box2d;
    ArrayList<Box> boxes;
    ArrayList<Wall> walls;

    public Box2DSketch(Context context) {
        super(context);
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new AccelerometerListener();

        box2d = new Box2DProcessing(this);
        box2d.createWorld();

        // A group of boxes
        boxes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Box p = new Box(box2d, random(200, width - 200), random(200, height - 200));
            boxes.add(p);
        }

        // Invisible walls
        walls = new ArrayList<>();
        walls.add(new Wall(box2d, width / 2, -25, width, 50));
        walls.add(new Wall(box2d, width / 2, height + 25, width, 50));
        walls.add(new Wall(box2d, -25, height / 2, 50, height));
        walls.add(new Wall(box2d, width + 25, height / 2, 50, height));
    }

    public void settings() {
        fullScreen(P2D);
    }

    public void setup() {
        orientation(PORTRAIT);
    }

    public void draw() {
        background(255);

        // update gravity from accelerometer data.
        box2d.setGravity(-ax, -ay);

        box2d.step();

        for (Wall wall : walls) {
            wall.display();
        }

        for (Box box : boxes) {
            box.display();
        }

    }

    public void mousePressed() {
        for (Box box : boxes) {
            box.shake();
        }
    }

    public void onResume() {
        super.onResume();
        if (manager != null) {
            manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void onPause() {
        super.onPause();
        if (manager != null) {
            manager.unregisterListener(listener);
        }
    }

    class AccelerometerListener implements SensorEventListener {
        public void onSensorChanged(SensorEvent event) {
            ax = event.values[0];
            ay = event.values[1];
            az = event.values[2];
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }
}
