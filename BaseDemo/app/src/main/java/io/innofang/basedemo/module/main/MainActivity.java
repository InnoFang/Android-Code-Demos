package io.innofang.basedemo.module.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.innofang.basedemo.R;
import io.innofang.basedemo.module.FragmentContainerActivity;

public class MainActivity extends FragmentContainerActivity {

    @Override
    protected Fragment createFragment() {
        return MainFragment.newInstance();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
