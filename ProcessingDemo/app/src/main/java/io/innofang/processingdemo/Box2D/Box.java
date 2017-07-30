package io.innofang.processingdemo.Box2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.util.Random;

import processing.core.PGraphics;
import shiffman.box2d.Box2DProcessing;

/**
 * Author: Inno Fang
 * Time: 2017/7/29 22:03
 * Description:
 */


public class Box extends PGraphics {

    Box2DProcessing box2d;

    Body body;
    float w;
    float h;

    // Constructor
    Box(Box2DProcessing box2d, float x, float y) {
        this.box2d = box2d;
        w = random(50, 100);
        h = random(50, 100);
        // Add the box to the box2d world
        makeBody(new Vec2(x, y), w, h);

        /* for PApplet
        String var7 = this.sketchRenderer();
        this.g = this.makeGraphics(this.width, this.height, var7, true);*/
    }

    void display() {

        Vec2 pos = box2d.getBodyPixelCoord(body);
        float a = body.getAngle();

        rectMode(CENTER);
        pushMatrix();
        translate(pos.x, pos.y);
        rotate(-a);
        fill(175);
        stroke(0);
        rect(0, 0, w, h);
        popMatrix();
    }

    void makeBody(Vec2 center, float w_, float h_) {
        // Define a polygon (this is what we use for a rectangle)
        PolygonShape sd = new PolygonShape();
        float box2dW = box2d.scalarPixelsToWorld(w_ / 2);
        float box2dH = box2d.scalarPixelsToWorld(h_ / 2);
        sd.setAsBox(box2dW, box2dH);

        // Define a fixture
        FixtureDef fd = new FixtureDef();
        fd.shape = sd;
        // Parameters that affect physics
        fd.density = 1;
        fd.friction = 0.3F;
        fd.restitution = 0.5F;

        // Define the body and make it from the shape
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(box2d.coordPixelsToWorld(center));

        body = box2d.createBody(bd);
        body.createFixture(fd);

        // Give it some initial random velocity
        shake();
    }

    void shake() {
        body.setLinearVelocity(new Vec2(random(-5, 5), random(2, 5)));
        body.setAngularVelocity(random(-5, 5));
    }


    // for PGraphics
    Random internalRandom;

    public final float random(float var1, float var2) {
        if(var1 >= var2) {
            return var1;
        } else {
            float var3 = var2 - var1;
            return this.random(var3) + var1;
        }
    }

    public final float random(float var1) {
        if(var1 == 0.0F) {
            return 0.0F;
        } else {
            if(this.internalRandom == null) {
                this.internalRandom = new Random();
            }

            float var2 = 0.0F;

            do {
                var2 = this.internalRandom.nextFloat() * var1;
            } while(var2 == var1);

            return var2;
        }
    }
}