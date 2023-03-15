package com.schoolnews.manage.application.ui.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.schoolnews.manage.application.R;

/**
 * @Description: 分享弹窗
 * @Author: leo.li
 * @CreateDate: 2019/8/27 10:56
 */
public class DialogForShare extends DialogFragment {
    private OnDialogShareListener onDialogShareListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (activity instanceof OnDialogShareListener) {
                onDialogShareListener = (OnDialogShareListener) activity;
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View view = inflater.inflate(R.layout.dialog_share, container);

        view.findViewById(R.id.weixin_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogShareListener.weixin();
                dismiss();
            }
        });
        view.findViewById(R.id.weibo_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogShareListener.weibo();
                dismiss();
            }
        });
        view.findViewById(R.id.qq_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogShareListener.qq();
                dismiss();
            }
        });
        view.findViewById(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
