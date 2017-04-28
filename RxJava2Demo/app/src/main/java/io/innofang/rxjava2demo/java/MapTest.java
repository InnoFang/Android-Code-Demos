package io.innofang.rxjava2demo.java;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 15:06
 * Description:
 */


public class MapTest {


    public static void main(String[] args) {
        /* 将数字转化为字符串 */
        Observable.just(1).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

}
