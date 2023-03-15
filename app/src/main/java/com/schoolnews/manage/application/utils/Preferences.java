package com.schoolnews.manage.application.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.schoolnews.manage.application.JlhxApplication;
import com.schoolnews.manage.application.bean.UserBean;
import com.schoolnews.manage.application.constant.GlobalKeyContans;

import java.util.Calendar;

/**
 * 全局常量数据吧
 */
public class Preferences {
    private static final String KEY_USER_ID = "kd_user_id"; //用户id
    private static final String KEY_USERNAME = "kd_username"; //用户姓名
    private static final String KEY_USER_TOKEN = "kd_token"; //用户token
    private static final String KEY_USER_ROLE = "kd_role"; //用户role
    private static final String KEY_USER_ORGID = "kd_org"; //用户orgId
    private static final String KEY_RANDOM_IMEI = "random_imei";//随机生成imei DeviceId
    private static final String KEY_COUNT_DOWN_TIME = "kd_countdowntime";//保存当前倒计时失效时间
    private static final String KEY_USER_NO = "user_no";
    private static final String KEY_USER_MOBILE = "mobile";
    private static final String KEY_USER_CLASS_NAME = "class_name";

    private static final String SP_NAME = "kd_huiju";

    private static long userId;     //用户id
    private static String username; //用户名
    private static String userClassName; //用户名
    private static String userPhone; //用户名
    private static String userStudyNo; //用户名
    private static String userToken; //用户token
    private static String roleCode; //用户role
    private static long orgId;    //用户组织机构id
    public static boolean ISBACKGROUND = true;  //是否后台标识

    /**
     * 保存随机imei
     *
     * @param imei
     */
    public static void saveRandomImei(String imei) {
        saveString(KEY_RANDOM_IMEI, imei);
    }

    public static void saveToken(String token) {
        saveString(KEY_USER_TOKEN, token);
    }

    public static void saveUserPhone(String phone) {
        saveString(KEY_USER_MOBILE, phone);
    }

    public static void saveUserClassName(String className) {
        saveString(KEY_USER_CLASS_NAME, className);
    }

    public static void saveUserStudyNo(String num) {
        saveString(KEY_USER_NO, num);
    }

    public static void saveUserName(String userName) {
        saveString(KEY_USERNAME, userName);
    }

    public static void saveUserId(long userId) {
        saveLong(KEY_USER_ID, userId);
    }

    /**
     * 获取随机imei
     *
     * @return
     */
    public static String getRandomImei() {
        return getString(KEY_RANDOM_IMEI);
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public static long getUserId() {
        if (userId == 0) {
            userId = getSharedPreferences().getLong(KEY_USER_ID, 0);
        }
        return userId;
    }


    public static String getKeyUserMobile() {
        if (TextUtils.isEmpty(userPhone)) {
            userPhone = getString(KEY_USER_MOBILE);
        }
        return userPhone;
    }

    public static String getKeyUserNo() {
        if (TextUtils.isEmpty(userStudyNo)) {
            userStudyNo = getString(KEY_USER_NO);
        }
        return userStudyNo;
    }

    public static String getKeyUserClassName() {
        if (TextUtils.isEmpty(userClassName)) {
            userClassName = getString(KEY_USER_CLASS_NAME);
        }
        return userClassName;
    }
    /**
     * 获取username
     *
     * @return
     */
    public static String getUsername() {
        if (TextUtils.isEmpty(username)) {
            username = getString(KEY_USERNAME);
        }
        return username;
    }

    public static String getUserToken() {
        if (TextUtils.isEmpty(userToken)) {
            userToken = getString(KEY_USER_TOKEN);
        }
        return userToken;
    }

    public static String getRoleCode() {
        if (TextUtils.isEmpty(roleCode)) {
            roleCode = getString(KEY_USER_ROLE);
        }
        return roleCode;
    }

    public static long getOrgId() {
        if (orgId == 0) {
            orgId = getLong(KEY_USER_ORGID);
        }
        return orgId;
    }

    /**
     * 保存用户信息（登录成功之后调用）
     *
     * @param bean
     */
    public static void saveUserInfo(@NonNull UserBean bean) {
        Preferences.userId = bean.getId();
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(KEY_USER_ID, bean.getId());
        userId = bean.getId();
        editor.putString(KEY_USERNAME, bean.getUsername());
        username = bean.getUsername();
        editor.putString(KEY_USER_TOKEN, bean.getToken());
        userToken = bean.getToken();
        editor.putString(KEY_USER_ROLE, bean.getRolecode());
        roleCode = bean.getRolecode();
        editor.putLong(KEY_USER_ORGID, bean.getOrgId());
        orgId = bean.getOrgId();
        editor.commit();
    }


    /**
     * 清除用户信息（退出成功调用）
     */
    public static void cleanUserInfo() {
        saveUserInfo(new UserBean());
    }

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return getUserId() != 0/* && !TextUtils.isEmpty(getUserToken()) && !TextUtils.isEmpty(getRoleCode())*/;
    }

    /**
     * 获取短信验证码的限制时间
     *
     * @return
     */
    public static int getCountDownTime(String username) {
        long aLong = getSharedPreferences().getLong(KEY_COUNT_DOWN_TIME + username, 0);
        if (aLong == 0) {
            return -1;
        }
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (aLong > timeInMillis) {
            return (int) ((aLong - timeInMillis) / 1000);
        }
        return -1;
    }

    /**
     * 设置短信获取的限制时间
     */
    public static void saveCountDownTime(String username) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(KEY_COUNT_DOWN_TIME + username, Calendar.getInstance().getTimeInMillis() + GlobalKeyContans.SHORT_MESSAGE_TIME_INTERVAL * 1000);
        editor.commit();

    }

    private static void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static void saveLong(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value);
        editor.commit();
    }

    private static long getLong(String key) {
        return getSharedPreferences().getLong(key, 0);
    }

    private static String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    static SharedPreferences getSharedPreferences() {
        return JlhxApplication.getApplication().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

}
