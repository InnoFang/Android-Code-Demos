package io.innofang.mvpdemo.sign_in;

import io.innofang.mvpdemo.BasePresenter;
import io.innofang.mvpdemo.BaseView;
import io.innofang.mvpdemo.util.Action;

/**
 * Created by qiao1 on 2017/1/7.
 */

public interface SignInContract {

    interface Presenter extends BasePresenter {

        void signIn(String username, String password, Action<Integer> action);

        void signIn(String username, String password);

    }

    interface View extends BaseView<Presenter> {
        String getUsername();

        String getPassword();

        void showToast(String message);
    }

}
