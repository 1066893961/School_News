package com.schoolnews.manage.application.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by fengzheng on 2017/5/9.
 */
public class ParamsUtils {

    public static HashMap<String,String>getParams(){

        HashMap<String,String> params = new HashMap<>();
        return params;
    }
    public static HashMap<String,Object> getObjParams(){

        HashMap<String,Object> params = new HashMap<>();
        return params;
    }
    public static String getJson(Map<String,String> params){
        Gson gson = new Gson();
        String josn = gson.toJson(params);
        LogUtil.i("ParamsJson",josn);
        return josn;
    }
    public static String getObjJson(Map<String,Object> params){
        Gson gson = new Gson();
        String josn = gson.toJson(params);
        LogUtil.i("ParamsJson",josn);
        return josn;
    }
}
