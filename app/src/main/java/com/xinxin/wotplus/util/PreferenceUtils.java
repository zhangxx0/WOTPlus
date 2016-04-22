package com.xinxin.wotplus.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by xinxin on 2016/4/22.
 * Preference工具类，取自NBAPlus开源项目
 */
public class PreferenceUtils {

    /**
     * 获取自定义名字的Preference的值
     *
     * @param context
     * @param customPreName
     * @param key
     * @param defaultVale
     * @return
     * @auther zhang.xx
     * @date 2016年4月23日00:04:32
     */
    public static String getCustomPrefString(Context context, String customPreName, String key, String defaultVale) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(customPreName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultVale);
    }

    /**
     * 向自定义Preference中放值
     *
     * @param context
     * @param customPreName
     * @param key
     * @param defaultVale
     * @return
     */
    public static void putCustomPrefString(Context context, String customPreName, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(customPreName, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static String getPrefString(Context context, String key, final String defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getString(key, defaultValue);
    }

    public static void setPrefString(Context context, final String key, final String value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putString(key, value).commit();
    }

    public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context context, final String key) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).contains(key);
    }

    public static void setPrefBoolean(Context context, final String key, final boolean value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putBoolean(key, value).commit();
    }

    public static void setPrefInt(Context context, final String key, final int value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putInt(key, value).commit();
    }

    public static int getPrefInt(Context context, final String key, final int defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getInt(key, defaultValue);
    }

    public static void setPrefFloat(Context context, final String key, final float value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putFloat(key, value).commit();
    }

    public static float getPrefFloat(Context context, final String key, final float defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getFloat(key, defaultValue);
    }

    public static void setSettingLong(Context context, final String key, final long value) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putLong(key, value).commit();
    }

    public static long getPrefLong(Context context, final String key, final long defaultValue) {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getLong(key, defaultValue);
    }

    public static void clearPreference(Context context, final SharedPreferences p) {
        final SharedPreferences.Editor editor = p.edit();
        editor.clear();
        editor.commit();
    }
}
