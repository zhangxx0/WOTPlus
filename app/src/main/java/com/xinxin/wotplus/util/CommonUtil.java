package com.xinxin.wotplus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.VersionVo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.network.Network;

import java.io.UnsupportedEncodingException;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        Observer<VersionVo> versionVoObserver = new Observer<VersionVo>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(view, "检查版本出错！", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(VersionVo versionVo) {
                doCheck(context,versionVo,view);
            }

        };

        Network.getUtilApi()
                .checkVersion(Constant.FIR_APP_ID, Constant.FIR_API_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(versionVoObserver);

    }

    private static void doCheck(Context context,VersionVo versionVo,View view) {

        // 进行版本的比较
        //FIR上当前的versionCode
        int firVersionCode = Integer.parseInt(versionVo.getVersion());
        //FIR上当前的versionName
        String firVersionName = versionVo.getVersionShort();

        // 本地版本Code和name
        int currentVersionCode = getVersionCode(context);
        String currentVersionName = getVersion(context);

        // Log.d("Version", "当前版本:" + currentVersionCode + "FIR上版本：" + firVersionCode);
        // Log.d("Version", "当前版本:" + currentVersionName + "FIR上版本：" + firVersionName);

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

    // 日期的格式化
    public static String formatDate(XvmMainInfo.DaylistEntity.InsertDateEntity dateEntity) {
        return (1900 + dateEntity.getYear()) + "-" + fillZero(dateEntity.getMonth() + 1) + "-"
                + fillZero(dateEntity.getDate());
    }
    // 日期格式填充0
    public static String fillZero(int i) {
        if (i < 10) {
            return "0" + i;
        } else {
            return i + "";
        }
    }

}
