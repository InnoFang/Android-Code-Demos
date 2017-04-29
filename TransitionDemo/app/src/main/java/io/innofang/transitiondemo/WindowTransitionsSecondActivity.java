package io.innofang.transitiondemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

public class WindowTransitionsSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_window_transitions_second);

        Transition transition = TransitionInflater
                .from(this)
//                .inflateTransition(R.transition.explode); /* 爆炸效果 */
//                .inflateTransition(R.transition.fade);  /* 淡化效果 */
                .inflateTransition(R.transition.slide); /* 滑动效果 */

        /* 退出时使用 */
        getWindow().setExitTransition(transition);
        /* 第一次进入时使用 */
        getWindow().setEnterTransition(transition);
    }
}
