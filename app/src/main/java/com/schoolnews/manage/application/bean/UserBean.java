package com.schoolnews.manage.application.bean;

import java.io.Serializable;


public class UserBean implements Serializable {


    /**
     * id : 115395708645192
     * createTime : 1539542064000
     * email : 123456@qq.com
     * phone : 18001193283
     * realname : 赵慧电销总监
     * status : enabled
     * username : 18001193273
     * mobilePhone : 18001193283
     * qq : 1234562gh
     * wechat : ssdd
     * organization : {"id":214996757461215,"name":"秦-5-1-1","parentId":214996755963825}
     * organizationId : 214996757461215
     * roles : [{"id":114907747907715,"name":"电销总监","permissions":[],"rolePermissions":[]}]
     * alias :
     * subOrganizationIds :
     * isLogin : 1
     * resetpwdTime : 1542101764000
     */

    private long id;
    private String phone;       //手机号
    private String name;        //姓名
    private String username;    //用户名
    private long orgId;       //组织ID
    private String rolecode;    //权限id
    private String token;       //token
    private String signature;       //signature签名


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}