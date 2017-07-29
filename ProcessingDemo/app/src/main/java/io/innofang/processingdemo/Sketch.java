package io.innofang.processingdemo;

import processing.core.PApplet;

/**
 * Author: Inno Fang
 * Time: 2017/7/29 17:57
 * Description:
 */

public class Sketch extends PApplet {

    public void settings() {
        size(2000, 2000);
    }

    public void setup() { }

    public void draw() {
        if (mousePressed) {
            ellipse(mouseX, mouseY, 50, 50);
        }
    }
}