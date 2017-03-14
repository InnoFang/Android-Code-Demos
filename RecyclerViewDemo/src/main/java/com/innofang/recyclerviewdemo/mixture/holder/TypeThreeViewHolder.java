package com.innofang.recyclerviewdemo.mixture.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.innofang.recyclerviewdemo.R;
import com.innofang.recyclerviewdemo.mixture.model.DataModelThree;

/**
 * Author: Inno Fang
 * Time: 2017/3/13 14:04
 * Description:
 */


public class TypeThreeViewHolder extends TypeAbstractViewHolder<DataModelThree> {
    public ImageView avatar;
    public TextView name;
    public TextView content;
    public ImageView contentImage;

    public TypeThreeViewHolder(View itemView) {
        super(itemView);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        contentImage = (ImageView) itemView.findViewById(R.id.content_image);
        name = (TextView) itemView.findViewById(R.id.name);
        content = (TextView) itemView.findViewById(R.id.content);
        itemView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void bindHolder(DataModelThree model) {
        avatar.setBackgroundResource(model.avatorColor);
        name.setText(model.name);
        content.setText(model.content);
        contentImage.setBackgroundResource(model.contentColor);
    }
}
