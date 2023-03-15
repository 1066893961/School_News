package com.schoolnews.manage.application.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.schoolnews.manage.application.constant.GlobalKeyContans;
import com.schoolnews.manage.application.info.TabLayoutInfo;

import java.util.ArrayList;
import java.util.List;


public class TabDynamicLayoutView extends LinearLayout implements View.OnClickListener {

    public TabDynamicLayoutView(Context context) {
        super(context);
        initView(context);
    }

    public TabDynamicLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabDynamicLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
    }

    public void setTabList(List<TabLayoutInfo> list){
        if(list== null){
            list = new ArrayList<>();
        }
        removeAllViews();
        for (TabLayoutInfo info:list) {
            TabView tabView = new TabView(getContext());

            tabView.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
            tabView.setOrientation(HORIZONTAL);
            tabView.setGravity(Gravity.CENTER);
            tabView.setDefaultData(info.getIconResId(),info.getTextResId());
            tabView.setIndex(info.getIndex());  //设置成对应的index
            tabView.setId(info.getIndex());     //设置id为对应的index
            tabView.setOnClickListener(this);
            addView(tabView);
        }
    }

    public void setDefaultTab(@GlobalKeyContans.TabIndex int index) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if(childAt != null && childAt instanceof TabView){
                TabView v = (TabView) childAt;
                v.setChecked(index == v.getIndex());
            }
        }
        if (mTabListener != null) {
            mTabListener.onTabSelected(index);
        }
    }

    @Override
    public void onClick(View view) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if(childAt != null && childAt instanceof TabView){
                TabView v = (TabView) childAt;
                v.setChecked(view.getId() == v.getId());
            }
        }
        if (mTabListener != null) {
            mTabListener.onTabSelected(view.getId());
        }
    }

    // 回调接口，用于获取tab的选中状态
    private OnTabSelectedListener mTabListener;

    public interface OnTabSelectedListener {
        void onTabSelected(@GlobalKeyContans.TabIndex int index);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        this.mTabListener = listener;
    }
}
