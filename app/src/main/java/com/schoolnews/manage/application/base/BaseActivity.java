package com.schoolnews.manage.application.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolnews.manage.application.JlhxApplication;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.constant.GlobalKeyContans;
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.manager.ActivityCollector;
import com.schoolnews.manage.application.utils.dialog.DialogMaker;
import com.schoolnews.manage.application.views.immersionbarview.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG;
    protected JlhxApplication mApp;
    protected Activity mActivity;
    protected Unbinder unbinder;
    protected AppBarLayout topView;
    protected Toolbar mToolBar;
    protected View mToolbar_line;
    private TextView mTitle;
    private RelativeLayout mRightRl, mLeftRl;
    private FinishReceiver finishReceiver;
    protected ImmersionBar mImmersionBar;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object myEvent) {
    }
    /**
     * 判断某个界面是否在前台
     *
     * @param context   Context
     * @param className 界面的类名
     * @return 是否在前台显示
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 留给子类去覆盖，需要修改颜色的子类自己去处理，默认透明
     */
    protected void setStatusBarColor(){
//        TopBarColorManager.setStatusBarMode(this);
        mImmersionBar = ImmersionBar.with(this)
                .statusBarColor(R.color.transparent)
                .statusBarDarkFont(true, 0.5f)
                .flymeOSStatusBarFontColorInt(Color.BLACK)
                .navigationBarColor(R.color.color_000000)
                .fitsSystemWindows(false);
        mImmersionBar.init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this,getClass());
        TAG = this.getClass().getSimpleName();
        mApp = JlhxApplication.getApplication();
        mActivity = this;

//        TopBarColorManager.setTopBarColor(false, mActivity);
        setStatusBarColor();

        initData(savedInstanceState);
        if (isShowTitle()) {
            setContentView(R.layout.layout_toolbar);
            initToolBar();
        } else {
            setContentView(R.layout.layout_toolbar_notitle);
        }
        initContentView();
        initView(savedInstanceState);
        setListener();
        processLogic(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        registerMessageReceiver();
    }

    private void initToolBar() {
        topView = (AppBarLayout) findViewById(R.id.top_view);
        mToolBar = (Toolbar) findViewById(R.id.top_toolbar);
        mToolbar_line = findViewById(R.id.toolbar_line);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mRightRl = (RelativeLayout) findViewById(R.id.toolbar_right_rl);
        mLeftRl = (RelativeLayout) findViewById(R.id.toolbar_back_rl);
        View view = getRightView();
        if (view != null) {
            mRightRl.addView(view);
        }
        // mToolBar.setPopupTheme(R.style.AppTheme_PopupOverlay);
        // mToolBar.setSubtitleTextColor(Color.WHITE);
        // mToolBar.setTitleTextColor(Color.WHITE);
        // mToolBar.setOverflowIcon(getResources().getDrawable(R.mipmap.nav_more_icon));
        mToolBar.setTitle("");
        mTitle.setText(null == getTitleName() ? getString(R.string.app_name) : getTitleName());
        setSupportActionBar(mToolBar);
        showBackButton();
        mToolBar.setVisibility(View.VISIBLE);
        mToolbar_line.setVisibility(View.GONE);
    }

    /**
     * 是否显示标题栏下方分割线  目前设计的页面有些有  有些没有
     * @param isShow
     */
    public void isShowDriveLine(boolean isShow) {
        if (isShow) {
            mToolbar_line.setVisibility(View.VISIBLE);
        } else {
            mToolbar_line.setVisibility(View.GONE);
        }
    }

    public void showLoadingDialog() {
        DialogMaker.showProgressDialog(this, null);
    }

    public void dismissLoadingDialog() {
        DialogMaker.dismissProgressDialog();
    }

    /**
     *
     * @param text  title显示文字
     */
    protected void setTitleText(CharSequence text){
        if(mTitle != null || text == null){
            mTitle.setText(text);
        }
    }

    private void initContentView() {
        FrameLayout mFrameLayout = (FrameLayout) findViewById(R.id.content_layout);
        getLayoutInflater().inflate(getLayoutId(), mFrameLayout);

        unbinder = ButterKnife.bind(this);
    }

    /**
     * 重新设置右侧按钮
     * @param view
     */
    protected void resetRightButton(View view){
        mRightRl.removeAllViews();
        if (view != null) {
            mRightRl.addView(view);
        }
    }

    /**
     * 显示返回按钮，并添加点击事件
     */
    protected void showBackButton() {
        mLeftRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        /*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationIcon(R.mipmap.back);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    /**
     * 获取布局视图
     */
    protected abstract int getLayoutId();

    /**
     * 是否显示标题栏
     */
    protected abstract boolean isShowTitle();

    /**
     * 获取标题
     *
     * @return
     */
    protected abstract String getTitleName();

    /**
     * 获取右边自定义视图
     *
     * @return
     */
    protected abstract View getRightView();

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);


    protected void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        if (isShow) {
            if (getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(getCurrentFocus(), 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(finishReceiver != null){
            LocalBroadcastManager.getInstance(this).unregisterReceiver(finishReceiver);
        }
        if(mImmersionBar != null){
            mImmersionBar.destroy();
        }
        fixInputMethodManagerLeak(this);
        if (unbinder != null) {
            unbinder.unbind();
        }


        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        HttpHelper.cancelTag(TAG);
        ActivityCollector.removeActivity(this);
    }


    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private class FinishReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(addOnReceive(context,intent)){
                return ;
            }
            if (GlobalKeyContans.MESSAGE_FINISH_ACTION.equals(intent.getAction())) {
                finish();
            }
        }
    }

    private void registerMessageReceiver() {
        finishReceiver = new FinishReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(GlobalKeyContans.MESSAGE_FINISH_ACTION);
        addFilterAction(filter);
        LocalBroadcastManager.getInstance(this).registerReceiver(finishReceiver, filter);
    }

    /**这个放给子类实现自己的receiver,一个Activity不能实现多个，所以用这中方法，base里面实现，让子类去增加action
     * 父类实现了 一个关闭自己的receiver
     */
    protected void addFilterAction(IntentFilter filter){
        //默认实现
    }

    /**
     * 子类处理receiver回调
     * @param context
     * @param intent
     * @return      true:自己处理receiver,false：父类处理receiver
     */
    protected boolean addOnReceive(Context context, Intent intent) {
        //默认实现
        return false;
    }

}