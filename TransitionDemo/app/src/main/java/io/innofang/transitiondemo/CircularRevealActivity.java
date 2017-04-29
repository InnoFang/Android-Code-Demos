package io.innofang.transitiondemo;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;

public class CircularRevealActivity extends AppCompatActivity {

    private static final String TAG = "CircularRevealActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);

        final View secondView = findViewById(R.id.second);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondView.setVisibility(View.VISIBLE);
                /* 找到 FAB 的圆心(在屏幕坐标轴的坐标) */
                int centerX = (v.getLeft() + v.getRight()) / 2;
                int centerY = (v.getTop() + v.getBottom()) / 2;
                /* Math.hypot(centerX, centerY) 返回 sqrt(centerX2 + centerY2) 的值*/
                float finalRadius = (float) Math.hypot(centerX, centerY);
                Log.i(TAG, "onClick: centerX " + centerX);
                Log.i(TAG, "onClick: centerY " + centerY);
                Log.i(TAG, "onClick: finalRadius " + finalRadius);
                Animator mCircularReveal = ViewAnimationUtils.createCircularReveal(
                        secondView, /* 覆盖的 View */
                        centerX,    /* 圆心横坐标 */
                        centerY,    /* 圆心纵坐标 */
                        0,          /* 圆形过度动画开始半径 */
                        finalRadius /* 圆形过度动画结束半径 */
                );
                /* 开始动画，间隔为 400 毫秒 */
                mCircularReveal.setDuration(400).start();
            }
        });
    }
}
