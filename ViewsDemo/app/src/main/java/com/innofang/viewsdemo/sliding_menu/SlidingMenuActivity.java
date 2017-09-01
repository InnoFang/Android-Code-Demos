package com.innofang.viewsdemo.sliding_menu;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.innofang.viewsdemo.R;

import java.util.ArrayList;
import java.util.List;

public class SlidingMenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        init();
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            mData.add(getString(R.string.top));
        }
        SlidingMenuAdapter adapter = new SlidingMenuAdapter(mData, this);
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder instanceof SlidingMenuViewHolder) {
                    final SlidingMenuViewHolder vh = (SlidingMenuViewHolder) viewHolder;
                    vh.setOnMenuClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            vh.changeMenuMode();
                            if (vh.isTop())
                                toast(R.string.cancel_top);
                            else
                                toast(R.string.top);
                        }
                    });

                }
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                toast(R.string.long_click);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    public void toast(@StringRes int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }
}