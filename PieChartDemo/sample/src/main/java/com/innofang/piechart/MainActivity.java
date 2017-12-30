package com.innofang.piechart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.innofang.library.PieData;
import com.innofang.library.PieView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieView pieView = (PieView) findViewById(R.id.pie_view);
        ArrayList<PieData> datas = new ArrayList<>();
        datas.add(new PieData("Inno", 60));
        datas.add(new PieData("Inno", 30));
        datas.add(new PieData("Inno", 40));
        datas.add(new PieData("Inno", 20));
        datas.add(new PieData("Inno", 20));
        datas.add(new PieData("Inno", 10));
        pieView.setPieDataList(datas);
    }
}
