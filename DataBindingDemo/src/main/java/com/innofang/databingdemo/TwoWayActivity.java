package com.innofang.databingdemo;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.innofang.databingdemo.databinding.ActivityTwoWayBinding;

public class TwoWayActivity extends AppCompatActivity {

    ActivityTwoWayBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_two_way);
        FormModel formModel = new FormModel("123", "123");
        mBinding.setModel(formModel);
        formModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Toast.makeText(TwoWayActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
