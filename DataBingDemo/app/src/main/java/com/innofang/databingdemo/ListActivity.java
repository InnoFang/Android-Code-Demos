package com.innofang.databingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.innofang.databingdemo.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ListActivity extends AppCompatActivity {

    ActivityListBinding mBinding;
    EmployeeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        mBinding.setPresenter(new Presenter());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EmployeeAdapter(this);
        mBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.setListener(new EmployeeAdapter.OnItemClickListener() {
            @Override
            public void onEmployeeClick(Employee employee) {
                Toast.makeText(ListActivity.this, employee.getFirstName(), Toast.LENGTH_SHORT).show();
            }
        });
        List<Employee> demoList = new ArrayList<>();
        demoList.add(new Employee("Inno", "Fang1", false));
        demoList.add(new Employee("Inno", "Fang2", true));
        demoList.add(new Employee("Inno", "Fang3", false));
        demoList.add(new Employee("Inno", "Fang4", true));
        demoList.add(new Employee("Inno", "Fang5", false));
        demoList.add(new Employee("Inno", "Fang6", true));
        demoList.add(new Employee("Inno", "Fang7", false));
        demoList.add(new Employee("Inno", "Fang8", true));
        demoList.add(new Employee("Inno", "Fang9", false));
        mAdapter.addAll(demoList);
    }

    public class Presenter {
        Random mRandom = new Random(System.currentTimeMillis());
        public void onClickAddItem(View view) {
            boolean isFired = mRandom.nextInt(20) % 2 == 0;
            mAdapter.add(new Employee("haha", "1", !isFired));
        }

        public void onClickDeleteItem(View view) {
            mAdapter.remove();
        }
    }
}
