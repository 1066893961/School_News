package com.schoolnews.manage.application.ui.home.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.bean.CommonListBean;
import com.schoolnews.manage.application.utils.StringUtils;
import com.schoolnews.manage.application.utils.image.GlideLoader;

import java.util.List;

/**
 * @author leo.li
 * @description:
 * @date :2019/7/24 9:48
 */
public class SchoolNewsAdapter extends BaseQuickAdapter<CommonListBean, BaseViewHolder> {

    public SchoolNewsAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public SchoolNewsAdapter(List data) {
        super(data);
    }

    public SchoolNewsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CommonListBean item) {
        if (item.getUrl().contains(".jpg") || item.getUrl().contains(".png")
                || item.getUrl().contains(".svg") || item.getUrl().contains(".jpeg")) {
            GlideLoader.loadNoCenter(mContext, item.getUrl(), (ImageView) helper.getView(R.id.title_iv), R.mipmap.bank);
        } else {
            ((ImageView) helper.getView(R.id.title_iv)).setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_video));
        }

        helper.setText(R.id.title_tv, item.getTitle());
        helper.setText(R.id.content_tv, item.getContent());
        helper.setText(R.id.time_tv, StringUtils.timeFormat(item.getCreateAt()));

    }
}