package com.xinxin.wotplus;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by xinxin on 2016/3/22.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
        // 初始化极光推送sdk
        // JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JPushInterface.setAlias(context, "WOTPlus0.7", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.d("Jpush set alias", String.valueOf(i));
            }
        });
    }

    public static Context getContext() {
        return context;
    }

}
