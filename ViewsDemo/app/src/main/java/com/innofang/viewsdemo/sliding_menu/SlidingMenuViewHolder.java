package com.innofang.viewsdemo.sliding_menu;

import android.graphics.Color;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.innofang.viewsdemo.R;

/**
 * Author: Inno Fang
 * Time: 2017/9/1 21:39
 * Description:
 */


public class SlidingMenuViewHolder extends RecyclerView.ViewHolder {

    TextView menuText;
    ImageView imageView;
    boolean top;

    public SlidingMenuViewHolder(View itemView) {
        super(itemView);
        menuText = (TextView) itemView.findViewById(R.id.menuText);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);
    }

    public void bindHolder(final SlidingMenuAdapter adapter, String text) {
        imageView.setBackgroundColor(Color.GREEN);
        menuText.setText(text);
        menuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.onCloseMenu();
                if (menuText.getText().toString().equals("置顶")) {
                    menuText.setText("取消置顶");
                    imageView.setBackgroundColor(Color.YELLOW);
                    top = true;
                } else {
                    menuText.setText("置顶");
                    imageView.setBackgroundColor(Color.GREEN);
                    top = false;
                }
            }
        });
    }

    public boolean isTop() {
        return top;
    }

    public void setMenuText(String text) {
        menuText.setText(text);
    }

    public void setMenuText(@StringRes int text) {
        menuText.setText(text);
    }

    public String getMenuText() {
        return menuText.getText().toString();
    }

}
