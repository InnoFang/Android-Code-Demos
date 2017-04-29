package io.innofang.transitiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ArcMotion;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import io.innofang.transitiondemo.util.CustomChangeBounds;

public class CustomTransitionSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_transition_second);
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        getSupportActionBar().setTitle("第二个Activity");

        /*  自定义 Shared Element切换动画 */

        //定义ArcMotion
        ArcMotion arcMotion = new ArcMotion();
        arcMotion.setMinimumHorizontalAngle(50f);
        arcMotion.setMinimumVerticalAngle(50f);

        // 设置插值器 - 慢进快出
        Interpolator interpolator = AnimationUtils
                .loadInterpolator(this, android.R.interpolator.fast_out_slow_in);

        //实例化自定义的ChangeBounds
        CustomChangeBounds changeBounds = new CustomChangeBounds();
        changeBounds.setPathMotion(arcMotion);
        changeBounds.setInterpolator(interpolator);
        changeBounds.addTarget(container);

        //将切换动画应用到当前的Activity的进入和返回
        getWindow().setSharedElementEnterTransition(changeBounds);
        getWindow().setSharedElementReturnTransition(changeBounds);

    }

    public void dismiss(View view) {
        finishAfterTransition();
    }
}
