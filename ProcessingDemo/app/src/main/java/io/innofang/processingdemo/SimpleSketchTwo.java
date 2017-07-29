package io.innofang.processingdemo;

import android.content.Context;

/**
 * Author: Inno Fang
 * Time: 2017/7/29 17:57
 * Description:
 */

public class SimpleSketchTwo extends Sketch {


    public SimpleSketchTwo(Context context) {
        super(context);
    }


    @Override
    public void settings() {
        super.settings();
        fullScreen();
    }

    @Override
    public void setup() {
        noStroke();
        fill(0);
    }

    @Override
    public void draw() {
        background(204);
        if (mousePressed) {
            if (mouseX < width / 2) {
                rect(0, 0, width / 2, height); // Left
            } else {
                rect(width / 2, 0, width / 2, height); // Right
            }
        }
    }
}