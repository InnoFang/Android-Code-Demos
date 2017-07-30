package io.innofang.processingdemo.Box2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import processing.core.PGraphics;
import shiffman.box2d.Box2DProcessing;

/**
 * Author: Inno Fang
 * Time: 2017/7/29 22:03
 * Description:
 */


public class Wall extends PGraphics {

    Box2DProcessing box2d;
    float x;
    float y;
    float w;
    float h;

    Body b;

    Wall(Box2DProcessing box2d, float x_, float y_, float w_, float h_) {
        this.box2d = box2d;
        x = x_;
        y = y_;
        w = w_;
        h = h_;

        PolygonShape sd = new PolygonShape();
        float box2dW = box2d.scalarPixelsToWorld(w / 2);
        float box2dH = box2d.scalarPixelsToWorld(h / 2);
        sd.setAsBox(box2dW, box2dH);

        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position.set(box2d.coordPixelsToWorld(x, y));
        b = box2d.createBody(bd);

        // Attached the shape to the body using a Fixture
        b.createFixture(sd, 1);
    }

    void display() {
        fill(100);
        noStroke();
        rectMode(CENTER);
        rect(x, y, w, h);
    }
}