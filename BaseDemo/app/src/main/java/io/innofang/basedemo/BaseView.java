package io.innofang.basedemo;

/**
 * Author: Inno Fang
 * Description: The base view of M-V-P
 */


public interface BaseView<T> {

    void setPresenter(T presenter);

}
