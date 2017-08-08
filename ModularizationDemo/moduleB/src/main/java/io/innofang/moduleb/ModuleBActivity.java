package io.innofang.moduleb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.innofang.library.base.BaseSimpleAdapter;
import io.innofang.library.base.BaseSimpleViewHolder;
import io.innofang.library.router.FunctionBus;
import io.innofang.library.router.module_b.FunctionModuleB;

public class ModuleBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_b);

        int num = 0;

        /*Uri data = getIntent().getData();
        if (null != data) {
            num = Integer.parseInt(data.getQueryParameter(RouterModuleB.ROUTER_PARAM_NUM));
        }*/

        FunctionModuleB function = FunctionBus.getFunction(FunctionModuleB.class);
        if (null != function) {
            num = function.getData();
        }

        // prepare data list
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(i + "");
        }


        // initialize recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.number_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BaseSimpleAdapter<String>(this, android.R.layout.simple_list_item_1, list) {

            @Override
            protected void bindViewHolder(BaseSimpleViewHolder viewHolder, String s, int position) {
                viewHolder.setText(android.R.id.text1, s);
            }
        });
    }
}
