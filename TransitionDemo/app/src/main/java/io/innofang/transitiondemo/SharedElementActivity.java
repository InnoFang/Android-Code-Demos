package io.innofang.transitiondemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SharedElementActivity extends AppCompatActivity {

    private View mFirstSharedView;
    private View mSecondSharedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element);
        getSupportActionBar().setTitle("第一个Activity");

        mFirstSharedView = findViewById(R.id.first_shared_view);
        mSecondSharedView = findViewById(R.id.secondSharedView);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, SharedElementSecondActivity.class);

//        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, firstSharedView, "sharedView").toBundle());

        /* 要实现这种共享元素的过渡，那么就需要在两个布局对应的 view 中指定相同的 android:transitionName="" */
        Pair first = new Pair<>(mFirstSharedView, ViewCompat.getTransitionName(mFirstSharedView));
        Pair second = new Pair<>(mSecondSharedView, ViewCompat.getTransitionName(mSecondSharedView));

        ActivityOptionsCompat transitionActivityOptions =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this, first, second);

        ActivityCompat.startActivity(this,
                intent, transitionActivityOptions.toBundle());
    }
}
