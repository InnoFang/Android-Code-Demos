package com.innofang.recyclerviewdemo.helper;

/**
 * Author: Inno Fang
 * Time: 2017/3/13 15:20
 * Description:
 */


public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
