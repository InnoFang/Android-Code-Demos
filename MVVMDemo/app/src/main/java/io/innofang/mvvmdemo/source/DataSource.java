package io.innofang.mvvmdemo.source;

import io.innofang.mvvmdemo.bean.UserBean;
import io.innofang.mvvmdemo.event.Action;

/**
 * Author: Inno Fang
 * Time: 2017/9/7 17:25
 * Description:
 */


public interface DataSource {
    void signIn(UserBean userBean, Action<Integer> action);
}
