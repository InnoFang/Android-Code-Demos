package io.innofang.processingdemo;

import android.content.Context;

/**
 * Author: Inno Fang
 * Time: 2017/7/30 13:15
 * Description:
 */


public class WallpapersSketch extends Sketch {

    float currH, currB;
    float nextH, nextB;
    float easing = 0.001F;
    int lastChange = 0;

    public WallpapersSketch(Context context) {
        super(context);
    }

    public void settings() {
        fullScreen(P2D);
    }

    public void setup() {
        colorMode(HSB, 100);
        currH = nextH = 100;
        currB = nextB = 100;
    }

    public void draw() {
        background(currH, currB, 100);
        updateCurrColor();
        if (5000 < millis() - lastChange) {
            pickNextColor();
            lastChange = millis();
        }
    }

    private void updateCurrColor() {
        // Easing between current and next colors
        currH += easing * (nextH - currH);
        currB += easing * (nextB - currB);
    }

    private void pickNextColor() {
        nextH = random(100);
        nextB = random(100);
    }
}
