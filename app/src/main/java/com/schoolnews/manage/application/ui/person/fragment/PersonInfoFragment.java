package com.schoolnews.manage.application.ui.person.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseFragment;
import com.schoolnews.manage.application.ui.login.activity.LoginActivity;
import com.schoolnews.manage.application.ui.person.activity.MyCollectionActivity;
import com.schoolnews.manage.application.ui.person.activity.PutMessageActivity;
import com.schoolnews.manage.application.utils.Preferences;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class PersonInfoFragment extends BaseFragment {


    @BindView(R.id.person_header_ll)
    LinearLayout personHeaderLl;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.class_tv)
    TextView classTv;
    @BindView(R.id.num_tv)
    TextView numTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.info_rel)
    RelativeLayout infoRel;
    @BindView(R.id.logout_rel)
    RelativeLayout logoutRel;

    public static PersonInfoFragment newInstance() {
        PersonInfoFragment fragment = new PersonInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person_info;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        nameTv.setText("姓名：" + Preferences.getUsername());
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }


    @OnClick({R.id.info_rel, R.id.logout_rel, R.id.fankui_rel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.info_rel:
                //我的收藏
                MyCollectionActivity.start(mActivity);
                break;
        case R.id.fankui_rel:
                PutMessageActivity.start(mActivity);
                break;
            case R.id.logout_rel:
                Preferences.cleanUserInfo();
                LoginActivity.start(mActivity);
                break;
        }
    }
}
