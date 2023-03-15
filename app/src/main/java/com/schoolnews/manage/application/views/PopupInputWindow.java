package com.schoolnews.manage.application.views;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.schoolnews.manage.application.R;
import com.schoolnews.manage.application.utils.StringUtils;
import com.schoolnews.manage.application.utils.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * Created by admin on 16/9/26.
 */
public class PopupInputWindow {
    private PopupWindow mPopWindow = null;
    private Activity mContext = null;
    private EditText mInputTextView;
    private TextView mSendBtn;
    public interface OnInputListener {
        public void inputString(String string);
    }

    public PopupInputWindow(Activity context) {
        mContext = context;
    }

    public void showPopup300(final View parent, final OnInputListener listener) {
        if (mPopWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.popwindow_input300, null);
            mInputTextView = (EditText) view.findViewById(R.id.text_input_et);
            mSendBtn = (TextView) view.findViewById(R.id.submit_tx);
            TextView submitTextView = (TextView) view.findViewById(R.id.submit_tx);
            submitTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = mInputTextView.getText().toString();
                    if (StringUtils.isEmpty(str)) {
                        ToastUtils.showShortToast("输入内容不能为空");
                        return;
                    }
                    if (listener != null) {
                        listener.inputString(str);
                        dismiss();
                    }
                }
            });
            // 创建一个PopuWidow对象
            mPopWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, 100, true);
            mPopWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            mPopWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            // mPopWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,true);
        }
//popupwindow弹出时的动画		popWindow.setAnimationStyle(R.style.popupWindowAnimation);
        // 使其聚集 ，要想监听菜单里控件的事件就必须要调用此方法
        mInputTextView.setText("");
        mInputTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = mInputTextView.getText().toString();
                /*
                String str = stringFilter(editable); //过滤特殊字符
                if (!editable.equals(str)) {
                    mInputTextView.setText(str);
                }
                LogUtil.i("text","输入了:"+mInputTextView.getText().toString().length());*/

                if (StringUtils.isEmpty(editable)) {
                    mSendBtn.setEnabled(false);
                    mSendBtn.setTextColor(mContext.getResources().getColor(R.color.color_999999));
                    return;
                }else {
                    mSendBtn.setTextColor(mContext.getResources().getColor(R.color.color_1CA998));
                    mSendBtn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        mPopWindow.setFocusable(true);
        // 设置允许在外点击消失
        mPopWindow.setOutsideTouchable(false);
        // 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        // mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        ColorDrawable dw = new ColorDrawable(mContext.getResources().getColor(R.color.wind_back_bg));
        mPopWindow.setBackgroundDrawable(dw);
        //软键盘不会挡着popupwindow
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // mPopWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        //设置菜单显示的位置
        mPopWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        backgroundAlpha(0.7f);
        popupInputMethodWindow(mInputTextView);
        //监听菜单的关闭事件
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                downInputKeyBoard(mInputTextView);
                backgroundAlpha(1f);
            }
        });
        //监听触屏事件
        mPopWindow.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                return false;
            }
        });
    }
    public static String stringFilter(String str)throws PatternSyntaxException{
        String regEx = "[/\\:*?<>|\"\n\t]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }
    public void setDataEmpty(){
        mInputTextView.setText("");
    }
    public void dismiss() {
        mPopWindow.dismiss();
    }

    private void popupInputMethodWindow(View handlert) {
        handlert.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Service.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 0);
    }

    private void downInputKeyBoard(final View handler) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(handler.getApplicationWindowToken(), 0);
                }
            }
        }, 0);

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mContext.getWindow().setAttributes(lp);
    }
}
