package io.innofang.mvpdemo.sign_in;

import android.util.Log;

import io.innofang.mvpdemo.model.ResponseImpl;
import io.innofang.mvpdemo.util.Action;

/**
 * Created by qiao1 on 2017/1/7.
 */
public class SignInPresenter implements SignInContract.Presenter {
    private static String TAG = "SignInPresenter";

    private SignInContract.View mView;
    private ResponseImpl mResponse;


    public SignInPresenter(SignInContract.View view, ResponseImpl response) {
        mView = view;
        mView.setPresenter(this);
        mResponse = response;
    }

    @Override
    public void start() {
        Log.i(TAG, "start: ");
    }

    @Override
    public void destroy() {
        Log.i(TAG, "destroy: ");
    }

    @Override
    public void signIn(String username, String passwd, Action<Integer> action) {
        mResponse.signIn(username, passwd, action);
    }

    @Override
    public void signIn(String username, String passwd) {
        mResponse.signIn(username, passwd, new Action<Integer>() {
            @Override
            public void onAction(Integer integer) {
                if(integer == 0) {
                    mView.showToast("Success");
                } else {
                    mView.showToast("fail");
                }
            }
        });
    }
}
