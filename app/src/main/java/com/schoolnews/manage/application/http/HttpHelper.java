package com.schoolnews.manage.application.http;

import com.schoolnews.manage.application.constant.AddressContants;
import com.schoolnews.manage.application.utils.Preferences;
import com.lzy.okgo.OkGo;

import java.io.File;
import java.util.List;

public class HttpHelper {

    /**
     * @param tag 取消请求标识
     */
    public static void cancelTag(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }


    /**
     * 活动列表
     *
     * @param tag
     * @param callback
     */
    public static void getFeeList(Object tag, JsonCallback callback) {

        OkGo.get(AddressContants.API_SERVER_FEE_LIST + "?userId=" + Preferences.getUserId())
                .tag(tag)
                .execute(callback);
    }


    public static void getCommonList(Object tag, int type, JsonCallback callback) {
        OkGo.get(AddressContants.API_SERVER_ZHAUNZHANG_LIST + "?type=" + type + "&userId=" + Preferences.getUserId())
                .tag(tag)
                .execute(callback);
    }


    /**
     * 登陆
     *
     * @param tag
     * @param callback
     */
    public static void login(Object tag, String name, String psd, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_LOGIN)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("mobile", name)
                .params("password", psd)
                .execute(callback);
    }


    /**
     * 修改个人信息
     *
     * @param tag
     * @param callback
     */
    public static void modifyInfo(Object tag, String name, String age, String phone, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_MODIFY)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("name", name)
                .params("age", age)
                .params("phone", phone)
                .execute(callback);
    }

    /**
     * 修改密码
     *
     * @param tag
     * @param callback
     */
    public static void modifyPsd(Object tag, String phone, String psd, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_MODIFY_PSD)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("phone", phone)
                .params("psd", psd)
                .execute(callback);
    }

    /**
     * 发布帖子
     *
     * @param tag
     * @param callback
     */
    public static void putMsg(Object tag, String title, String content, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_PUT_MSG)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("title", title)
                .params("content", content)
                .execute(callback);
    }

    /**
     * 发布帖子
     *
     * @param tag
     * @param callback
     */
    public static void modifyMsg(Object tag, String title, String content, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_MODIFY_MSG)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("title", title)
                .params("content", content)
                .execute(callback);
    }

    /**
     * 注册
     *
     * @param tag
     * @param callback
     */
    public static void regist(Object tag, String phone, String psd, String name, String studyNo, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_REGIST)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("mobile", phone)
                .params("password", psd)
                .params("userName", name)
                .params("payPassword", studyNo)
                .execute(callback);
    }


    /**
     * 上传文件
     *
     * @param tag
     * @param files
     * @param callback
     */
    public static void uploadFile(Object tag, List<File> files, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_REGIST)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .addFileParams("file", files)
                .execute(callback);
    }

    /**
     * 收藏
     *
     * @param tag
     * @param callback
     */
    public static void collect(Object tag, int id, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_COLLECT)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("id", id)
                .execute(callback);
    }

    /**
     * 取消收藏
     *
     * @param tag
     * @param callback
     */
    public static void cacelCollect(Object tag, int id, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_CANCEL_COLLECT)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("id", id)
                .execute(callback);
    }


    /**
     * 点赞
     *
     * @param tag
     * @param callback
     */
    public static void like(Object tag, int id, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_LIKE)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("id", id)
                .execute(callback);
    }

    /**
     * 取消点赞
     *
     * @param tag
     * @param callback
     */
    public static void noLike(Object tag, int id, JsonCallback callback) {
        OkGo.post(AddressContants.API_SERVER_NO_LIKE)
                .tag(tag)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("id", id)
                .execute(callback);
    }


}
