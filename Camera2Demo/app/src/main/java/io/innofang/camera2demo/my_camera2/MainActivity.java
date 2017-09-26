package io.innofang.camera2demo.my_camera2;

import android.support.v4.app.Fragment;

import io.innofang.camera2demo.R;

public class MainActivity extends FragmentContainerActivity {

    @Override
    protected Fragment createFragment() {
        return MyCamera2Fragment.newInstance();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }
}
