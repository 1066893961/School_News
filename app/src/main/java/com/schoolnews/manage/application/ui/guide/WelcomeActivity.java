package com.schoolnews.manage.application.ui.guide;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.ui.MainActivity;
import com.schoolnews.manage.application.ui.login.activity.LoginActivity;
import com.schoolnews.manage.application.utils.Preferences;

public class WelcomeActivity extends BaseActivity {
    Runnable runnable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
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
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        runnable = new Runnable() {
            @Override
            public void run() {
//               GuideActivity.startGuidActivity(mActivity);
//                if (Preferences.isLogin()) {
                    MainActivity.start(mActivity);
//                } else {
//                    LoginActivity.start(WelcomeActivity.this);
//                }
                finish();
            }
        };

        new Handler().postDelayed(runnable, 3000);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
