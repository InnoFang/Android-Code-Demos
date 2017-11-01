package com.innofang.callbackdemo.call_back_demo_0;

/**
 * Author: Inno Fang
 * Time: 2017/3/15 17:04
 * Description:
 */


public class Boss  implements CallBack {

    private Wang wang;

    public Boss(Wang wang){
        this.wang = wang;
    }

    public void doThings(){
        for (int i = 0; i < 10; i++) {
            System.out.println("招呼客人");
            if (i == 5) { // 没鞋了，叫小王去拿鞋
                wang.doWork(this);
            }
        }
    }

    @Override
    public void call() {
        System.out.println("小王去库房拿鞋");
    }
}