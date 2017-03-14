package com.innofang.recyclerviewdemo.grid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.innofang.recyclerviewdemo.R;
import com.innofang.recyclerviewdemo.adapter.Adapter;
import com.innofang.recyclerviewdemo.helper.ItemTouchHelperCallBack;
import com.innofang.recyclerviewdemo.helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity implements OnStartDragListener {

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add("Hello, I am " + i);
        }
        Adapter adapter = new Adapter(this, this);
        adapter.addAll(list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.grid_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallBack(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
