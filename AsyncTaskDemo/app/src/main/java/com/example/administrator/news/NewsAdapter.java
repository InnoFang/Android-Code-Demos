package com.example.administrator.news;

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
 * Time: 2016/5/28 10:41 41
 * Annotation:
 */
public class NewsAdapter extends BaseAdapter {

    private List<NewsBean> mList;
    private LayoutInflater mInflater;
    private ImageLoader imageLoader;

    public NewsAdapter (Context context,List<NewsBean> mList) {
        this.mList = mList;
        this.mInflater = LayoutInflater.from (context);
        imageLoader = new ImageLoader ();
    }

    @Override
    public int getCount () {
        return mList.size ();
    }

    @Override
    public Object getItem (int position) {
        return mList.get (position);
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder ();
            convertView = mInflater.inflate (R.layout.item_layout,null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById (R.id.iv_icon);
            viewHolder.tvContent = (TextView) convertView.findViewById (R.id.tv_content);
            viewHolder.tvTitle = (TextView) convertView.findViewById (R.id.tv_title);
            convertView.setTag (viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag ();
        }
        viewHolder.ivIcon.setImageResource (R.mipmap.ic_launcher);
        String url = mList.get (position).newsIconUrl;
        viewHolder.ivIcon.setTag (url);
//        imageLoader.showImageByThread (viewHolder.ivIcon,url);
        imageLoader.showImageByAsyncTask (viewHolder.ivIcon, url);
        viewHolder.tvTitle.setText (mList.get (position).newsTitle);
        viewHolder.tvContent.setText (mList.get (position).newsContent);
        return convertView;
    }
    class ViewHolder{
        public TextView tvTitle;
        public TextView tvContent;
        public ImageView ivIcon;
    }
}
