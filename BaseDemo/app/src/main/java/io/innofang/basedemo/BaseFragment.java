package io.innofang.basedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Author: Inno Fang
 * Description: Base Operation of Fragment
 */


public class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    public static Fragment newInstance() {
        return new BaseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("Hello World");
        return textView;
    }
}
