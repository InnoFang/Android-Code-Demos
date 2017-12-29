package com.example.administrator.baseadaptertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        List<ItemBean> itemBeansList = new ArrayList<> ();
        for(int i = 0; i < 20; i++)
        {
            itemBeansList.add (new ItemBean
                    (R.mipmap.ic_launcher,"I'm title" + i, "I'm content"+i));
        }
        ListView listView = (ListView) findViewById (R.id.lv_main);
        listView.setAdapter (new MyAdapter (this,itemBeansList));
    }
}
