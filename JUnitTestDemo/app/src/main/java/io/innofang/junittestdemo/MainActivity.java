package io.innofang.junittestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.innofang.junittestdemo.utils.CalculateUtil;

public class MainActivity extends AppCompatActivity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result_text_view);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id._0:
            case R.id._1:
            case R.id._2:
            case R.id._3:
            case R.id._4:
            case R.id._5:
            case R.id._6:
            case R.id._7:
            case R.id._8:
            case R.id._9:
            case R.id.add:
            case R.id.div:
            case R.id.mul:
            case R.id.sub:
                result.setText(result.getText().toString() + ((Button) view).getText().toString());
                break;
            case R.id.equal:
                if (TextUtils.isEmpty(result.getText().toString())) {
                    Toast.makeText(this, "Equation cannot be empty!", Toast.LENGTH_SHORT).show();
                    break;
                }
                try {
                    String[] token = result.getText().toString().split("\\+|-|\\*|\\\\");
                    int a = Integer.parseInt(token[0]);
                    int b = Integer.parseInt(token[1]);
                    for (String operator : result.getText().toString().split("\\w")) {
                        if (!TextUtils.isEmpty(operator)) {
                            switch (operator.charAt(0)) {
                                case '+':
                                    result.setText(String.valueOf(CalculateUtil.add(a, b)));
                                    break;
                                case '-':
                                    result.setText(String.valueOf(CalculateUtil.div(a, b)));
                                    break;
                                case '*':
                                    result.setText(String.valueOf(CalculateUtil.mul(a, b)));
                                    break;
                                case '\\':
                                    result.setText(String.valueOf(CalculateUtil.sub(a, b)));
                                    break;
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.result_text_view:
                result.setText("");
                break;
            default:
                break;
        }
    }
}
