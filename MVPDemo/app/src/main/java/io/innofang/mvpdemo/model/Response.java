package io.innofang.mvpdemo.model;

import io.innofang.mvpdemo.util.Action;

/**
 * Created by qiao1 on 2017/1/7.
 */

public interface Response {

    void signIn(String username, String passwd, Action<Integer> action);

}
