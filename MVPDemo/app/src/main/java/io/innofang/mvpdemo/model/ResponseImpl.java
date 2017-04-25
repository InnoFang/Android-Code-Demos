package io.innofang.mvpdemo.model;


import io.innofang.mvpdemo.model.local.LocalResponse;
import io.innofang.mvpdemo.model.remote.RemoteResponse;
import io.innofang.mvpdemo.util.Action;

/**
 * Created by qiao1 on 2017/1/7.
 */
public class ResponseImpl implements Response {
    private static String TAG = "ResponseImpl";

    private LocalResponse mLocalResponse;
    private RemoteResponse mRemoteResponse;

    public ResponseImpl(LocalResponse localResponse,
                        RemoteResponse remoteResponse) {
        mLocalResponse = localResponse;
        mRemoteResponse = remoteResponse;
    }


    @Override
    public void signIn(final String username, final String password, final Action<Integer> action) {

        mRemoteResponse.signIn(username, password, new Action<Integer>() {

            @Override
            public void onAction(Integer integer) {

            }
        });

    }
}
