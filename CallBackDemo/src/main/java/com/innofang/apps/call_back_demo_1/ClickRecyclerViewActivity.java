package com.innofang.apps.call_back_demo_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.innofang.apps.R;

import java.util.ArrayList;
import java.util.List;

public class ClickRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_recycler_view);

        List<String> strings = new ArrayList<>();
        /*实例化Adapter*/
        ClickAdapter adapter = new ClickAdapter(this, strings);
        /*数据初始化*/
        for (int i = 1; i <= 100; i++) {
            strings.add("Hello, I am " + i);
        }
        /*初始化RecyclerView*/
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        /*为RecyclerView的item设置点击事件*/
        adapter.setOnClickItemListener(new ClickAdapter.OnClickItemListener() {
            @Override
            public void onClick(String text) {
                Toast.makeText(ClickRecyclerViewActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
