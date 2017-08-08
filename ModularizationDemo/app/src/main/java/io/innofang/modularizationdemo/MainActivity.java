package io.innofang.modularizationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.innofang.library.base.BaseActivity;
import io.innofang.library.router.FunctionBus;
import io.innofang.library.router.RouterBus;
import io.innofang.library.router.module_a.FunctionModuleA;
import io.innofang.library.router.module_a.RouterModuleA;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = RouterBus
                .getRouter(RouterModuleA.class)
                .newIntentFromModuleA("info from main module");
        if (null != intent) {
            FunctionBus.setFunction(new FunctionModuleA() {
                @Override
                public Object getData() {
                    return "Use functionBus";
                }
            });
            Log.i("tag", intent.getAction());
            Log.i("tag", intent.getData().getPath());
            startActivity(intent);
        }
    }
}
