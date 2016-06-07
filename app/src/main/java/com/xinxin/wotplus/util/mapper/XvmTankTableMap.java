package com.xinxin.wotplus.util.mapper;

import android.util.Log;

import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import rx.functions.Func1;

/**
 * Created by xinxin on 2016/6/5.
 * 单车列表的数据转化
 */
public class XvmTankTableMap implements Func1<XvmAllInfo, XvmAllInfo> {

    private static XvmTankTableMap INSTANCE = new XvmTankTableMap();

    public XvmTankTableMap() {
    }

    public static XvmTankTableMap getInstance() {
        return INSTANCE;
    }

    @Override
    public XvmAllInfo call(XvmAllInfo xvmAllInfo) {
        /**
         * 进行数据的转换、排序等操作
         */
        XvmAllInfo reXvmAllInfo = new XvmAllInfo();
        reXvmAllInfo = xvmAllInfo;

        //
        List<XvmMainInfo.TanklistEntity> tanklist = xvmAllInfo.getXvmMainInfo().getTanklist();

        // 单车数据列表
        List<XvmMainInfo.TanklistEntity> xvmTankTables =xvmAllInfo.getTankTables2();
        // tanks.js
        Map tankmap = xvmAllInfo.getTanks();

        tanklist.addAll(xvmTankTables);

        // 单车数据排序
        List<XvmMainInfo.TanklistEntity> sortedTankTables = tanksort(tanklist,tankmap);

        // 对比了一下，排序前后坦克的ID排列没什么变化啊？
        for (XvmMainInfo.TanklistEntity entity : xvmTankTables) {
            Log.d("-", String.valueOf(entity.getId().getVehicleTypeCd()));
        }
        for (XvmMainInfo.TanklistEntity entity2 : sortedTankTables) {
            Log.d("+", String.valueOf(entity2.getId().getVehicleTypeCd()));
        }

        reXvmAllInfo.setTankTables2(sortedTankTables);

        return reXvmAllInfo;
    }

    public static List<XvmMainInfo.TanklistEntity> tanksort(List<XvmMainInfo.TanklistEntity> tanklist, final Map map) {

        // 做一个排序算法，在这里，或者在之后生成的map中按照 “场次"由多到少排序
        // 并不是按照场次这么简单，有个比较复杂的算法；
        java.util.Collections.sort(tanklist, new Comparator<XvmMainInfo.TanklistEntity>() {

            @Override
            public int compare(XvmMainInfo.TanklistEntity t1, XvmMainInfo.TanklistEntity t2) {
                // var tanka=tankDict[a['id']['vehicleTypeCd']];
                TankInfo ti1 = (TankInfo) map.get(t1.getId().getVehicleTypeCd() + "");
                TankInfo ti2 = (TankInfo) map.get(t2.getId().getVehicleTypeCd() + "");

                return (int) ((float)t2.getTotalpower() / getTankModify(ti2) - (float)t1.getTotalpower() / getTankModify(ti1));
            }

        });

        return tanklist;
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


}


















