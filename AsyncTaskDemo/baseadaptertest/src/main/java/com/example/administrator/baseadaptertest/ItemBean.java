package com.example.administrator.baseadaptertest;

/**
 * User: Administrator
 * Time: 2016/5/28 11:06 06
 * Annotation:
 */
public class ItemBean {

    public int ItemImageResourceId;
    public String ItemTitle;
    public String ItemContent;


    public ItemBean (int itemImageResourceId,String itemTitle,String itemContent) {
        ItemTitle = itemTitle;
        ItemImageResourceId = itemImageResourceId;
        ItemContent = itemContent;
    }
}
