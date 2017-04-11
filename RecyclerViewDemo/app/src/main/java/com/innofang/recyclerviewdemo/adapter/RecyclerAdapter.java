package com.innofang.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.innofang.recyclerviewdemo.R;
import com.innofang.recyclerviewdemo.mixture.holder.TypeOneViewHolder;
import com.innofang.recyclerviewdemo.mixture.holder.TypeThreeViewHolder;
import com.innofang.recyclerviewdemo.mixture.holder.TypeTwoViewHolder;
import com.innofang.recyclerviewdemo.mixture.model.DataModelOne;
import com.innofang.recyclerviewdemo.mixture.model.DataModelThree;
import com.innofang.recyclerviewdemo.mixture.model.DataModelTwo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Inno Fang
 * Time: 2017/3/12 13:15
 * Description:
 */


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;

    private LayoutInflater mLayoutInflater;

    public RecyclerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    private List<DataModelOne> list1;
    private List<DataModelTwo> list2;
    private List<DataModelThree> list3;
    private List<Integer> types = new ArrayList<>();
    private Map<Integer, Integer> mPositions = new HashMap<>();

    public void addList(List<DataModelOne> list1, List<DataModelTwo> list2, List<DataModelThree> list3) {
        addListByType(TYPE_ONE, list1);
        addListByType(TYPE_TWO, list2);
        addListByType(TYPE_THREE, list3);
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
    }

    private void addListByType(int type, List list) {
        mPositions.put(type, types.size());
        for (int i = 0; i < list.size(); i++) {
            types.add(type);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ONE:
                return new TypeOneViewHolder(
                        mLayoutInflater.inflate(R.layout.item_type_one, parent, false));
            case TYPE_TWO:
                return new TypeTwoViewHolder(
                        mLayoutInflater.inflate(R.layout.item_type_two, parent, false));
            case TYPE_THREE:
                return new TypeThreeViewHolder(
                        mLayoutInflater.inflate(R.layout.item_type_three, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        int realPosition = position - mPositions.get(viewType);
        switch (viewType) {
            case TYPE_ONE:
                ((TypeOneViewHolder) holder).bindHolder(list1.get(realPosition));
                break;
            case TYPE_TWO:
                ((TypeTwoViewHolder) holder).bindHolder(list2.get(realPosition));
                break;
            case TYPE_THREE:
                ((TypeThreeViewHolder) holder).bindHolder(list3.get(realPosition));
                break;


        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }
}
