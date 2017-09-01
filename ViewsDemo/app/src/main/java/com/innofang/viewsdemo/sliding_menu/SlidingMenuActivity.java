package com.innofang.viewsdemo.sliding_menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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
            mData.add("置顶");
        }
        SlidingMenuAdapter adapter = new SlidingMenuAdapter(mData, this);
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder instanceof SlidingMenuViewHolder) {
                   SlidingMenuViewHolder vh = (SlidingMenuViewHolder) viewHolder;
                   if (vh.isTop()) {
                       Toast.makeText(SlidingMenuActivity.this, "取消置顶", Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(SlidingMenuActivity.this, "置顶", Toast.LENGTH_SHORT).show();
                   }
                }
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {

            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
