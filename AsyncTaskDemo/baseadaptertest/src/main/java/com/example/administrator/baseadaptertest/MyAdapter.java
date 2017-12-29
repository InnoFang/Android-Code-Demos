package com.example.administrator.baseadaptertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * User: Administrator
 * Time: 2016/5/28 11:12 12
 * Annotation:
 */
public class MyAdapter extends BaseAdapter {

    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    public MyAdapter (Context context, List<ItemBean> list) {
        mList = list;
        mInflater = LayoutInflater.from (context);
    }

    /**
     *
     * @return 返回ListView的数据量
     */
    @Override
    public int getCount () {
        return mList.size ();
    }

    /**
     *
     * @param position 指定索引的数据项
     * @return 取出对应的数据项
     */
    @Override
    public Object getItem (int position) {
        return mList.get (position);
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return  返回每一项的显示内容
     */
    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        ////////////////////////Stupid Way////////////////////////
        //没有做任何优化处理，每次都创建新的View，设置控件，效率低
//        View view = mInflater.inflate (R.layout.item,null);
//        ImageView imageView = (ImageView) view.findViewById (R.id.iv_image);
//        TextView title = (TextView) view.findViewById (R.id.tv_title);
//        TextView content = (TextView) view.findViewById (R.id.tv_content);
//        ItemBean bean = mList.get (position);
//        imageView.setImageResource (bean.ItemImageResourceId);
//        title.setText (bean.ItemTitle);
//        content.setText (bean.ItemContent);
//        return view;
        ////////////////////////Stupid Way////////////////////////

        ////////////////////////Normal Way////////////////////////
        //利用了ListView的缓存特性，如果没有缓存才创建的View，但是findViewById仍然会浪费大量时间
//        if (convertView == null) {//为空：View未被实例化，缓存池中无缓存
//            convertView = mInflater.inflate (R.layout.item,null);
//        }
//        View view = mInflater.inflate (R.layout.item,null);
//        ImageView imageView = (ImageView) view.findViewById (R.id.iv_image);
//        TextView title = (TextView) view.findViewById (R.id.tv_title);
//        TextView content = (TextView) view.findViewById (R.id.tv_content);
//        ItemBean bean = mList.get (position);
//        imageView.setImageResource (bean.ItemImageResourceId);
//        title.setText (bean.ItemTitle);
//        content.setText (bean.ItemContent);
//        return convertView;
        ////////////////////////Normal Way////////////////////////

        ////////////////////////Best Way////////////////////////
        //不仅利用了ListView的缓存，更通过ViewHolder类来实现显示数据的视图的缓存
        //避免多次通过findViewById寻找控件
        ViewHolder viewHolder;
        if (convertView == null ){
            viewHolder = new ViewHolder ();
            convertView = mInflater.inflate (R.layout.item,null);
            viewHolder.imageView = (ImageView) convertView.findViewById (R.id.iv_image);
            viewHolder.title = (TextView) convertView.findViewById (R.id.tv_title);
            viewHolder.content = (TextView) convertView.findViewById (R.id.tv_content);
            convertView.setTag (viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag ();
        }
        ItemBean bean = mList.get (position);
        viewHolder.imageView.setImageResource (bean.ItemImageResourceId);
        viewHolder.title.setText (bean.ItemTitle);
        viewHolder.content.setText (bean.ItemContent);
        return convertView;
        ////////////////////////Best Way////////////////////////
    }

    class ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }
}
