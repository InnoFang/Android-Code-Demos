package com.juhezi.mvptest.model;

import com.juhezi.mvptest.util.Action;

/**
 * Created by qiao1 on 2017/1/7.
 */

public interface Response {

    void signIn(String username, String passwd, Action<Integer> action);

}
