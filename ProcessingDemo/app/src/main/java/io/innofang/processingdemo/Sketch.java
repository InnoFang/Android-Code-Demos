package io.innofang.processingdemo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import processing.core.PApplet;

/**
 * Author: Inno Fang
 * Time: 2017/7/29 21:12
 * Description:
 */


public abstract class Sketch extends PApplet {

    protected int width;
    protected int height;

    @SuppressWarnings("deprecation")
    public Sketch(Context context) {

        WindowManager wm = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        /*
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        */

        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    public void settings() {
        size(width, height);
    }

}
