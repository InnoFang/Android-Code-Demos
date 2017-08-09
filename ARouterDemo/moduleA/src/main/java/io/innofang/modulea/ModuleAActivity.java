package io.innofang.modulea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/module_a/1")
public class ModuleAActivity extends AppCompatActivity {

    @Autowired
    public int num;
    @Autowired
    public String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_a);

        ARouter.getInstance().inject(this);

        Toast.makeText(this, "num: " + num + " str: " + str, Toast.LENGTH_SHORT).show();
    }
}
