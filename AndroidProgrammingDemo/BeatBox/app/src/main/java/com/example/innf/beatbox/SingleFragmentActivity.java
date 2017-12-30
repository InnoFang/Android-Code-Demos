package com.example.innf.beatbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Author: Inno Fang
 * Time: 2016/7/14 17:05
 * Description: 通用的用于托管Fragment的Activity模板
 */

public abstract  class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //若获取的fragment已存在队列中，Fragment就直接返回它
        if (null == fragment) {
            //创建一个新的fragment事务，加入一个添加操作，然后提交该事务
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)//param1是容器视图资源ID，param2是新创建的crimeFragment
                    .commit();
        }
    }

}
