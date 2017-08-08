package io.innofang.modularizationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.innofang.library.router.RouterBus;
import io.innofang.library.router.module_a.RouterModuleA;
import io.innofang.modulea.ModuleAActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = RouterBus
                .getRouter(RouterModuleA.class)
                .newIntentFromModuleA("info from module A");
        if (null != intent) {
            startActivity(new Intent(MainActivity.this, ModuleAActivity.class));
        }
    }
}
