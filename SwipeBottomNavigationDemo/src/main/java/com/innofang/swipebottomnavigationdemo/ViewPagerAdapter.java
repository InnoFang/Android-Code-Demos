package com.innofang.swipebottomnavigationdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/3/23 19:39
 * Description:
 */


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mFragments = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
