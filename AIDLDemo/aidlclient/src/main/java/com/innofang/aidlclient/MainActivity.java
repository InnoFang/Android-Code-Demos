package com.innofang.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.innofang.aidldemo.ICalcAidlInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mNum1EditText,
                    mNum2EditText,
                    mResultEditText;
    private Button mCalcButton;
    private ICalcAidlInterface mICalcAidlInterface;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 拿到了远程的服务
            mICalcAidlInterface = ICalcAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 回收资源
            mICalcAidlInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        bindService();
    }

    private void initView() {
        mNum1EditText = (EditText) findViewById(R.id.num1_edit_text);
        mNum2EditText = (EditText) findViewById(R.id.num2_edit_text);
        mResultEditText = (EditText) findViewById(R.id.result_edit_text);
        mCalcButton = (Button) findViewById(R.id.calculate_button);

        mCalcButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String num1 = mNum1EditText.getText().toString();
        String num2 = mNum2EditText.getText().toString();
        if ("".equals(num1) || ("".equals(num2)))
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
        try {//具体
            int result =  mICalcAidlInterface.add(Integer.valueOf(num1), Integer.valueOf(num2));
            mResultEditText.setText(String.valueOf(result));
        } catch (RemoteException e) {
            e.printStackTrace();
            mResultEditText.setText(R.string.error);
        }
    }

    private void bindService() {
        // 获取到服务
        Intent intent = new Intent();
        // 显示Intent启动绑定服务
        intent.setComponent(new ComponentName("com.innofang.aidldemo",
                "com.innofang.aidldemo.IRemoteService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
