package com.example.fangli.mymvpdemo.model;

import java.util.List;

public  class ResultsBean {
        /**
         * _id : 5b1684f0421aa95aaf603f7e
         * createdAt : 2018-06-06T10:43:58.834Z
         * desc : 好用漂亮的Android 表格框架,样式丰富。
         * images : ["http://img.gank.io/09e1cd15-a35c-4897-af36-c63700954d58","http://img.gank.io/1733a2fb-182c-4591-a092-43b1f8c4b87a","http://img.gank.io/b96915e3-98d5-42c9-8b3d-6a186fde4ff0","http://img.gank.io/126201f1-34ba-4696-8763-75622c9eb7a4","http://img.gank.io/99815b0e-d00d-47c9-b0ba-f867532109b5"]
         * publishedAt : 2018-06-06T00:00:00.0Z
         * source : chrome
         * type : Android
         * url : https://github.com/huangyanbin/smartTable
         * used : true
         * who : 艾米
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

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    ", images=" + images +
                    '}';
        }
    }