package com.innofang.databingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }

    public void goToMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void goToList(View view) {
        startActivity(new Intent(this, ListActivity.class));
    }

    public void goToExpression(View view) {
        startActivity(new Intent(this, ExpressionActivity.class));
    }

    public void goToTwoWay(View view) {
        startActivity(new Intent(this, TwoWayActivity.class));
    }

    public void goToLambda(View view) {
        startActivity(new Intent(this, LambdaActivity.class));
    }

    public void goToAnimation(View view) {
        startActivity(new Intent(this, AnimationActivity.class));
    }
}
