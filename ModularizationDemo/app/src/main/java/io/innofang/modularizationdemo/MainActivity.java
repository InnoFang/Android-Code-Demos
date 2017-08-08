package io.innofang.modularizationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import io.innofang.library.base.BaseActivity;
import io.innofang.library.router.FunctionBus;
import io.innofang.library.router.RouterBus;
import io.innofang.library.router.module_a.FunctionModuleA;
import io.innofang.library.router.module_a.RouterModuleA;
import io.innofang.library.router.module_b.FunctionModuleB;
import io.innofang.library.router.module_b.RouterModuleB;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.module_a_button:
                intent = RouterBus
                        .getRouter(RouterModuleA.class)
                        .newIntentFromModuleA("info from main module");
                if (null != intent) {
                    FunctionBus.setFunction(new FunctionModuleA() {
                        @Override
                        public Object getData() {
                            return "Use functionBus";
                        }
                    });
                    startActivity(intent);
                }
                break;
            case R.id.module_b_button:
                String input = ((EditText) findViewById(R.id.num_edit_text)).getText().toString();
                if (!TextUtils.isEmpty(input)) {
                    final int num = Integer.valueOf(input);
                    intent = RouterBus
                            .getRouter(RouterModuleB.class)
                            .newIntentFromModuleB(num);
                    FunctionBus.setFunction(new FunctionModuleB() {
                        @Override
                        public Integer getData() {
                            return num;
                        }
                    });
                    if (null != intent) {
                        startActivity(intent);
                    }
                }

                break;
            default:
                break;
        }

    }
}
