package io.innofang.basedemo.module;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.innofang.basedemo.R;
import io.innofang.basedemo.base.BaseFragment;
import io.innofang.basedemo.base.BaseSimpleAdapter;
import io.innofang.basedemo.base.BaseSimpleViewHolder;
import io.innofang.basedemo.utils.Toasts;

/**
 * Author: Inno Fang
 * Description:
 */


public class MainFragment extends BaseFragment {

    public static MainFragment newInstance() {

        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void createView(final View view, Bundle savedInstanceState) {
        TextView text = (TextView) find(R.id.text_view);
        Toasts.showToast(text.getText().toString());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("This is " + i);
        }
        RecyclerView rv = (RecyclerView) find(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new BaseSimpleAdapter<String>(getActivity(), android.R.layout.activity_list_item, list) {
            @Override
            protected void bindViewHolder(BaseSimpleViewHolder viewHolder, String s, int position) {
                viewHolder.setImageResource(android.R.id.icon, R.mipmap.ic_launcher_round);
                viewHolder.setText(android.R.id.text1, s);
                viewHolder.setBackgroundColor(android.R.id.text1, Color.GRAY);
                viewHolder.setTextColor(android.R.id.text1, Color.RED);
                viewHolder.setOnClickListener(android.R.id.text1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toasts.showShort(((TextView)v).getText().toString());
                    }
                });

                Random random = new Random();
                if (random.nextInt() % 2 == 0) {
                    viewHolder.setVisible(android.R.id.icon, true);
                } else {
                    viewHolder.setVisible(android.R.id.icon, false);
                }
            }
        });
    }

}
