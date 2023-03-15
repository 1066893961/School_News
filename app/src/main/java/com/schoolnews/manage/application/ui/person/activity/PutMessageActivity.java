package com.schoolnews.manage.application.ui.person.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.constant.AddressContants;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.utils.Preferences;
import com.schoolnews.manage.application.utils.ToastUtils;
import com.lzy.okgo.OkGo;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2021/4/1 10:35
 */
public class PutMessageActivity extends BaseActivity {


    @BindView(R.id.content_et)
    EditText contentEt;
    @BindView(R.id.add_tv)
    TextView addTv;

    public static void start(Context context) {
        Intent intent = new Intent(context, PutMessageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_bank;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "意见反馈";
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

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }


    @OnClick(R.id.add_tv)
    public void onClick() {
        ToastUtils.showShortToast("意见反馈提交成功");
        finish();
    }
}
