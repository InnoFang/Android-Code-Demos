package io.innofang.basedemo.utils.event;

/**
 * Author: Inno Fang
 * Time: 2017/4/25 11:40
 * Description: the callback
 */


public interface Action<T> {

    void onAction(T t);

}