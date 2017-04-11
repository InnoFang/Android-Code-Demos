package com.innofang.swipebottomnavigationdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MenuItem mMenuItem;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_android:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.item_bubble:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.item_music:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.item_game:
                        mViewPager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (null != mMenuItem) {
                    mMenuItem.setChecked(false);
                } else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                mMenuItem = mBottomNavigationView.getMenu().getItem(position);
                mMenuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        List<Fragment> list = new ArrayList<>();
        for (String s : BottomNavigationViewHelper.getNavigationItemTitles(mBottomNavigationView)) {
            list.add(ContentFragment.newInstance(s));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        mViewPager.setAdapter(adapter);
    }
}
