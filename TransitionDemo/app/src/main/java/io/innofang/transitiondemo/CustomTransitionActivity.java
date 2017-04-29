package io.innofang.transitiondemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CustomTransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_transition);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, CustomTransitionSecondActivity.class),
                ActivityOptions
                        .makeSceneTransitionAnimation(this, view, "transition_morph_view")
                        .toBundle());
    }
}
