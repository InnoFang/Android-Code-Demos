package com.innofang.databingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.innofang.databingdemo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    Employee mEmployee = new Employee("Inno", "Fang");
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setEmployee(mEmployee);
        binding.setPresenter(new Presenter());
    }

    public class Presenter {

        public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
            mEmployee.setFirstName(text.toString());
            binding.setEmployee(mEmployee);
        }

        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
        }

        public void onClickListenerBinding (Employee employee){
            Toast.makeText(MainActivity.this, employee.getLastName(), Toast.LENGTH_SHORT).show();
        }
    }
}
