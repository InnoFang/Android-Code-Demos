package com.innofang.recyclerviewdemo.mixture.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.innofang.recyclerviewdemo.R;
import com.innofang.recyclerviewdemo.mixture.model.DataModelOne;

/**
 * Author: Inno Fang
 * Time: 2017/3/13 14:04
 * Description:
 */


public class TypeOneViewHolder extends TypeAbstractViewHolder<DataModelOne> {
    public ImageView avatar;
    public TextView name;
    public TypeOneViewHolder(View itemView) {
        super(itemView);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        name = (TextView) itemView.findViewById(R.id.name);
        itemView.setBackgroundColor(Color.BLACK);
    }

    @Override
    public void bindHolder (DataModelOne model) {
        avatar.setBackgroundResource(model.avatorColor);
        name.setText(model.name);
    }
}
