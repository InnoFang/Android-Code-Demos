package io.innofang.mvvmdemo.event;

/**
 * Author: Inno Fang
 * Time: 2017/9/7 17:25
 * Description:
 */


public interface Action<T> {
    void onAction(T t);
}
