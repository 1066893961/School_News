package com.schoolnews.manage.application.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.WindowManager;

import com.schoolnews.manage.application.JlhxApplication;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.utils.dialog.DialogMaker;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.lang.ref.WeakReference;

/**
 * Author:lwz
 * Time:2019/12/20
 * Description:
 */


public class ShareManager {

    private static WeakReference<ShareManager> instance = null;
    private static WeakReference<ShareBoardConfig> config;
    private boolean is_dismiss;
    private Activity activity;
    private String valueNameWeChat;
    private String valueNameCircle;
    private String valueNameSina;
    private String valueNameQQ;
    private String valueTitle;
    private String keyTitle;

    public static ShareManager getInstance() {
        if (instance == null || instance.get() == null) {
            instance = new WeakReference<>(new ShareManager());
            UMShareAPI.get(JlhxApplication.getApplication());
        }
        return instance.get();
    }


    /**
     * 分享
     *
     * @param activity
     * @param url      链接
     * @param title    标题
     * @param img      图片链接
     * @param des      描述
     */
    public void shareAction(Activity activity, String url, String title, String img, String des) {
        LogUtil.i("SHARE", "title:" + title);
        UMWeb web = getUMWeb(activity, url, title, img, des);
        if (web == null)
            return;
        this.activity = activity;
        if (config == null || config.get() == null) {
            config = new WeakReference<>(new ShareBoardConfig());
            config.get().setTitleVisibility(false);
            config.get().setCancelButtonVisibility(false);
            config.get().setIndicatorVisibility(false);
            config.get().setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);
            config.get().setShareboardBackgroundColor(Color.WHITE);
            config.get().setMenuItemTextColor(Color.rgb(63, 63, 63));
        }
        new ShareAction(activity).withMedia(web)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ)
                .setCallback(umShareListener).open(config.get());

    }


    /**
     * 分享
     *
     * @param activity
     * @param url      链接
     * @param title    标题
     * @param res      图片链接
     * @param des      描述
     */
    public void shareAction(Activity activity, String url, String title, int res, String des) {
        LogUtil.i("SHARE", "title:" + title);
        UMWeb web = getUMWeb(activity, url, title, res, des);
        if (web == null)
            return;
        this.activity = activity;
        if (config == null || config.get() == null) {
            config = new WeakReference<>(new ShareBoardConfig());
            config.get().setTitleVisibility(false);
            config.get().setCancelButtonVisibility(true);
            config.get().setIndicatorVisibility(false);
            config.get().setCancelButtonText("取消");
            config.get().setCancelButtonTextColor(R.color.color_030303);
            config.get().setMenuItemBackgroundShape(R.drawable.dialog_date_select_bg);
            config.get().setShareboardBackgroundColor(Color.WHITE);
            config.get().setMenuItemTextColor(R.color.color_0f63a4);
        }
        new ShareAction(activity).withMedia(web)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ)
                .setCallback(umShareListener).open(config.get());

    }

    /**
     * 分享 QQ
     *
     * @param activity
     * @param url      链接
     * @param title    标题
     * @param img      图片链接
     * @param des      描述
     */
    public void shareQQAction(Activity activity, String url, String title, String img, String des) {
        UMWeb web = getUMWeb(activity, url, title, img, des);
        if (web == null)
            return;
        this.activity = activity;
        new ShareAction(activity).withMedia(web).
                setPlatform(SHARE_MEDIA.QQ)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 分享新浪
     *
     * @param activity
     * @param url      链接
     * @param title    标题
     * @param img      图片链接
     * @param des      描述
     */
    public void shareSinaAction(Activity activity, String url, String title, String img, String des) {
        UMWeb web = getUMWeb(activity, url, title, img, des);
        if (web == null)
            return;
        this.activity = activity;
        new ShareAction(activity).withMedia(web).
                setPlatform(SHARE_MEDIA.SINA)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 分享微信
     *
     * @param activity
     * @param url      链接
     * @param title    标题
     * @param img      图片链接
     * @param des      描述
     */
    public void shareWeiXinAction(Activity activity, String url, String title, String img, String des) {
        UMWeb web = getUMWeb(activity, url, title, img, des);
        if (web == null)
            return;
        this.activity = activity;
        new ShareAction(activity).withMedia(web).
                setPlatform(SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener)
                .share();
    }

    /**
     * 分享 微信朋友圈
     *
     * @param activity
     * @param url      链接
     * @param title    标题
     * @param img      图片链接
     * @param des      描述
     */
    public void shareWeixinCircleAction(Activity activity, String url, String title, String img, String des) {
        UMWeb web = getUMWeb(activity, url, title, img, des);
        if (web == null)
            return;
        this.activity = activity;
        new ShareAction(activity).withMedia(web).
                setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(umShareListener)
                .share();
    }

    private UMWeb getUMWeb(Activity activity, String url, String title, String img, String des) {
        UMImage thumb;
        if (StringUtils.isEmpty(url)) {
            ToastUtils.showShortToast("分享链接地址为空");
            return null;
        }
        if (StringUtils.isEmpty(title)) {
            title = "校园新闻";
        }
        if (StringUtils.isEmpty(img)) {
            thumb = new UMImage(activity, R.mipmap.ic_launcher);
        } else {
            thumb = new UMImage(activity, img);
        }
        if (StringUtils.isEmpty(des)) {
            des = "校园新鲜事早知道";
        }

        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(des);//描述

        return web;
    }


    private UMWeb getUMWeb(Activity activity, String url, String title, int res, String des) {
        UMImage thumb;
        if (StringUtils.isEmpty(url)) {
            ToastUtils.showShortToast("分享链接地址为空");
            return null;
        }
        if (StringUtils.isEmpty(title)) {
            title = "金链汇信";
        }
        if (res == 0) {
            thumb = new UMImage(activity, R.mipmap.ic_launcher);
        } else {
            thumb = new UMImage(activity, res);
        }
        if (StringUtils.isEmpty(des)) {
            des = "金链汇信";
        }

        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(des);//描述

        return web;
    }

    public void setAttributes(Activity context, float alpha) {
        if (context != null) {
            WindowManager.LayoutParams lp = context.getWindow()
                    .getAttributes();
            lp.alpha = alpha;
            context.getWindow().setAttributes(lp);
        }
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            if (platform == SHARE_MEDIA.WEIXIN && !UMShareAPI.get(JlhxApplication.getApplication()).isInstall(activity, SHARE_MEDIA.WEIXIN)) {
                ToastUtils.showShortToast("没有安装微信");
            } else if (platform == SHARE_MEDIA.WEIXIN_CIRCLE && !UMShareAPI.get(JlhxApplication.getApplication()).isInstall(activity, SHARE_MEDIA.WEIXIN)) {
                ToastUtils.showShortToast("没有安装微信");
            } else if (platform == SHARE_MEDIA.SINA && !UMShareAPI.get(JlhxApplication.getApplication()).isInstall(activity, SHARE_MEDIA.SINA)) {
                ToastUtils.showShortToast("没有安装新浪微博");
            } else if (platform == SHARE_MEDIA.QQ && !UMShareAPI.get(JlhxApplication.getApplication()).isInstall(activity, SHARE_MEDIA.QQ)) {
                ToastUtils.showShortToast("没有安装QQ");
            } else {
//                EventBus.getDefault().post(new ShareEvent(GlobalKeyContans.EVENT_KEY_SHARE_ONSTART,platform,null));
            }


            DialogMaker.showProgressDialog(activity, "", true, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                }
            }).setCanceledOnTouchOutside(false);
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtil.d("plat", "platform" + platform);
            DialogMaker.dismissProgressDialog();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (t != null) {
                LogUtil.d("throw", "throw:" + t.getMessage());
            }
            DialogMaker.dismissProgressDialog();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            DialogMaker.dismissProgressDialog();
        }
    };

    public void onDestroy() {
        UMShareAPI.get(JlhxApplication.getApplication()).release();
        activity = null;
        instance = null;
    }

    public void onResume() {
        DialogMaker.dismissProgressDialog();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(JlhxApplication.getApplication()).onActivityResult(requestCode, resultCode, data);
        DialogMaker.dismissProgressDialog();
    }
}
