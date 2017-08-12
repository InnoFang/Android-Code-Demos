package io.innofang.jsoupdemo;

/**
 * Author: Inno Fang
 * Time: 2017/8/12 11:57
 * Description:
 */


public class ArticleItem {

    public String title;
    public String moreLink;

    public ArticleItem(String title, String moreLink) {
        this.title = title;
        this.moreLink = moreLink;
    }

    @Override
    public String toString() {
        return "ArticleItem{" +
                "title='" + title + '\'' +
                ", moreLink='" + moreLink + '\'' +
                '}';
    }
}
