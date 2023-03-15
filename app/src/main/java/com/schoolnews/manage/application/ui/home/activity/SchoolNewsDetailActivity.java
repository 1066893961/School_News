package com.schoolnews.manage.application.ui.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.lzy.okgo.OkGo;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.base.BaseActivity;
import com.schoolnews.manage.application.bean.CommonListBean;
import com.schoolnews.manage.application.bean.FeeListBean;
import com.schoolnews.manage.application.bean.InfoBean;
import com.schoolnews.manage.application.constant.AddressContants;
import com.schoolnews.manage.application.constant.GlobalConfigContants;
import com.schoolnews.manage.application.http.HttpHelper;
import com.schoolnews.manage.application.http.JsonCallback;
import com.schoolnews.manage.application.http.LzyResponse;
import com.schoolnews.manage.application.ui.account.adapter.PingLunListAdapter;
import com.schoolnews.manage.application.ui.dialog.DialogForShare;
import com.schoolnews.manage.application.ui.dialog.OnDialogShareListener;
import com.schoolnews.manage.application.utils.Preferences;
import com.schoolnews.manage.application.utils.ShareManager;
import com.schoolnews.manage.application.utils.StringUtils;
import com.schoolnews.manage.application.utils.ToastUtils;
import com.schoolnews.manage.application.utils.image.GlideLoader;
import com.schoolnews.manage.application.views.CustomLinearLayoutManager;
import com.schoolnews.manage.application.views.PopupInputWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * @Description: 校园资讯详情
 * @Author: leo.li
 * @CreateDate: 2020/12/17 11:30
 */
public class SchoolNewsDetailActivity extends BaseActivity implements OnDialogShareListener {

    @BindView(R.id.top_iv)
    ImageView topIv;
    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.shoucang_img)
    ImageView shoucangImg;
    @BindView(R.id.shoucang_ll)
    LinearLayout shoucangLl;
    @BindView(R.id.comment_ll)
    LinearLayout commentLl;
    @BindView(R.id.share_ll)
    LinearLayout shareLl;
    @BindView(R.id.pinglun_rv)
    RecyclerView pinglunRv;
    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.ns)
    NestedScrollView ns;

    private PopupInputWindow mInputWindow;
    final Handler myHandler = new Handler();
    private CommonListBean mRecordsBean;
    private PingLunListAdapter mPingLunListAdapter;

    public static void startAction(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, SchoolNewsDetailActivity.class);
        context.startActivity(intent);
    }

    public static void startAction(Activity context, CommonListBean recordsBean) {
        Intent intent = new Intent();
        intent.setClass(context, SchoolNewsDetailActivity.class);
        intent.putExtra("bean", recordsBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_schoole_news_detail_info_layout;
    }

    @Override
    protected boolean isShowTitle() {
        return true;
    }

    @Override
    protected String getTitleName() {
        return "新闻详情";
    }

    @Override
    protected View getRightView() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRecordsBean = (CommonListBean) getIntent().getSerializableExtra("bean");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPingLunListAdapter = new PingLunListAdapter(R.layout.pinglun_list_item);
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(mActivity);
        linearLayoutManager.setScrollEnabled(false);
        pinglunRv.setLayoutManager(linearLayoutManager);
        pinglunRv.setAdapter(mPingLunListAdapter);

        if (mRecordsBean.getCollectionId() == 0) {
            shoucangImg.setImageResource(R.mipmap.icon_shoucang_normal);
        } else {
            shoucangImg.setImageResource(R.mipmap.icon_shoucang_hover);
        }

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        if (StringUtils.isEmpty(mRecordsBean.getUrl())) {
            videoView.setVisibility(View.GONE);
            topIv.setVisibility(View.GONE);
        } else {
            if (mRecordsBean.getUrl().contains(".jpg") || mRecordsBean.getUrl().contains(".png")
                    || mRecordsBean.getUrl().contains(".svg") || mRecordsBean.getUrl().contains(".jpeg")) {
                videoView.setVisibility(View.GONE);
                topIv.setVisibility(View.VISIBLE);
                GlideLoader.loadNoCenter(mActivity, mRecordsBean.getUrl(), topIv, R.mipmap.icon_school);
                contentTv.setText(mRecordsBean.getContent());
            } else {
                videoView.setVisibility(View.VISIBLE);
                topIv.setVisibility(View.GONE);
                videoView.setVideoPath(mRecordsBean.getUrl());
                MediaController mediaController = new MediaController(this);
                //VideoView与MediaController建立关联
                videoView.setMediaController(mediaController);
                videoView.start();
                videoView.requestFocus();
            }
        }

        getCommonList();
    }

    private void getCommonList() {
        OkGo.get(AddressContants.API_SERVER_COMMON_LIST + "?newsId=" + mRecordsBean.getId())
                .execute(new JsonCallback<LzyResponse<List<CommonListBean>>>() {

                    @Override
                    public void onSuccess(LzyResponse<List<CommonListBean>> agentBeanLzyResponse, Call call, Response response) {
                        List<CommonListBean> commonListBeans = agentBeanLzyResponse.list;
                        mPingLunListAdapter.setNewData(commonListBeans);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    @OnClick({R.id.shoucang_ll, R.id.comment_ll, R.id.share_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shoucang_ll:
                if (mRecordsBean.getCollectionId() == 0) {
                    conlectionThisDemand();
                } else {
                    cancelConlectionThisDemand();
                }
                break;
            case R.id.comment_ll:
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mInputWindow == null) {
                            mInputWindow = new PopupInputWindow(mActivity);
                        }
                        mInputWindow.showPopup300(shoucangLl, new PopupInputWindow.OnInputListener() {
                            @Override
                            public void inputString(String comment) {
                                OkGo.post(AddressContants.API_SERVER_ADD_COMMON_LIST)
                                        .isMultipart(true)
                                        .headers("Content-Type", "multipart/form-data; boundary=;")
                                        .params("userId", Preferences.getUserId())
                                        .params("newsId", mRecordsBean.getId())
                                        .params("content", comment)
                                        .execute(new JsonCallback<LzyResponse<Object>>() {

                                            @Override
                                            public void onSuccess(LzyResponse<Object> agentBeanLzyResponse, Call call, Response response) {
                                                ToastUtils.showShortToast("评论成功");
                                                getCommonList();
                                            }

                                            @Override
                                            public void onError(Call call, Response response, Exception e) {
                                                super.onError(call, response, e);
                                            }
                                        });
                            }
                        });
                    }
                });
                break;
            case R.id.share_ll:
                DialogForShare dialogForShare = new DialogForShare();
                dialogForShare.show(getSupportFragmentManager(), "about_apollo");
                break;
        }
    }

    /**
     * 收藏
     */
    private void conlectionThisDemand() {
        OkGo.post(AddressContants.API_SERVER_COLLECT)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("userId", Preferences.getUserId())
                .params("newsId", mRecordsBean.getId())
                .execute(new JsonCallback<LzyResponse<InfoBean>>() {

                    @Override
                    public void onSuccess(LzyResponse<InfoBean> agentBeanLzyResponse, Call call, Response response) {
                        ToastUtils.showShortToast("收藏成功");
                        shoucangImg.setImageResource(R.mipmap.icon_shoucang_hover);
                        mRecordsBean.setCollectionId(111);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.showLongToast("收藏失败");
                    }
                });
    }


    /**
     * 取消收藏
     */
    private void cancelConlectionThisDemand() {

        OkGo.post(AddressContants.API_SERVER_CANCEL_COLLECT)
                .isMultipart(true)
                .headers("Content-Type", "multipart/form-data; boundary=;")
                .params("collectionId", mRecordsBean.getCollectionId())
                .execute(new JsonCallback<LzyResponse<Object>>() {

                    @Override
                    public void onSuccess(LzyResponse<Object> agentBeanLzyResponse, Call call, Response response) {
                        ToastUtils.showShortToast("取消收藏");
                        shoucangImg.setImageResource(R.mipmap.icon_shoucang_normal);
                        mRecordsBean.setCollectionId(0);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.showLongToast("收藏失败");
                    }
                });
    }

    @Override
    public void weixin() {
        ShareManager.getInstance().shareWeiXinAction(mActivity, "www.baidu.com", "校园新闻", "", "校园新鲜事早知道");
        ToastUtils.showLongToast("请配置正确的UMENG_APPKEY");
    }

    @Override
    public void weibo() {
        ShareManager.getInstance().shareSinaAction(mActivity, "www.baidu.com", "校园新闻", "", "校园新鲜事早知道");
        ToastUtils.showLongToast("请配置正确的UMENG_APPKEY");
    }

    @Override
    public void qq() {
        ShareManager.getInstance().shareQQAction(mActivity, "www.baidu.com", "校园新闻", "", "校园新鲜事早知道");
        ToastUtils.showLongToast("请配置正确的UMENG_APPKEY");
    }
}
