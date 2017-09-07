package io.innofang.mvvmdemo.sign_in;

import android.support.v4.app.Fragment;

import io.innofang.mvvmdemo.R;
import io.innofang.mvvmdemo.SingleFragmentActivity;

public class SignInActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SignInFragment.newInstance();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }
}
