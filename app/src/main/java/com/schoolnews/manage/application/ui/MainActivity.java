package com.schoolnews.manage.application.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.constant.GlobalKeyContans;
import com.schoolnews.manage.application.info.TabLayoutInfo;
import com.schoolnews.manage.application.ui.account.fragment.AccountFragment;
import com.schoolnews.manage.application.ui.account.fragment.ConsultFragment;
import com.schoolnews.manage.application.ui.home.fragment.HomePageFragment;
import com.schoolnews.manage.application.ui.person.fragment.PersonInfoFragment;
import com.schoolnews.manage.application.utils.Preferences;
import com.schoolnews.manage.application.views.TabDynamicLayoutView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements TabDynamicLayoutView.OnTabSelectedListener {
    public static final String TAB_INDEX = "index";
    public static boolean isForeground = false;

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.tab_dynamic_layout)
    TabDynamicLayoutView mDynamicTabLayout;

    private @GlobalKeyContans.TabIndex
    int mTabIndex = GlobalKeyContans.MAIN_HOMEPAGE_INDEX;
    private HomePageFragment mHomePageFragment;
    private AccountFragment mAccountFragment;
    private ConsultFragment mConsultFragment;
    private PersonInfoFragment mPersonInfoFragment;
    private long exitTime = 0;

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    @Override
    protected String getTitleName() {
        return null;
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTabIndex = getIntent().getIntExtra(TAB_INDEX, GlobalKeyContans.MAIN_HOMEPAGE_INDEX);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        List<TabLayoutInfo> list = new ArrayList<>();
        list.add(new TabLayoutInfo(R.drawable.homepage_tab_workbench, R.string.homepage, GlobalKeyContans.MAIN_HOMEPAGE_INDEX));
        list.add(new TabLayoutInfo(R.drawable.homepage_tab_garb_single, R.string.account, GlobalKeyContans.MAIN_SINGLE_INDEX));
        list.add(new TabLayoutInfo(R.drawable.homepage_tab_basic, R.string.manage, GlobalKeyContans.MAIN_CONSULT));
        list.add(new TabLayoutInfo(R.drawable.homepage_tab_my, R.string.my, GlobalKeyContans.MAIN_MY_INDEX));
        mDynamicTabLayout.setTabList(list);
    }

    @Override
    protected void setListener() {
        mDynamicTabLayout.setOnTabSelectedListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDynamicTabLayout.setDefaultTab(mTabIndex);
        isForeground = true;
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 隐藏fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomePageFragment) {
            transaction.hide(mHomePageFragment);
        }
        if (null != mAccountFragment) {
            transaction.hide(mAccountFragment);
        }
        if (null != mConsultFragment) {
            transaction.hide(mConsultFragment);
        }
        if (null != mPersonInfoFragment) {
            transaction.hide(mPersonInfoFragment);
        }
    }


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 保存关闭时当前所处的fragment的指针
        outState.putInt(TAB_INDEX, mTabIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // 页面切回来时恢复到关闭前的fragment指针
        mTabIndex = savedInstanceState.getInt(TAB_INDEX);
    }


    @Override
    public void onTabSelected(int index) {
        // 开启事物
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 隐藏fragment
        hideFragments(transaction);
        switch (index) {
            case GlobalKeyContans.MAIN_HOMEPAGE_INDEX:
                if (null == mHomePageFragment) {
                    mHomePageFragment = HomePageFragment.newInstance();
                    transaction.add(R.id.container, mHomePageFragment);
                } else {
                    transaction.show(mHomePageFragment);
                }
                mImmersionBar.statusBarDarkFont(true).init();//深色文字
                break;
            case GlobalKeyContans.MAIN_SINGLE_INDEX:
                if (null == mAccountFragment) {
                    mAccountFragment = AccountFragment.newInstance();
                    transaction.add(R.id.container, mAccountFragment);
                } else {
                    transaction.show(mAccountFragment);
                }
                mImmersionBar.statusBarDarkFont(true).init(); //深色文字

                break;
              case GlobalKeyContans.MAIN_CONSULT:
                if (null == mConsultFragment) {
                    mConsultFragment = ConsultFragment.newInstance();
                    transaction.add(R.id.container, mConsultFragment);
                } else {
                    transaction.show(mConsultFragment);
                }
                mImmersionBar.statusBarDarkFont(true).init(); //深色文字

                break;
            case GlobalKeyContans.MAIN_MY_INDEX:
                if (null == mPersonInfoFragment) {
                    mPersonInfoFragment = PersonInfoFragment.newInstance();
                    transaction.add(R.id.container, mPersonInfoFragment);
                } else {
                    transaction.show(mPersonInfoFragment);
                }
                mImmersionBar.statusBarDarkFont(true).init();//深色文字

                break;

        }

        mTabIndex = index;
        // 此方法是在使用onSaveInstanceState之后替代commit方法的
        // 状态保存后在添加fragment会有异常 这个方法可以避免
        transaction.commitAllowingStateLoss();
    }

    // 两次点击返回时间不超过2秒的话退出应用

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();

            } else {
                System.exit(0);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
