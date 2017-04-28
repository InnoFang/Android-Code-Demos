package io.innofang.retrofitdemo;

import java.util.List;

/**
 * Author: Inno Fang
 * Time: 2017/4/28 10:32
 * Description:
 */


public class Gank {

    @Override
    public String toString() {
        return "Gank{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    /**
     * error : false
     * results : [{"_id":"59007c87421aa954511ebf15","createdAt":"2017-04-26T18:55:03.485Z","desc":"深入理解ServiceManager","publishedAt":"2017-04-27T13:16:46.955Z","source":"web","type":"Android","url":"https://pqpo.me/2017/04/26/learn-servicemanager/","used":true,"who":"Linmin Qiu"},{"_id":"59008fa3421aa9544825f919","createdAt":"2017-04-26T20:16:35.816Z","desc":"浅谈RxJava中的线程管理","publishedAt":"2017-04-27T13:16:46.955Z","source":"web","type":"Android","url":"http://www.zjutkz.net/2017/04/26/%E6%B5%85%E8%B0%88RxJava%E4%B8%AD%E7%9A%84%E7%BA%BF%E7%A8%8B%E7%AE%A1%E7%90%86/","used":true,"who":null},{"_id":"5900daa7421aa9544825f91b","createdAt":"2017-04-27T01:36:39.650Z","desc":"Android Development Useful Tools","publishedAt":"2017-04-27T13:16:46.955Z","source":"web","type":"Android","url":"https://blog.mindorks.com/android-development-useful-tools-fd73283e82e3","used":true,"who":"AMIT SHEKHAR"},{"_id":"59013172421aa9544ed889f1","createdAt":"2017-04-27T07:46:58.470Z","desc":"图片阴影新玩法，让阴影不再单调","images":["http://img.gank.io/a5ace6ba-1349-492e-ab5e-edbfbab4d34a"],"publishedAt":"2017-04-27T13:16:46.955Z","source":"web","type":"Android","url":"https://github.com/DingMouRen/PaletteImageView","used":true,"who":null},{"_id":"59017cb9421aa9544ed889f8","createdAt":"2017-04-27T13:08:09.279Z","desc":"又一个漂亮的日历组件","images":["http://img.gank.io/1af5b311-d09e-4b21-873a-d15be6fc879d"],"publishedAt":"2017-04-27T13:16:46.955Z","source":"chrome","type":"Android","url":"https://github.com/mahendramahi/CalendarView","used":true,"who":"代码家"},{"_id":"58ff6fbc421aa9544825f90e","createdAt":"2017-04-25T23:48:12.455Z","desc":"Amazing Open Source Android Apps","publishedAt":"2017-04-26T11:30:43.767Z","source":"web","type":"Android","url":"https://blog.mindorks.com/android-amazing-open-source-apps-e44f520593cc","used":true,"who":"AMIT SHEKHAR"},{"_id":"590012a0421aa9544ed889e9","createdAt":"2017-04-26T11:23:12.770Z","desc":"Android 浮动 Debug 工具箱，直接在浮动窗口执行 Android 测试功能。","images":["http://img.gank.io/7417e149-112a-466c-8124-b73c161ee866"],"publishedAt":"2017-04-26T11:30:43.767Z","source":"chrome","type":"Android","url":"https://github.com/hulab/debugkit","used":true,"who":"代码家"},{"_id":"58fddc9a421aa954511ebf00","createdAt":"2017-04-24T19:08:10.208Z","desc":"横幅广告轮播控件","images":["http://img.gank.io/ffa66550-643a-429c-8e4c-53b51827a637"],"publishedAt":"2017-04-25T13:11:39.357Z","source":"web","type":"Android","url":"https://github.com/czy1121/bannerview","used":true,"who":"ezy"},{"_id":"58fe0496421aa954511ebf01","createdAt":"2017-04-24T21:58:46.939Z","desc":"Android虚拟机检测库，意在未授权的情况下禁止在虚拟机App上非法运行","images":["http://img.gank.io/88159d8b-e69f-4f79-8ef4-72c3c9f2289e"],"publishedAt":"2017-04-25T13:11:39.357Z","source":"web","type":"Android","url":"https://github.com/bunnyblue/AntiVM","used":true,"who":"ZQiang94"},{"_id":"58fea251421aa9544ed889da","createdAt":"2017-04-25T09:11:45.13Z","desc":"Java 图片本地压缩框架，节省流量必备之选。","publishedAt":"2017-04-25T13:11:39.357Z","source":"chrome","type":"Android","url":"https://github.com/Sunzxyong/Tiny","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 59007c87421aa954511ebf15
         * createdAt : 2017-04-26T18:55:03.485Z
         * desc : 深入理解ServiceManager
         * publishedAt : 2017-04-27T13:16:46.955Z
         * source : web
         * type : Android
         * url : https://pqpo.me/2017/04/26/learn-servicemanager/
         * used : true
         * who : Linmin Qiu
         * images : ["http://img.gank.io/a5ace6ba-1349-492e-ab5e-edbfbab4d34a"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
