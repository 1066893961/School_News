package com.schoolnews.manage.application.ui.login.activity;

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
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class RegistActivity extends BaseActivity {

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
    @BindView(R.id.user_name_et)
    EditText userNameEt;
    @BindView(R.id.study_no_et)
    EditText studyNoEt;
    @BindView(R.id.class_name_et)
    EditText classNameEt;
    @BindView(R.id.regist_ll)
    LinearLayout registLl;

    public static void start(Context context) {
        Intent intent = new Intent(context, RegistActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        titleTv.setText("注册");
        loginTv.setVisibility(View.GONE);
        registLl.setVisibility(View.GONE);
    }


    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }


    @OnClick({R.id.clear_iv, R.id.regist_tv, R.id.login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_iv:
                inputPhoneEt.setText(null);
                break;
            case R.id.regist_tv:
                loginCient();
                break;
        }
    }

    /**
     * 注册
     */
    private void loginCient() {
        showLoadingDialog();

        HttpHelper.regist(TAG, inputPhoneEt.getText().toString(), vertifyEt.getText().toString(), userNameEt.getText().toString(),
                studyNoEt.getText().toString(),  new JsonCallback<LzyResponse<Object>>() {
                    @Override
                    public void onSuccess(LzyResponse<Object> lzyResponse, Call call, Response response) {
                        LoginActivity.start(mActivity);
                        finish();
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoadingDialog();
                        ToastUtils.showLongToast("注册失败");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}

