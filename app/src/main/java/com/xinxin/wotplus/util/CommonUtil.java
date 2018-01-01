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
import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.VersionVo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.model.XvmTankTopVO;
import com.xinxin.wotplus.network.Network;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
                doCheck(context, versionVo, view);
            }

        };

        Network.getUtilApi()
                .checkVersion(Constant.FIR_APP_ID, Constant.FIR_API_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(versionVoObserver);

    }

    private static void doCheck(Context context, VersionVo versionVo, View view) {

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

    public static String formatDate2(XvmMainInfo.TanklistEntity.UpdateTimeEntity dateEntity) {
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

    // 获取权重
    public static double getTankWeight(TankInfo tankInfo) {
        int[] weightlist = new int[]{0, 20, 30, 50, 80, 120, 180, 270, 400, 600, 900};
        double weight = weightlist[tankInfo.getLevel()];
        weight *= getTankModify(tankInfo);
        return weight;
    }

    /**
     * 排序时使用的一个方法
     *
     * @param ti
     */
    public static double getTankModify(TankInfo ti) {

        if (ti.getEntype().toLowerCase() == "spg") {
            return 0.8;
        } else if (ti.getEntype().toLowerCase() == "lighttank") {
            if (ti.getLevel() == 7) {
                return 1.15;
            } else if (ti.getLevel() == 8) {
                return 1.15;
            }
        } else if (ti.getEntype().toLowerCase() == "heavytank") {
            if (ti.getLevel() == 8 || ti.getLevel() == 9) {
                return 1.05;
            } else if (ti.getLevel() == 10) {
                return 1.05;
            }
        } else if (ti.getEntype().toLowerCase() == "at-spg") {
            if (ti.getLevel() >= 8) {
                return 1.1;
            }
        } else if (ti.getEntype().toLowerCase() == "mediumtank") {
            if (ti.getLevel() >= 9) {
                return 1.05;
            }
        }
        return 1;
    }

    /**
     * * 两个Double数相加 *
     *
     * @param v1 *
     * @param v2 *
     * @return Double
     */
    public static Double addDouble(Double v1, Double v2) {
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return new Double(b1.add(b2).doubleValue());
    }

    /**
     * 根据不同的比率返回不同的字体颜色
     *
     * @param wr
     * @return
     */
    public static int getWRClass(float wr) {
        if (wr >= 60)
            return R.color.wrclass_ss;
        if (wr >= 56)
            return R.color.wrclass_s;
        if (wr >= 54)
            return R.color.wrclass_a;
        if (wr >= 51)
            return R.color.wrclass_b;
        if (wr >= 48)
            return R.color.wrclass_c;
        if (wr >= 46)
            return R.color.wrclass_d;

        return R.color.wrclass_e;

    }

    /**
     * 坦克类型
     *
     * @param type
     * @return
     */
    public static String getTankType(String type) {
        switch (type) {
            case "lightTank":
                return "LT";
            case "mediumTank":
                return "MT";
            case "heavyTank":
                return "HT";
            case "AT-SPG":
                return "TD";
            case "SPG":
                return "SPG";
            default:
                return type;
        }
    }

    /**
     * 坦克国家
     *
     * @param country
     * @return
     */
    public static String getTankCountry(String country) {
        switch (country) {
            case "ussr":
                return "苏联";
            case "usa":
                return "美国";
            case "germany":
                return "德国";
            case "uk":
                return "英国";
            case "china":
                return "中国";
            case "japan":
                return "日本";
            case "czech":
                return "捷克";
            case "france":
                return "法国";
            default:
                return country;
        }
    }

    /**
     * 获取坦克国旗图片路径
     *
     * @param country
     * @return
     */
    public static String getTankCountryIcon(String country) {
        switch (country) {
            case "ussr":
                return "http://rank.kongzhong.com/Data/icons/nations/dussr.png";
            case "usa":
                return "http://rank.kongzhong.com/Data/icons/nations/dusa.png";
            case "germany":
                return "http://rank.kongzhong.com/Data/icons/nations/dgermany.png";
            case "uk":
                return "http://rank.kongzhong.com/Data/icons/nations/duk.png";
            case "china":
                return "http://rank.kongzhong.com/Data/icons/nations/dchina.png";
            case "japan":
                return "http://rank.kongzhong.com/Data/icons/nations/djapan.png";
            case "czech":
                return "http://rank.kongzhong.com/Data/icons/nations/dczech.png";
            case "france":
                return "http://rank.kongzhong.com/Data/icons/nations/dfrance.png";
            default:
                return "http://rank.kongzhong.com/Data/icons/nations/dchina.png";
        }
    }

    /**
     * 获取坦克图标
     * @param country
     * @param name
     * @return http://182.18.61.50/Data/icons/vehicle/france-Bat_Chatillon25t.png
     */
    public static String getTankIcon(String country, String name) {
        String base = "http://rank.kongzhong.com/Data/icons/vehicle/";
        return base + country + "-" + name + ".png";
    }

    /**
     * 获取坦克等级图标
     * @param level
     * @return
     */
    public static String getTankLevelIcon(String level) {
        switch (level) {
            case "level1":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_1.png";
            case "level2":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_2.png";
            case "level3":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_3.png";
            case "level4":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_4.png";
            case "level5":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_5.png";
            case "level6":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_6.png";
            case "level7":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_7.png";
            case "level8":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_8.png";
            case "level9":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_9.png";
            case "level10":
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_10.png";

            default:
                return "http://rank.kongzhong.com/Data/icons/filters/levels/level_10.png";
        }
    }

    /**
     * 获取坦克类型图标
     * @param type
     * @return
     */
    public static String getTankTypeIcon(String type) {
        switch (type) {
            case "lightTank":
                return "http://rank.kongzhong.com/Data/icons/filters/tanks/lightTank.png";
            case "mediumTank":
                return "http://rank.kongzhong.com/Data/icons/filters/tanks/mediumTank.png";
            case "heavyTank":
                return "http://rank.kongzhong.com/Data/icons/filters/tanks/heavyTank.png";
            case "AT-SPG":
                return "http://rank.kongzhong.com/Data/icons/filters/tanks/AT-SPG.png";
            case "SPG":
                return "http://rank.kongzhong.com/Data/icons/filters/tanks/SPG.png";
            default:
                return "http://rank.kongzhong.com/Data/icons/filters/tanks/heavyTank.png";
        }

    }

    /**
     * 活跃坦克排序方法 由js方法转换
     * <p/>
     * 使用的是js的sort方法，需要转换为java方法
     * <p/>
     * tankinfo['list'].sort(tanksort);
     * <p/>
     * function tanksort(a,b)
     * <p/>
     * getTankModify(tank)
     */
    public static List<XvmMainInfo.TanklistEntity> tanksort(List<XvmMainInfo.TanklistEntity> tanklist, final Map map) {

        // 做一个排序算法，在这里，或者在之后生成的map中按照 “场次"由多到少排序
        // 并不是按照场次这么简单，有个比较复杂的算法；
        java.util.Collections.sort(tanklist, new Comparator<XvmMainInfo.TanklistEntity>() {

            @Override
            public int compare(XvmMainInfo.TanklistEntity t1, XvmMainInfo.TanklistEntity t2) {
                // var tanka=tankDict[a['id']['vehicleTypeCd']];
                TankInfo ti1 = (TankInfo) map.get(t1.getId().getVehicleTypeCd() + "");
                TankInfo ti2 = (TankInfo) map.get(t2.getId().getVehicleTypeCd() + "");

                return (int) (t2.getTotalpower() / CommonUtil.getTankModify(ti2) - t1.getTotalpower() / CommonUtil.getTankModify(ti1));
            }

        });

        return tanklist;
    }

    /**
     * 按照场次和更新日期排序
     * <p/>
     * 暂时只按照场次
     * 2016年6月11日15:18:55
     */
    public static List<XvmMainInfo.TanklistEntity> tanksortByBattles(List<XvmMainInfo.TanklistEntity> tanklist) {

        Collections.sort(tanklist, new Comparator<XvmMainInfo.TanklistEntity>() {
            @Override
            public int compare(XvmMainInfo.TanklistEntity t1, XvmMainInfo.TanklistEntity t2) {

                int i = t2.getBattles() - t1.getBattles();
                // 下面的这个没吊用；
//                if (i==0) {
//                    int j = (int) (t2.getUpdateTime().getTime() - t1.getUpdateTime().getTime());
//                    return  j;
//                }

                return i;
            }
        });

        return tanklist;
    }

    /**
     * 按照更新日期来排序
     *
     * @param tanklist
     * @return
     */
    public static List<XvmMainInfo.TanklistEntity> tanksortByDate(List<XvmMainInfo.TanklistEntity> tanklist) {

        Collections.sort(tanklist, new Comparator<XvmMainInfo.TanklistEntity>() {
            @Override
            public int compare(XvmMainInfo.TanklistEntity t1, XvmMainInfo.TanklistEntity t2) {

                String date1 = formatDate2(t1.getUpdateTime());
                String date2 = formatDate2(t2.getUpdateTime());

                int j = date2.compareTo(date1);

                return j;
            }
        });

        return tanklist;
    }

    /**
     * 战车战斗明细中日战斗数据排序
     * @param daylist
     * @return
     */
    public static List<XvmMainInfo.DaylistEntity> dayListSortByDate(List<XvmMainInfo.DaylistEntity> daylist) {

        Collections.sort(daylist, new Comparator<XvmMainInfo.DaylistEntity>() {
            @Override
            public int compare(XvmMainInfo.DaylistEntity d1, XvmMainInfo.DaylistEntity d2) {
                String date1 = formatDate(d1.getInsert_date());
                String date2 = formatDate(d2.getInsert_date());
                int j = date2.compareTo(date1);
                return j;
            }
        });

        return daylist;
    }

    /**
     * 坦克榜单排序
     *
     * @param tankTopVOs
     * @return
     */
    public static List<XvmTankTopVO> tankTopSortBy(List<XvmTankTopVO> tankTopVOs, final Map map) {

        Collections.sort(tankTopVOs, new Comparator<XvmTankTopVO>() {
            @Override
            public int compare(XvmTankTopVO lhs, XvmTankTopVO rhs) {

                TankInfo ti1 = (TankInfo) map.get(lhs.getTankid() + "");
                TankInfo ti2 = (TankInfo) map.get(rhs.getTankid() + "");
                if (ti1 != null && ti2 != null) {
                    if (ti2.getLevel() > ti1.getLevel()) {
                        return 1;
                    }
                    if (ti2.getLevel() == ti1.getLevel()){
                        return 0;
                    }
                    return -1;
                }
                return -1;
            }
        });

        return tankTopVOs;
    }

}
