package com.xinxin.wotplus;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by xinxin on 2016/3/22.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
        Log.d("+++++++++++++++++++++++", "初始化极光推送sdk");
        // 初始化极光推送sdk
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static Context getContext() {
        return context;
    }

}
