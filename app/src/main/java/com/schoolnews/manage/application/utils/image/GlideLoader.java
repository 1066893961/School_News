package com.schoolnews.manage.application.utils.image;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.schoolnews.manage.application.utils.DipUtils;
import com.schoolnews.manage.application.utils.StringUtils;


/**
 * Description: ImageLoader
 * Creator: yxc
 * date: 2016/9/21 9:53
 */
public class GlideLoader {

    public static void load(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!StringUtils.isEmpty(url)) {
            Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void load(Context context, int id, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        Glide.with(context).load(id).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    public static void loadRound(Context context, String url, ImageView iv, int holderResouce, int round) {    //，使用占位图,圆角

        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .transform(new GlideRoundTransform(context, round))
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }

    }

    /**
     * 资讯列表图片加载无圆角
     *
     * @param context
     * @param url
     * @param iv
     * @param holderResouce
     * @param round
     */
    public static void loadNoRound(Context context, String url, ImageView iv, int holderResouce, int round) {    //，使用占位图,圆角

        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .transform(new GlideRoundTransform(context, 0))
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }

    }

    public static void loadRoundCenterCrop(Context context, String url, ImageView iv, int holderResouce, int round) {    //，使用占位图,圆角

        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()//TODO 修改首次加载图片无圆角效果问题
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, round))
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }
    }

    public static void loadTwoRoundCenterCrop(Context context, String url, ImageView iv, int holderResouce, int round) {    //，使用占位图,圆角
        CornerTransform transformation = new CornerTransform(context, DipUtils.dp2px(context, round));
        //只是绘制左上角和右上角圆角
        transformation.setExceptCorner(false, false, true, true);
        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()//TODO 修改首次加载图片无圆角效果问题
                    .transform(new CenterCrop(context), transformation)
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }
    }

    public static void loadCircle(Context context, String url, ImageView iv, int holderResouce) {    //，使用占位图,圆行
        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .transform(new GlideCircleTransform(context))
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }


    }

    public static void load(Context context, String url, ImageView iv, int holderResouce) {    //，使用占位图
        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }

    }

    public static void loadNoCenter(Context context, String url, ImageView iv, int holderResouce) {    //，使用占位图
        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }

    }

    public static void loadNoCenterBrand(Context context, String url, ImageView iv, int holderResouce) {    //，使用占位图
        if (!StringUtils.isEmpty(url)) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .placeholder(holderResouce)
                    .error(holderResouce)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv);
        } else {
            iv.setImageResource(holderResouce);
        }

    }

    public static void load(Activity activity, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!activity.isDestroyed() && !StringUtils.isEmpty(url)) {
            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void load(Activity activity, int id, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(id).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void loadAll(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
        Glide.with(context).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    public static void loadAll(Activity activity, String url, ImageView iv) {    //不缓存，全部从网络加载
        if (!activity.isDestroyed() && !StringUtils.isEmpty(url)) {
            Glide.with(activity).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
        }
    }
}
