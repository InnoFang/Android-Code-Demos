package com.innofang.apps.call_back_demo_0;

/**
 * Author: Inno Fang
 * Time: 2017/3/15 17:05
 * Description:
 */


public class Test {
    public static void main(String[] args) {
        Wang wang = new Wang();
        Boss boss = new Boss(wang);
        boss.doThings();
    }
}
