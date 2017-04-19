package com.innofang.ndkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mResultRecyclerView;
    private ResultAdapter mAdapter;
    private JniUtil jniUtil = new JniUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText numberA = (EditText) findViewById(R.id.number_a_edit_text);
        final EditText numberB = (EditText) findViewById(R.id.number_b_edit_text);
        mResultRecyclerView = (RecyclerView) findViewById(R.id.result_recycler_view);
        mAdapter = new ResultAdapter(this, new ArrayList<String>());
        mResultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mResultRecyclerView.setAdapter(mAdapter);
        findViewById(R.id.result_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = numberA.getText().toString();
                String b = numberB.getText().toString();
                if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(b)) {
                    resetResults(Integer.parseInt(a), Integer.parseInt(b));
                    /*Toast.makeText(MainActivity.this,
                            "" + jniUtil.add(Integer.parseInt(a), Integer.parseInt(b)),
                            Toast.LENGTH_SHORT).show();*/
                } else {
                    Toast.makeText(MainActivity.this,
                            "cannot be empty",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetResults(int a, int b) {
        ArrayList<String> result = new ArrayList<>();
        result.add(String.format(Locale.CHINA, "%d + %d = %d", a, b, jniUtil.add(a, b)));
        result.add(String.format(Locale.CHINA, "%d + %d = %d", a, b, jniUtil.sub(a, b)));
        result.add(String.format(Locale.CHINA, "%d * %d = %d", a, b, jniUtil.mul(a, b)));
        result.add(String.format(Locale.CHINA, "%d / %d = %f", a, b, jniUtil.div(a, b)));
        mAdapter.setResultList(result);
    }


}
