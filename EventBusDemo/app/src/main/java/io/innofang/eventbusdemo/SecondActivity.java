package io.innofang.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_event_message:
                EventBus.getDefault().post(new MessageEvent(getString(R.string.message)));
                break;
            case R.id.send_sticky_event_message:
                EventBus.getDefault().postSticky(new MessageEvent(getString(R.string.sticky_message)));
                break;
        }
        finish();
    }
}
