package com.innofang.databingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.innofang.databingdemo.databinding.ActivityExpressionBinding;


public class ExpressionActivity extends AppCompatActivity {

    ActivityExpressionBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Employee employee = new Employee("Inno", "Fang", false);
        employee.setAvatar("https://avatars0.githubusercontent.com/u/15724026?v=3&s=460");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_expression);
        mBinding.setEmployee(employee);
    }
}
