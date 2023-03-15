package com.schoolnews.manage.application;

import android.app.Activity;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import com.schoolnews.manage.application.utils.Preferences;
import com.schoolnews.manage.application.utils.Utils;
import com.lzy.okgo.OkGo;

import java.util.logging.Level;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class JlhxApplication extends MultiDexApplication {
    private static JlhxApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        //初始化utils
        Utils.init(this);
        //网络请求框架
        OkGo.init(this);
        OkGo.getInstance().setConnectTimeout(15 * 1000);   //超时事件15秒
        OkGo.getInstance().setRetryCount(1);   //重复请求1次
        registerActivityLifecycleCallbacks();

        if (BuildConfig.DEBUG) {
            OkGo.getInstance().debug("OkGo", Level.INFO, true);   //日志打印
        }
    }

    public static JlhxApplication getApplication() {
        return mApplication;
    }

    private int count = 0;

    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (count == 0) {
                    Preferences.ISBACKGROUND = false;
                }
                count++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                if (count == 0) {
                    Preferences.ISBACKGROUND = true;
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}