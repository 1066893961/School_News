package com.schoolnews.manage.application.constant;

/**
 * 网址相关常量
 */
public final class AddressContants {


    private static String SERVER_ADDR_HOME = "";

    static {
        if (GlobalConfigContants.intEnviSwitch == 0) {
            SERVER_ADDR_HOME = "http://172.26.57.105:8086";//测试环境使用
        }
    }

    public static final String API_SERVER_LOGIN = SERVER_ADDR_HOME + "/user/login";
    public static final String API_SERVER_MODIFY = SERVER_ADDR_HOME + "/user/register";
    public static final String API_SERVER_MODIFY_PSD = SERVER_ADDR_HOME + "/user/register";
    public static final String API_SERVER_PUT_MSG = SERVER_ADDR_HOME + "/user/register";
    public static final String API_SERVER_MODIFY_MSG = SERVER_ADDR_HOME + "/user/register";
    public static final String API_SERVER_REGIST = SERVER_ADDR_HOME + "/user/register";
    public static final String API_SERVER_FEE_LIST = SERVER_ADDR_HOME + "/news/collectionList";
    public static final String API_SERVER_COMMON_LIST = SERVER_ADDR_HOME + "/news/commentList";  //文件上传
    public static final String API_SERVER_ADD_COMMON_LIST = SERVER_ADDR_HOME + "/news/addComment";  //文件上传
    public static final String API_SERVER_COLLECT = SERVER_ADDR_HOME + "/news/addCollection";  //文件上传
    public static final String API_SERVER_CANCEL_COLLECT = SERVER_ADDR_HOME + "/news/deleteCollection";  //文件上传
    public static final String API_SERVER_LIKE = SERVER_ADDR_HOME + "/admin/file/upload";  //文件上传
    public static final String API_SERVER_NO_LIKE = SERVER_ADDR_HOME + "/admin/file/upload";  //文件上传


    public static final String API_SERVER_ADD_BANK_CARD = SERVER_ADDR_HOME + "/bankCard/add";
    public static final String API_SERVER_BANK_LIST = SERVER_ADDR_HOME + "/bankCard/list";
    public static final String API_SERVER_DELETE_BANK_CARD = SERVER_ADDR_HOME + "/bankCard/del";
    public static final String API_SERVER_ZHAUNZHANG_LIST = SERVER_ADDR_HOME + "/news/list";
    public static final String API_SERVER_ZHAUNZHANG = SERVER_ADDR_HOME + "/transferFlow/add";
    public static final String API_SERVER_SEARCH = SERVER_ADDR_HOME + "/news/key";


}
