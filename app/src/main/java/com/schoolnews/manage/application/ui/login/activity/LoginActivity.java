package com.schoolnews.manage.application.ui.login.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.bean.InfoBean;
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.manager.ActivityCollector;
import com.schoolnews.manage.application.ui.MainActivity;
import com.schoolnews.manage.application.utils.Preferences;
import com.schoolnews.manage.application.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.account_tv)
    TextView accountTv;
    @BindView(R.id.input_phone_et)
    EditText inputPhoneEt;
    @BindView(R.id.clear_iv)
    ImageView clearIv;
    @BindView(R.id.vertify_tv)
    TextView vertifyTv;
    @BindView(R.id.vertify_et)
    EditText vertifyEt;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.regist_tv)
    TextView registTv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.regist_ll)
    LinearLayout registLl;

    protected InfoBean mInfoBean;// 登陆信息

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * @param context
     * @param flag    为空时不弹框，适配锁定操作
     */
    public static void start(Context context, String flag) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("LOGOUT_MESSAGE", flag);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (context instanceof Application) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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
        registLl.setVisibility(View.GONE);
    }


    @Override
    protected void setListener() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCollector.removeAllActivity();
    }


    @Override
    protected boolean addOnReceive(Context context, Intent intent) {
        return true;
    }


    @OnClick({R.id.clear_iv, R.id.regist_tv, R.id.login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_iv:
                inputPhoneEt.setText(null);
                break;
            case R.id.regist_tv:
                RegistActivity.start(mActivity);
                break;
            case R.id.login_tv:
//                loginCient();
                MainActivity.start(getApplicationContext());
                finish();
                break;
        }
    }

    /**
     * 登陆
     */
    private void loginCient() {
        showLoadingDialog();
        HttpHelper.login(TAG, inputPhoneEt.getText().toString(), vertifyEt.getText().toString(), new JsonCallback<LzyResponse<InfoBean>>() {
            @Override
            public void onSuccess(LzyResponse<InfoBean> lzyResponse, Call call, Response response) {

                mInfoBean = lzyResponse.entity;

                Preferences.saveUserId(mInfoBean.getId());
                Preferences.saveUserName(mInfoBean.getUserName());
                Preferences.saveUserStudyNo(mInfoBean.getStudyNo());
                Preferences.saveUserPhone(mInfoBean.getMobile());
                Preferences.saveUserClassName(mInfoBean.getClassName());
                MainActivity.start(getApplicationContext());
                finish();
                dismissLoadingDialog();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                dismissLoadingDialog();
                ToastUtils.showLongToast("登录失败");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

