package com.innofang.databingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.innofang.databingdemo.databinding.ActivityLambdaBinding;

public class LambdaActivity extends AppCompatActivity {

    ActivityLambdaBinding mBinding;

    public class Presenter {
        public void onEmployeeClick(Employee employee) {
            Toast.makeText(LambdaActivity.this, "onEmployeeClick", Toast.LENGTH_SHORT).show();
        }

        public void onEmployeeLongClick(Employee employee) {
            Toast.makeText(LambdaActivity.this, "onEmployeeLongClick", Toast.LENGTH_SHORT).show();
        }
        public void onFocusChange(Employee employee) {
            Toast.makeText(LambdaActivity.this, "onFocusChange", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_lambda);
        mBinding.setEmployee(new Employee("Inno", "Fang"));
        mBinding.setPresenter(new Presenter());
    }
}
