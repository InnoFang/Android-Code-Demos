package com.juhezi.mvptest.sign_in;

import com.juhezi.mvptest.BasePresenter;
import com.juhezi.mvptest.BaseView;
import com.juhezi.mvptest.util.Action;

/**
 * Created by qiao1 on 2017/1/7.
 */

public interface SignInContract {

    interface Presenter extends BasePresenter {

        void signIn(String username, String passwd, Action<Integer> action);

        void signIn(String username, String passwd);

    }

    interface View extends BaseView<Presenter> {
        String getUsername();

        String getPasswd();

        void showToast(String message);
    }

}
