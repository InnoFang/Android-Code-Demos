package io.innofang.arouterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        //startActivity(new Intent(MainActivity.this, ModuleAActivity.class));
//        ARouter.getInstance().build("/module_a/1").navigation();


        ARouter.getInstance()
                .build("/module_a/1")
                .withInt("num", 10)
                .withString("str", "hello")
                .navigation(this, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        Toast.makeText(MainActivity.this, "Please waiting...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onArrival(Postcard postcard) {

                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {

                    }
                });

    }
}
