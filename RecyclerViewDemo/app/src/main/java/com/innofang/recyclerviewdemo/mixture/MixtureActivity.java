package com.innofang.recyclerviewdemo.mixture;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.innofang.recyclerviewdemo.R;
import com.innofang.recyclerviewdemo.adapter.RecyclerAdapter;
import com.innofang.recyclerviewdemo.mixture.model.DataModelOne;
import com.innofang.recyclerviewdemo.mixture.model.DataModelThree;
import com.innofang.recyclerviewdemo.mixture.model.DataModelTwo;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.type;

public class MixtureActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;

    int colors[] = {
            android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixture);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mRecyclerView.getAdapter().getItemViewType(position);
                if (type == mRecyclerAdapter.TYPE_THREE) {
                    return gridLayoutManager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerAdapter = new RecyclerAdapter(this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                GridLayoutManager.LayoutParams layoutParams =
                        (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize = layoutParams.getSpanSize();
                int spanIndex = layoutParams.getSpanSize();
                outRect.top = 20;
                if (spanSize != gridLayoutManager.getSpanCount()) {
                    if (spanIndex == 1) {
                        outRect.left = 10;
                    } else {
                        outRect.right = 10;
                    }
                }
            }
        });
        initData();
    }

    private void initData() {

        List<DataModelOne> list1 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            DataModelOne data = new DataModelOne();
            data.avatorColor = colors[0];
            data.name = "name : " + i;
            list1.add(data);
        }

        List<DataModelTwo> list2 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {

            DataModelTwo data = new DataModelTwo();
            data.avatorColor = colors[1];
            data.name = "name : " + i;
            data.content = "content : " + i;
            list2.add(data);
        }

        List<DataModelThree> list3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataModelThree data = new DataModelThree();
            data.avatorColor = colors[2];
            data.name = "name : " + i;
            data.content = "content : " + i;
            data.contentColor = colors[(type + 1) % 3];
            list3.add(data);
        }

        mRecyclerAdapter.addList(list1, list2, list3);
        mRecyclerAdapter.notifyDataSetChanged();
    }

}
