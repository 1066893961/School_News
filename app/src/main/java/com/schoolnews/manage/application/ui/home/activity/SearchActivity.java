package com.schoolnews.manage.application.ui.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.bean.CommonListBean;
import com.schoolnews.manage.application.constant.AddressContants;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.ui.home.adapter.SchoolNewsAdapter;
import com.schoolnews.manage.application.utils.Preferences;
import com.schoolnews.manage.application.utils.StringUtils;
import com.schoolnews.manage.application.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/12/17 11:30
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.clear_iv)
    ImageView clearIv;
    @BindView(R.id.search_result_rv)
    RecyclerView searchResultRv;

    private SchoolNewsAdapter mSchoolNewsAdapter;


    public static void startAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, SearchActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected boolean isShowTitle() {
        return false;
    }

    @Override
    protected String getTitleName() {
        return "";
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
        mSchoolNewsAdapter = new SchoolNewsAdapter(R.layout.news_list_item);
        searchResultRv.setLayoutManager(new LinearLayoutManager(mActivity));
        searchResultRv.setAdapter(mSchoolNewsAdapter);
    }

    @Override
    protected void setListener() {
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!StringUtils.isEmpty(v.getText().toString())) {
                        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        getSearchResult(v.getText().toString());
                    } else {
                        ToastUtils.showShortToast("请输入搜索内容");
                        return true;
                    }
                }
                return false;
            }
        });

        mSchoolNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SchoolNewsDetailActivity.startAction(mActivity, mSchoolNewsAdapter.getData().get(position));
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }


    private void getSearchResult(String searchName) {
        OkGo.get(AddressContants.API_SERVER_SEARCH + "?key=" +searchName)
                .execute(new JsonCallback<LzyResponse<List<CommonListBean>>>() {

                    @Override
                    public void onSuccess(LzyResponse<List<CommonListBean>> agentBeanLzyResponse, Call call, Response response) {
                        List<CommonListBean> commonListBeans = agentBeanLzyResponse.list;
                        mSchoolNewsAdapter.setNewData(commonListBeans);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    @OnClick({R.id.clear_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_iv:
                searchEt.setText("");
                break;
        }
    }

}
