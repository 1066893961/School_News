package com.schoolnews.manage.application.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;



public class CallPhoneManager {

    public static void callPhonePhone(Context context, String phoneNumber){
        //拨打电话
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void autoCallPhone(Context context, String phoneNumber){
        //拨打电话
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
