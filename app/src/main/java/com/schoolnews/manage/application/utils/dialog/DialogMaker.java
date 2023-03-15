package com.schoolnews.manage.application.utils.dialog;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.lang.ref.WeakReference;

public class DialogMaker {
	private static DialogEasyProgress progressDialog;
	private static WeakReference<Context> context_;

    public static DialogEasyProgress showProgressDialog(@NonNull Context context, String message) {
        return showProgressDialog(context, message, true, null);
    }

	public static DialogEasyProgress showProgressDialog(@NonNull Context context, String message, boolean cancelable) {
		return showProgressDialog(context, message, cancelable, null);
	}

	public static DialogEasyProgress showProgressDialog(@NonNull Context context, String message, boolean canCancelable, OnCancelListener listener) {

		if (progressDialog == null) {
			if(context != null ) {
				context_ = new WeakReference(context);
			}
			progressDialog = new DialogEasyProgress(context_.get(), message);
		} else if (progressDialog.getContext() != context) {
			dismissProgressDialog();
			if(context != null ) {
				context_ = new WeakReference(context);
			}
			progressDialog = new DialogEasyProgress(context_.get(), message);
		}

		progressDialog.setCancelable(canCancelable);
		progressDialog.setOnCancelListener(listener);
		progressDialog.show();
		//progressDialog.startAnim();

		return progressDialog;
	}
	
	public static void dismissProgressDialog() {
		if (null == progressDialog) {
			return;
		}
		if (progressDialog.isShowing()) {
			try {
				//progressDialog.stopAnim();
				progressDialog.dismiss();
				progressDialog = null;
			} catch (Exception e) {
				// maybe we catch IllegalArgumentException here.
			}

		}

	}
	
	public static void setMessage(String message) {
		if (null != progressDialog && progressDialog.isShowing()
				&& !TextUtils.isEmpty(message)) {
			progressDialog.setMessage(message);
		}
	}

	public static void updateLoadingMessage(String message) {
		if (null != progressDialog && progressDialog.isShowing()
				&& !TextUtils.isEmpty(message)) {
			progressDialog.updateLoadingMessage(message);
		}
	}
	
	public static boolean isShowing() {
		return (progressDialog != null && progressDialog.isShowing());
	}
}
