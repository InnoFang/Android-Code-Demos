package io.innofang.processingdemo;

import android.content.Context;

/**
 * Author: Inno Fang
 * Time: 2017/7/29 17:57
 * Description:
 */

public class SimpleSketchOne extends Sketch {

    public SimpleSketchOne(Context context) {
        super(context);
    }

    @Override
    public void setup() {}

    @Override
    public void draw() {
        if (mousePressed) {
            ellipse(mouseX, mouseY, 50, 50);
        }
    }
}