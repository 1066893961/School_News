package com.schoolnews.manage.application.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolnews.manage.application.R;


/**
 * CREATE BY ZYK ON 2016/12/7.
 * param：从相册或拍照弹窗
 */

public class ActionSheetView {


    private String mFirstContent;

    private String mSecondContent;

    private String mThirdContent;

    private LinearLayout mLayout;

    private TextView mContent;
    private TextView mContentTop;

    private TextView mCancel;
    private Context mContext;
    private View mSingleLine;

    public final static int CODE_BUTTON_TOP = 0;
    public final static int CODE_BUTTON_CONTENT = 1;
    public final static int CODE_BUTTON_CANCE = 2;

    public interface OnActionSheetSelected {
        void onClick(int whichButton);
    }

    private ActionSheetView(Context context, String firstContent,
                            String secondContent, String thirdContent) {
        this.mFirstContent = firstContent;
        this.mSecondContent = secondContent;
        this.mThirdContent = thirdContent;
        this.mContext = context;
        initView();
        if (TextUtils.isEmpty(mFirstContent)) {
            mContentTop.setVisibility(View.GONE);
            mSingleLine.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(mSecondContent)) {
            mSingleLine.setVisibility(View.GONE);
            mContent.setVisibility(View.GONE);
        }
    }

    public static ActionSheetView getInstance (Context context) {
        return new ActionSheetView(context, null, null, null);
    }

    public static ActionSheetView getInstance (Context context, String firstContent,
                                               String secondContent, String thirdContent) {
        return new ActionSheetView(context, firstContent, secondContent, thirdContent);
    }

    private void initView () {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (LinearLayout) inflater.inflate(R.layout.dialog_photo, null);
        mLayout.setMinimumWidth(10000);
        mContent = (TextView) mLayout.findViewById(R.id.content);
        mContentTop = (TextView) mLayout.findViewById(R.id.content_top);
        mCancel = (TextView) mLayout.findViewById(R.id.dialog_cancel);
        mSingleLine = mLayout.findViewById(R.id.view_line);
        setText();
    }

    private void setText () {
        if (!TextUtils.isEmpty(mFirstContent)) {
            mContentTop.setText(mFirstContent);
        }
        if (!TextUtils.isEmpty(mFirstContent)) {
            mContent.setText(mSecondContent);
        }
        if (!TextUtils.isEmpty(mSecondContent)) {
            mCancel.setText(mThirdContent);
        }

    }

    private void setListener (final OnActionSheetSelected actionSheetSelected,
                              final Dialog dlg) {

        mContent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                actionSheetSelected.onClick(CODE_BUTTON_CONTENT);
                dlg.dismiss();
            }
        });

        mContentTop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                actionSheetSelected.onClick(CODE_BUTTON_TOP);
                dlg.dismiss();
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {
                actionSheetSelected.onClick(CODE_BUTTON_CANCE);
                dlg.dismiss();
            }
        });

    }
    public Dialog showSheet (OnActionSheetSelected actionSheetSelected,
                             DialogInterface.OnCancelListener cancelListener) {
        final Dialog dlg = new Dialog(mContext, R.style.ActionSheet);
        setListener(actionSheetSelected, dlg);
        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = -1000;
        lp.gravity = Gravity.BOTTOM;
        dlg.onWindowAttributesChanged(lp);
        dlg.setCanceledOnTouchOutside(true);
        if (cancelListener != null)
            dlg.setOnCancelListener(cancelListener);
        dlg.setContentView(mLayout);
        dlg.show();
        return dlg;
    }
}
