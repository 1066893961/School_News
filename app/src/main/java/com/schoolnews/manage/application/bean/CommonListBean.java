package com.schoolnews.manage.application.bean;

import java.io.Serializable;


public class CommonListBean implements Serializable {

    /**
     * id : 1
     * userId : 2
     * bankName : 中国银行
     * bankCardNo : 628123456
     * bankCardAddr : null
     * money : null
     * createAt : 1617357891988
     */

    private long id;
    private int type;
    private int collectionId;
    private long userId;
    private String bankName;
    private String title;
    private String content;
    private String url;
    private String bankCardNo;
    private Object bankCardAddr;
    private double money;
    private long createAt;

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public Object getBankCardAddr() {
        return bankCardAddr;
    }

    public void setBankCardAddr(Object bankCardAddr) {
        this.bankCardAddr = bankCardAddr;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}
