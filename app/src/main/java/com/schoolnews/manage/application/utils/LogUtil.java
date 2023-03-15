package com.schoolnews.manage.application.utils;
/**
 * Created by lwz on 2018/4/24.
 */
public class LogUtil {
	private static boolean is_show = true;
    public static final void init(boolean is_show) {
		LogUtil.is_show = is_show;
    }

	public static final void init(String logFile, int level) {
		LogImpl.init(logFile, level);
	}

	public static final void v(String tag, String msg) {
		if(is_show)
		LogImpl.v(tag, buildMessage(msg));
	}

	public static final void v(String tag, String msg, Throwable thr) {
		if(is_show)
		LogImpl.v(tag, buildMessage(msg), thr);
	}

	public static final void d(String tag, String msg) {
		if(is_show)
		LogImpl.d(tag, buildMessage(msg));
	}

	public static final void d(String tag, String msg, Throwable thr) {
		if(is_show)
		LogImpl.d(tag, buildMessage(msg), thr);
	}

	public static final void i(String tag, String msg) {
		if(is_show)
		LogImpl.i(tag, buildMessage(msg));
	}

	public static final void i(String tag, String msg, Throwable thr) {
		if(is_show)
		LogImpl.i(tag, buildMessage(msg), thr);
	}

	public static final void w(String tag, String msg) {
		if(is_show)
		LogImpl.w(tag, buildMessage(msg));
	}

	public static final void w(String tag, String msg, Throwable thr) {
		if(is_show)
		LogImpl.w(tag, buildMessage(msg), thr);
	}

	public static final void w(String tag, Throwable thr) {
		if(is_show)
		LogImpl.w(tag, buildMessage(""), thr);
	}

	public static final void e(String tag, String msg) {
		if(is_show)
		LogImpl.e(tag, buildMessage(msg));
	}

	public static final void e(String tag, String msg, Throwable thr) {
		if(is_show)
		LogImpl.e(tag, buildMessage(msg), thr);
	}
	
	public static final void ui(String msg) {
		if(is_show)
		LogImpl.i("ui", buildMessage(msg));
	}

	public static final void res(String msg) {
		if(is_show)
		LogImpl.i("RES", buildMessage(msg));
	}

	public static final void audio(String msg) {
		if(is_show)
		LogImpl.i("AudioRecorder", buildMessage(msg));
	}

	public static String getLogFileName(String cat) {
		return LogImpl.getLogFileName(cat);
	}

	private static String buildMessage(String msg) {
		return msg;
	}
}
