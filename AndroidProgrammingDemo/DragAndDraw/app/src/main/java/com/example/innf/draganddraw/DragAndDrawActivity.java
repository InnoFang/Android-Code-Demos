package com.example.innf.draganddraw;

import android.support.v4.app.Fragment;

public class DragAndDrawActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return DragAndDrawFragment.newInstance();
    }
}
