package com.innofang.recyclerviewdemo.mixture.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.innofang.recyclerviewdemo.R;
import com.innofang.recyclerviewdemo.mixture.model.DataModelTwo;

/**
 * Author: Inno Fang
 * Time: 2017/3/13 14:04
 * Description:
 */


public class TypeTwoViewHolder extends TypeAbstractViewHolder<DataModelTwo> {
    public ImageView avatar;
    public TextView name;
    public TextView content;
    public TypeTwoViewHolder(View itemView) {
        super(itemView);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
        itemView.setBackgroundColor(Color.GREEN);
    }
    
    @Override
    public void bindHolder (DataModelTwo model) {
        avatar.setBackgroundResource(model.avatorColor);
        name.setText(model.name);
        content.setText(model.content);
    }
}
