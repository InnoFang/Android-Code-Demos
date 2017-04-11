package com.innofang.databingdemo;

import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.innofang.databingdemo.databinding.ActivityAnimationBinding;

public class AnimationActivity extends AppCompatActivity {

    ActivityAnimationBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_animation);
        mBinding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                ViewGroup view = (ViewGroup) binding.getRoot();
                TransitionManager.beginDelayedTransition(view);
                return true;
            }
        });
        mBinding.setPresenter(new Presenter());
    }

    public class Presenter {
        public void onCheckedChanged(View view, boolean isChecked) {
            mBinding.setShowImage(isChecked);

        }
    }
}
