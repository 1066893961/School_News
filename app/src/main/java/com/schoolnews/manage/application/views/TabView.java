package com.schoolnews.manage.application.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.constant.GlobalKeyContans;


public class TabView extends LinearLayout {
    private ImageView iconIv;
    private TextView textTv/*,markNum*/;
    private @GlobalKeyContans.TabIndex int index;
    public TabView(Context context) {
        super(context);
        initView(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.view_tab, this);
        iconIv = (ImageView) findViewById(R.id.iv);
        textTv = (TextView) findViewById(R.id.tv_text);
//        markNum = (TextView) findViewById(R.id.tv_mark);
    }
    /**
     * 设置默认值
     * @param iconResId
     * @param textResId
     * @return
     */
    public TabView setDefaultData(@DrawableRes int iconResId, @StringRes int textResId){
        setIcon(iconResId);
        setText(textResId);
        return this;
    }

    /**
     * 设置默认值
     * @param iconResId
     * @param text
     * @return
     */
    public TabView setDefaultData(@DrawableRes int iconResId, CharSequence text){
        setIcon(iconResId);
        setText(text);
        return this;
    }

    /**
     * 设置默认值
     * @param drawable
     * @param textResId
     * @return
     */
    public TabView setDefaultData(Drawable drawable, @StringRes int textResId){
        setIcon(drawable);
        setText(textResId);
        return this;
    }

    /**
     * 设置默认值
     * @param drawable
     * @param text
     * @return
     */
    public TabView setDefaultData(Drawable drawable , CharSequence text){
        setIcon(drawable);
        setText(text);
        return this;
    }
    /**
     * 设置默认值
     * @param iconResId
     * @param textResId
     * @param markStr
     * @return
     */
    public TabView setDefaultData(@DrawableRes int iconResId, @StringRes int textResId, String markStr){
        setIcon(iconResId);
        setText(textResId);
//        setMarkNum(markStr);
        return this;
    }

    /**
     * 设置默认值
     * @param iconResId
     * @param text
     * @param markStr
     * @return
     */
    public TabView setDefaultData(@DrawableRes int iconResId, CharSequence text, String markStr){
        setIcon(iconResId);
        setText(text);
//        setMarkNum(markStr);
        return this;
    }
    /**
     * 设置图片
     * @param resId
     * @return
     */
    public TabView setIcon(int resId){
        iconIv.setImageResource(resId);
        return this;
    }

    /**
     * 设置图片
     * @param drawable
     * @return
     */
    public TabView setIcon(Drawable drawable){
        iconIv.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置下方文字
     * @param resId
     * @return
     */
    public TabView setText(@StringRes int resId){
        textTv.setText(resId);

        return this;
    }

    /**
     * 设置下方文字
     * @param text
     * @return
     */
    public TabView setText(CharSequence text){
        textTv.setText(text);
        textTv.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        return this;
    }

    public void setChecked(boolean isChecked){
//        textCtv.setChecked(isChecked);
        textTv.setSelected(isChecked);
        iconIv.setSelected(isChecked);
    }
    public TabView setIconVisiable(int visibility){
//        dotIv.setVisibility(visibility);
        return this;
    }

    public void setIndex(@GlobalKeyContans.TabIndex int index) {
        this.index = index;
    }

    public @GlobalKeyContans.TabIndex int getIndex() {
        return index;
    }
//    /**
//     * 设置右上角数字角标
//     * @param str
//     * @return
//     */
//    public TabView setMarkNum(String str){
//        int num = 0;
//        try {
//            num = Integer.parseInt(str);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//
//        return setMarkNum(num);
//    }
//    /**
//     * 设置右上角数字角标
//     * @param num
//     * @return
//     */
//    public TabView setMarkNum(int num){
//        if(num>0){
//            if (num > 99) {
//                markNum.setText("99+");
//            }else{
//                markNum.setText(num+"");
//            }
//            markNum.setVisibility(View.VISIBLE);
//        }else {
//            markNum.setText(null);
//            markNum.setVisibility(View.GONE);
//        }
//        return this;
//    }
}
