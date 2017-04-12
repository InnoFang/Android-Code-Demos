package com.innofang.viewsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.innofang.viewsdemo.views.Bezier3View;
import com.innofang.viewsdemo.views.BezierHeartView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setBezier3View();
        setBezierHeartView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.square_scaler_view:
                setContentView(R.layout.view_square_scaler);
                break;
            case R.id.circle_rotater_view:
                setContentView(R.layout.view_circle_rotater);
                break;
            case R.id.sketch:
                setContentView(R.layout.view_sketch);
                break;
            case R.id.bezier_2_view:
                setContentView(R.layout.view_bezier_2);
                break;
            case R.id.bezier_3_view:
                setBezier3View();
                break;
            case R.id.bezier_heart_view:
                setBezierHeartView();
                break;
            default:
                break;
        }
        return true;
    }

    private void setBezierHeartView() {
        setContentView(R.layout.view_bezier_heart);
        final BezierHeartView bezierHeartView = (BezierHeartView) findViewById(R.id.bezier_heart_view);
        ((CheckBox)findViewById(R.id.show_coordinate_system)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bezierHeartView.setShowCoordinateSystem(isChecked);
            }
        });
        ((CheckBox) findViewById(R.id.show_auxiliary_line)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bezierHeartView.setShowAuxiliaryLine(isChecked);
            }
        });
       /* findViewById(R.id.show_again_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    private void setBezier3View() {
        setContentView(R.layout.view_bezier_3);
        final Bezier3View bezier3View = (Bezier3View) findViewById(R.id.bezier_3_view);
        ((RadioButton)findViewById(R.id.control_1)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bezier3View.setMode(isChecked);
            }
        });
       /* ((RadioButton) findViewById(R.id.control_2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bezier3View.setMode(false);
            }
        });*/
    }
}
