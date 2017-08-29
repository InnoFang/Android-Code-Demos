package io.innofang.junittestdemo.utils;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

/**
 * Author: Inno Fang
 * Time: 2017/8/29 16:41
 * Description:
 */

public class CalculateUtilTest {
    @Test
    public void add() throws Exception {
        assertEquals(2, CalculateUtil.add(1, 1), 1e-6);
        assertEquals(10, CalculateUtil.add(4, 6), 1e-6);
        assertEquals(100, CalculateUtil.add(30, 70), 1e-6);
    }

    @Test
    public void div() throws Exception {
        assertEquals(2, CalculateUtil.div(4, 2), 1e-6);
        assertEquals(22, CalculateUtil.div(50, 28), 1e-6);
        assertEquals(100, CalculateUtil.div(150, 50), 1e-6);
    }

    @Test
    public void mul() throws Exception {
        assertEquals(24, CalculateUtil.mul(4, 6), 1e-6);
        assertEquals(24, CalculateUtil.mul(3, 8), 1e-6);
        assertEquals(24, CalculateUtil.mul(2, 12), 1e-6);
    }

    @Test
    public void sub() throws Exception {
        assertEquals(2, CalculateUtil.sub(8, 4), 1e-6);
        assertEquals(2, CalculateUtil.sub(50, 25), 1e-6);
        assertEquals(2, CalculateUtil.sub(6, 3), 1e-6);
    }

}