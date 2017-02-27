package com.innofang.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author: Inno Fang
 * Time: 2017/1/20 21:57
 * Description:
 */

public class IRemoteService extends Service {
    private static final String TAG = "IRemoteService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    private IBinder mIBinder = new ICalcAidlInterface.Stub() {

        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.i(TAG, "received a remote request," +
                    " the input parameters are : " + num1 + " and " + num2);
            return num1 + num2;
        }
    };

}
