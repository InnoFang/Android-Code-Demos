package io.innofang.transitiondemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CircularReveal(View view) {
        startActivity(new Intent(this, CircularRevealActivity.class));
    }

    public void windowTransitions(View view) {
        startActivity(new Intent(this, WindowTransitionsActivity.class));
    }

    public void sharedElement(View view) {
        startActivity(new Intent(this, SharedElementActivity.class));
    }

    public void customTransition(View view) {
        startActivity(new Intent(this, CustomTransitionActivity.class));
    }
}
