package com.juhezi.mvptest.model;

import com.juhezi.mvptest.model.local.LocalResponse;
import com.juhezi.mvptest.model.remote.RemoteResponse;
import com.juhezi.mvptest.util.Action;

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
    public void signIn(final String username, final String passwd, final Action<Integer> action) {

        mRemoteResponse.signIn(username, passwd, integer -> {
            if (integer == 0) {  //OK
                action.onAction(0);
            } else {
                mLocalResponse.signIn(username, passwd, action);
            }
        });

    }
}
