package com.xinxin.wotplus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.VersionVo;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by xinxin on 2016/3/31.
 * 普通工具类
 */
public class CommonUtil {

    /**
     * utf编码
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */

    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return context.getString(R.string.can_not_find_version_name);
        }
    }

    /**
     * @param context
     * @return 版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 检查版本更新
     *
     * @param context
     */
    public static void checkVersion(final Context context, final View view) {

        String versionUrl = Constant.FIR_VERSION_BASE + Constant.FIR_APP_ID + "?api_token=" + Constant.FIR_API_TOKEN;

        RequestQueue mQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(versionUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        VersionVo versionVo = gson.fromJson(response.toString(), VersionVo.class);

                        // 进行版本的比较
                        //FIR上当前的versionCode
                        int firVersionCode = Integer.parseInt(versionVo.getVersion());
                        //FIR上当前的versionName
                        String firVersionName = versionVo.getVersionShort();

                        // 本地版本Code和name
                        int currentVersionCode = getVersionCode(context);
                        String currentVersionName = getVersion(context);

                        Log.d("Version", "当前版本:" + currentVersionCode + "FIR上版本：" + firVersionCode);
                        Log.d("Version", "当前版本:" + currentVersionName + "FIR上版本：" + firVersionName);

                        if (firVersionCode > currentVersionCode) {

                            //需要更新
                            showUpdateDialog(versionVo, context);
                        } else if (firVersionCode == currentVersionCode) {

                            //如果本地app的versionCode与FIR上的app的versionCode一致，则需要判断versionName.
                            if (!currentVersionName.equals(firVersionName)) {
                                showUpdateDialog(versionVo, context);
                            } else {
                                Snackbar.make(view, "已经是最新版本", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Snackbar.make(view, "已经是最新版本", Snackbar.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);

    }

    public static void showUpdateDialog(final VersionVo versionVo, final Context context) {
        String title = "发现新版：" + versionVo.getVersionShort() + " 版本号：" + versionVo.getVersion();

        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(versionVo.getChangelog())
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse(versionVo.getUpdate_url());
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(uri);
                        context.startActivity(intent);
                    }
                }).show();

    }

}
