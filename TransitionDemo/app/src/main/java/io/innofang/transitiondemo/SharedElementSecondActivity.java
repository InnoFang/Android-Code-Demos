package io.innofang.transitiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SharedElementSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_second);
        getSupportActionBar().setTitle("第二个Activity");
    }

    public void onClick(View v) {
        finish();
    }
}
