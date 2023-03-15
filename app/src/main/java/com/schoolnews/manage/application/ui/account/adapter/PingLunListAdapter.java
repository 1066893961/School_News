package com.schoolnews.manage.application.ui.account.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.bean.CommonListBean;
import com.schoolnews.manage.application.bean.FeeListBean;
import com.schoolnews.manage.application.utils.StringUtils;

import java.util.List;


public class PingLunListAdapter extends BaseQuickAdapter<CommonListBean, BaseViewHolder> {


    public PingLunListAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public PingLunListAdapter(List data) {
        super(data);
    }

    public PingLunListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CommonListBean item) {
        helper.setText(R.id.name_tv, item.getContent());
        helper.setText(R.id.content_tv, StringUtils.timeFormat(item.getCreateAt()));
    }
}