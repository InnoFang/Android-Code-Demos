package com.innofang.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.innofang.recyclerviewdemo.grid.GridActivity;
import com.innofang.recyclerviewdemo.linear.LinearActivity;
import com.innofang.recyclerviewdemo.mixture.MixtureActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onGotoMixture(View view) {
        startActivity(new Intent(this, MixtureActivity.class));
    }

    public void onGotoLinear(View view) {
        startActivity(new Intent(this, LinearActivity.class));
    }

    public void onGotoGrid(View view) {
        startActivity(new Intent(this, GridActivity.class));
    }
}
