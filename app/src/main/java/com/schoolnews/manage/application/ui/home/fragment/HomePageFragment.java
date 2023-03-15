package com.schoolnews.manage.application.ui.home.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseFragment;
import com.schoolnews.manage.application.base.BaseFragmentAdapter;
import com.schoolnews.manage.application.ui.home.activity.SearchActivity;
import com.schoolnews.manage.application.views.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class HomePageFragment extends BaseFragment {

    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.resource_title_rel)
    RelativeLayout resourceTitleRel;
    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;
    @BindView(R.id.search_tv)
    TextView searchTv;

    private BaseFragmentAdapter mFragmentAdapter;
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private String[] mTitles = {"推荐", "要闻", "视频", "娱乐", "体育", "财经", "科技", "军事", "游戏"};
    private int mTabId;

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BaseFragment fragment1 = CLassListFragment.newInstance(1);
        BaseFragment fragment2 = CLassListFragment.newInstance(2);
        BaseFragment fragment3 = CLassListFragment.newInstance(3);
        BaseFragment fragment4 = CLassListFragment.newInstance(4);
        BaseFragment fragment5 = CLassListFragment.newInstance(5);
        BaseFragment fragment6 = CLassListFragment.newInstance(6);
        BaseFragment fragment7 = CLassListFragment.newInstance(7);
        BaseFragment fragment8 = CLassListFragment.newInstance(8);
        BaseFragment fragment9 = CLassListFragment.newInstance(9);
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);
        mFragmentList.add(fragment5);
        mFragmentList.add(fragment6);
        mFragmentList.add(fragment7);
        mFragmentList.add(fragment8);
        mFragmentList.add(fragment9);

        mFragmentAdapter = new BaseFragmentAdapter(mActivity.getSupportFragmentManager(), mFragmentList);
        mViewPager.setOffscreenPageLimit(9);
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

    @OnClick(R.id.search_tv)
    public void onClick() {
        SearchActivity.startAction(mActivity);
    }
}
