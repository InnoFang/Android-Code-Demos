package io.innofang.htmldemo;

import java.util.Random;

/**
 * Author: Inno Fang
 * Time: 2017/8/12 16:00
 * Description:
 */


public class ColorUtil {

    /**
     * generate random color code
     *
     * @return color code
     */
    public static String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return "#" + r + g + b;
    }

}
