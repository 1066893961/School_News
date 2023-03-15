package com.schoolnews.manage.application.http;

import java.io.Serializable;


public class Page implements Serializable{

    /**
     * plainPageNum : 1
     * pageNum : 1
     * numPerPage : 50
     * orderField :
     * orderDirection :
     * totalPage : 1
     * prePage : 1
     * nextPage : 1
     * totalCount : 6
     */

    private int plainPageNum;
    private int pageNum;        //当前页
    private int numPerPage;
    private String orderField;
    private String orderDirection;
    private int totalPage;      //总页数
    private int prePage;
    private int nextPage;
    private int totalCount;     //总条数

    public int getPlainPageNum() {
        return plainPageNum;
    }

    public void setPlainPageNum(int plainPageNum) {
        this.plainPageNum = plainPageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean needLoadMore(){
        return totalPage>pageNum;
    }
}
