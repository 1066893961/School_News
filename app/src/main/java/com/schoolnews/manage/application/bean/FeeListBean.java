package com.schoolnews.manage.application.bean;

import java.io.Serializable;
import java.util.List;


public class FeeListBean implements Serializable {


    /**
     * records : [{"id":1,"name":"活动","activityDate":1616425754505,"optBy":"ltf","type":1,"content":"吃饭","payMoney":"200"}]
     * total : 1
     * size : 10
     * current : 1
     * orders : []
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;
    private List<?> orders;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public List<?> getOrders() {
        return orders;
    }

    public void setOrders(List<?> orders) {
        this.orders = orders;
    }

    public static class RecordsBean implements Serializable {
        /**
         * id : 1
         * name : 活动
         * activityDate : 1616425754505
         * optBy : ltf
         * type : 1
         * content : 吃饭
         * payMoney : 200
         */

        private int id;
        private String name;
        private long activityDate;
        private String optBy;
        private int type;
        private String content;
        private String payMoney;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getActivityDate() {
            return activityDate;
        }

        public void setActivityDate(long activityDate) {
            this.activityDate = activityDate;
        }

        public String getOptBy() {
            return optBy;
        }

        public void setOptBy(String optBy) {
            this.optBy = optBy;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(String payMoney) {
            this.payMoney = payMoney;
        }
    }
}
