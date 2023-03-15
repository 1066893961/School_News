package com.schoolnews.manage.application.ui.account.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.RelativeLayout;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseFragment;
import com.schoolnews.manage.application.base.BaseFragmentAdapter;
import com.schoolnews.manage.application.ui.home.fragment.CLassListFragment;
import com.schoolnews.manage.application.views.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//党政教育

public class ConsultFragment extends BaseFragment{

    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.resource_title_rel)
    RelativeLayout resourceTitleRel;
    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;


    private BaseFragmentAdapter mFragmentAdapter;
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private String[] mTitles = {"党政新闻", "高校教育新闻", "关于考研"};


    public static ConsultFragment newInstance() {
        ConsultFragment fragment = new ConsultFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xinling_luntan;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BaseFragment fragment1 = CLassListFragment.newInstance(12);
        BaseFragment fragment2 = CLassListFragment.newInstance(13);
        BaseFragment fragment3 = CLassListFragment.newInstance(14);
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mFragmentList);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabs.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        mTabs.getTabAt(0).setText(mTitles[0]);

        for (int i = 0; i < mTitles.length; i++) {
            mTabs.getTabAt(i).setText(mTitles[i]);
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }


}
