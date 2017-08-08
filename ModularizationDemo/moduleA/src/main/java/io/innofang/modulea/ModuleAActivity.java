package io.innofang.modulea;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.innofang.library.base.BaseActivity;
import io.innofang.library.router.FunctionBus;
import io.innofang.library.router.module_a.FunctionModuleA;

public class ModuleAActivity extends BaseActivity {

    private static final String TAG = "ModuleAActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_a);


        StringBuilder sb = new StringBuilder();

        Uri data = getIntent().getData();
        if (data != null) {
            Log.i(TAG, "onCreate: " + data.getPath());
            String info = data.getQueryParameter("info");
            sb.append(info).append("\n");
        }

        FunctionModuleA function = FunctionBus.getFunction(FunctionModuleA.class);
        if (function != null) {
            sb.append((CharSequence) function.getData());
        }

        ((TextView) findViewById(R.id.info_text_view)).setText(sb.toString());
    }
}
