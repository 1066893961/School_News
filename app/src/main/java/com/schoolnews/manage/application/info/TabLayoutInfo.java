package com.schoolnews.manage.application.info;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.schoolnews.manage.application.constant.GlobalKeyContans;


public class TabLayoutInfo {
    private @DrawableRes
    int iconResId;
    private @StringRes
    int textResId;
    private @GlobalKeyContans.TabIndex
    int index; //索引

    public TabLayoutInfo(@DrawableRes int iconResId, @StringRes int textResId, @GlobalKeyContans.TabIndex int index) {
        this.iconResId = iconResId;
        this.textResId = textResId;
        this.index = index;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getTextResId() {
        return textResId;
    }

    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    public @GlobalKeyContans.TabIndex int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
