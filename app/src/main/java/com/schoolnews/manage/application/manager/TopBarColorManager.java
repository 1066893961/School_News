package com.schoolnews.manage.application.manager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.schoolnews.manage.application.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;



public class TopBarColorManager {
    private static int mColor = Color.WHITE;

    /**
     * 设置导航栏颜色
     */
    public static void setTopBarColor(boolean isHomePage, Activity activity, int color){
        mColor = color;
        setTopBarColor(isHomePage,activity);
    }



    /**
     * 设置导航栏颜色
     */
    public static void setTopBarColor(boolean isHomePage, Activity activity) {
        isHomePage = false;
        if (PhoneSystemManager.isMiui()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            {
                setStatusBarDarkMode(!isHomePage, activity);
                changeTopColorNomal(isHomePage, activity);
            }
            else
            {
                changeTopColorUmNomal(isHomePage, activity);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isHomePage) {
                // 沉浸式
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                //非沉浸式
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
            changeTopColorNomal(isHomePage, activity);
        }
        else {
            changeTopColorUmNomal(isHomePage, activity);
        }
    }

    private static void changeTopColorNomal(boolean isHomePage, Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            if (isHomePage) {
                window.setStatusBarColor(ContextCompat.getColor(activity, R.color.status_bar_red_color));
            } else {
                window.setStatusBarColor(mColor);
            }

            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
                ViewCompat.setFitsSystemWindows(mChildView, true);
            }
        }
    }

    private static void changeTopColorUmNomal(boolean isHomePage, Activity activity)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            if (isHomePage) {
                window.setStatusBarColor(ContextCompat.getColor(activity, R.color.status_bar_red_color));
            } else {
                window.setStatusBarColor(ContextCompat.getColor(activity,R.color.status_bar_gray_color));
            }

            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
                ViewCompat.setFitsSystemWindows(mChildView, true);
            }
        }
    }

    /**
     * 小米
     *
     * @param darkmode
     * @param activity
     */
    private static void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置状态栏背景为白色，字体深色
     * @param context
     */
    public static void setStatusBarMode(Activity context){
        if(context == null){
            return ;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(context, Color.WHITE);//白色
//            StatusBarCompat.setStatusBarMode(context,true);         //深色文字
//        }else{
//            StatusBarCompat.setStatusBarColor(context, Color.BLACK);//黑色
            LightStatusBarUtils.setLightStatusBar(context,true);    //深色文字
        }
    }
    /**
     * 设置状态栏背景为白色，字体深色
     * @param context
     */
    public static void setStatusBarModeTranslate(Activity context){
        if(context == null){
            return ;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            StatusBarCompat.setStatusBarColor(context, Color.TRANSPARENT,200);//透明,不生效
            StatusBarCompat.translucentStatusBar(context);//透明  ，生效同时内容可以顶到最上面
//            StatusBarCompat.setStatusBarMode(context,true);         //深色文字
//        }else{
//            StatusBarCompat.setStatusBarColor(context, Color.BLACK);//黑色
            LightStatusBarUtils.setLightStatusBar(context,false);    //浅色文字
        }
    }
}
