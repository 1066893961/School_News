package com.schoolnews.manage.application.ui.person.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.bean.InfoBean;
import com.schoolnews.manage.application.event.ModifySuccessEvent;
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.utils.Preferences;
import com.schoolnews.manage.application.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2021/4/1 10:35
 */
public class PersonInfoActivity extends BaseActivity {

    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.age_et)
    EditText ageEt;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.modify_tv)
    TextView modifyTv;

    public static void start(Context context) {
        Intent intent = new Intent(context, PersonInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "修改个人信息";
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
        nameEt.setText(Preferences.getUsername());
        phoneEt.setText(Preferences.getKeyUserMobile());
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @OnClick(R.id.modify_tv)
    public void onClick() {
        modifyInfo();
    }

    /**
     * 登陆
     */
    private void modifyInfo() {
        showLoadingDialog();
        HttpHelper.modifyInfo(TAG, nameEt.getText().toString(), ageEt.getText().toString(), phoneEt.getText().toString(), new JsonCallback<LzyResponse<InfoBean>>() {
            @Override
            public void onSuccess(LzyResponse<InfoBean> lzyResponse, Call call, Response response) {

                EventBus.getDefault().post(new ModifySuccessEvent());
//                Preferences.saveUserId(mInfoBean.getId());
//                Preferences.saveUserName(mInfoBean.getUserName());
//                Preferences.saveUserStudyNo(mInfoBean.getStudyNo());
//                Preferences.saveUserPhone(mInfoBean.getMobile());
//                Preferences.saveUserClassName(mInfoBean.getClassName());
                ToastUtils.showLongToast("修改成功");
                finish();
                dismissLoadingDialog();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                dismissLoadingDialog();
                finish();
            }
        });
    }
}
