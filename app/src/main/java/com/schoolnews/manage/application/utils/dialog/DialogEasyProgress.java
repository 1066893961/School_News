package com.schoolnews.manage.application.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;

import com.schoolnews.manage.application.R;


/**
 * 
 * 一个半透明窗口,包含一个Progressbar 和 Message部分. 其中Message部分可选. 可单独使用,也可以使用
 * {@link DialogMaker} 进行相关窗口显示.
 * 
 * @author Qijun
 * 
 */
public class DialogEasyProgress extends Dialog {

	private int mLayoutId;
	//private FlipImageView imageView;
	//private AnimationDrawable animationDrawable;
	public DialogEasyProgress(Context context, int style, int layout) {
		super(context, style);
		LayoutParams Params = getWindow().getAttributes();
		Params.width = LayoutParams.FILL_PARENT;
		Params.height = LayoutParams.FILL_PARENT;
		getWindow().setAttributes(Params);
		mLayoutId = layout;
	}


	public DialogEasyProgress(Context context, String msg) {
		this(context, R.style.easy_dialog_style, R.layout.dialog_easy_progress);
		setMessage(msg);
	}


	public void setMessage(String msg) {
	}

	public void updateLoadingMessage(String msg) {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(mLayoutId);
		//imageView = (FlipImageView) findViewById(R.id.iv_progress_dialog);
	}


//	public void startAnim()
//	{
//		if(imageView !=null )
//		{
//			imageView.startFlid();
//		}
//	}
//
//	public void stopAnim()
//	{
//		if (imageView != null) {
//			imageView.stopFlide();
//		}
//	}
}