package com.schoolnews.manage.application.ui.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseFragment;
import com.schoolnews.manage.application.bean.CommonListBean;
import com.schoolnews.manage.application.bean.FeeListBean;
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.ui.home.activity.SchoolNewsDetailActivity;
import com.schoolnews.manage.application.ui.home.adapter.SchoolNewsAdapter;
import com.schoolnews.manage.application.utils.ToastUtils;
import com.schoolnews.manage.application.utils.dialog.DialogMaker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Author:lwz
 * Time:2019/7/16
 * Description:
 */

public class CLassListFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private static final String KEY_TABID = "tabid";
    @BindView(R.id.black_list_rv)
    RecyclerView blackListRv;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout refreshLayout;


    private int mTabId;
    private SchoolNewsAdapter mOrderListAdapter;
    //    private List<GrabSingleListBean> grabSingleListBeans = new ArrayList<>();
    //请求页数
    private int mPageNum;
//    private OrderBean mOrderBean;


    public static CLassListFragment newInstance(int tabId) {
        CLassListFragment fragment = new CLassListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_TABID, tabId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTabId = getArguments().getInt(KEY_TABID);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getContext(), true));
        refreshLayout.setPullDownRefreshEnable(true);

        mOrderListAdapter = new SchoolNewsAdapter(R.layout.news_list_item);
        blackListRv.setLayoutManager(new LinearLayoutManager(mActivity));
        blackListRv.setAdapter(mOrderListAdapter);

        mOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SchoolNewsDetailActivity.startAction(mActivity, mOrderListAdapter.getData().get(position));
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

//        getListData(mTabId);
        List<CommonListBean> commonListBeans = new ArrayList<>();
        CommonListBean commonListBean = new CommonListBean();
        commonListBean.setTitle("油价突变！3月17日加油站92、95号汽油，零号柴油最新限价！");
        commonListBean.setContent("站在农民角度，关注三农！农民兄弟们大家好，这里是老道编辑部！时间过得飞快，一转眼2023年已经来到3月中旬！随着国家各项惠农政策的全面落地，农业农村在2023年也将迎来一系列的发展机遇！现在正值春耕生产的关键阶段，国家针对粮食产业方面也格外重视！按照中央一号文件正式发布的相关内容来看，在2023年粮食产业也将迎来一系列的重大利好！不过当大家获得政策层面强力扶持的同时，汽柴油价格的调整或将再次让农民面临春耕成本增加的问题！按照国家发改部门制定的油品调控政策来看，进入到3月17日开始之后，新一轮汽柴油价格调整或将再次启动！从现在国际原油市场情况来看，3月下旬国内成品油价格很有可能会再次迎来行情突变！今天老道就为大家重点关注一下，油价调整的相关趋势！具体情况，咱们一起说一说！\n" +
                "\n" +
                "【油价突变！3月17日加油站92、95号汽油，零号柴油最新限价！】\n" +
                "\n" +
                "老道说：最近几天网络上关于汽柴油价格调控的讨论很热烈，从2023年开始一直到现在已经完成的5轮次油价调整当中，有两次油价迎来上调，一次油价迎来下跌，两次油价处于搁浅调整状态！综合油价涨跌调整情况来看，今年开年国内成品油价格总体处于上涨区间，汽柴油价格截止到现在累计涨幅已经达到245元每吨左右！客观地说，农民今年的春耕种地成本迎来提高已经成为现实！那么在3月17日，即将启动的新一轮汽柴油价格调整当中，油价是否能够迎来下调？当前国际原油市场又有哪些新变化？起码有3件事，希望广大的农民兄弟能够有所了解！");
        commonListBean.setUrl("https://pics6.baidu.com/feed/c2cec3fdfc039245c919320f6a9a7fc97d1e2518.jpeg@f_auto?token=8ba9a53502493c924a85daa2682f186b");
        commonListBeans.add(commonListBean);
        commonListBeans.add(commonListBean);
        commonListBeans.add(commonListBean);
        commonListBeans.add(commonListBean);
        commonListBeans.add(commonListBean);
        mOrderListAdapter.setNewData(commonListBeans);
    }

    private void getListData(int tabId) {
        showLoadingDialog();
        HttpHelper.getCommonList(TAG, tabId, new JsonCallback<LzyResponse<List<CommonListBean>>>() {
            @Override
            public void onSuccess(LzyResponse<List<CommonListBean>> listLzyResponse, Call call, Response response) {

                mOrderListAdapter.setNewData(listLzyResponse.list);
                DialogMaker.dismissProgressDialog();
                refreshLayout.endRefreshing();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                ToastUtils.showShortToast("加载失败");
                dismissLoadingDialog();
                refreshLayout.endRefreshing();
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getListData(mTabId);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
