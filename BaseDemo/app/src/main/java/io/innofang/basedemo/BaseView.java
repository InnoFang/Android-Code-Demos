package io.innofang.basedemo;

/**
 * Author: Inno Fang
 * Time: 2017/4/25 09:18
 * Description: The base view of M-V-P
 */


public interface BaseView<T> {

    void setPresenter(T presenter);

}
