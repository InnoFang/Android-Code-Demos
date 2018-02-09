package io.innofang.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If you want to handle sticky message, please comment out the following code
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.intent_to_second_button:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.handle_sticky_button:
                EventBus.getDefault().register(this);
                break;
        }
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandleMessage(MessageEvent messageEvent) {
        Toast.makeText(this, "Message: " + messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe(sticky = true)
    public void onHandleStickyMessage(MessageEvent messageEvent) {
        Toast.makeText(this, "Sticky Message: " + messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
