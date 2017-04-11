package com.innofang.databingdemo;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Author: Inno Fang
 * Time: 2017/2/19 12:17
 * Description:
 * 自定义BindingAdapter
 */

public class DemoBindingAdapter {

    @BindingAdapter({"imageUrl", "placeholder"})
    public static void loadImageFromUrl(ImageView view, String url, Drawable drawable) {
        Glide.with(view.getContext())
                .load(url)
                .placeholder(drawable)
                .into(view);
    }

}
