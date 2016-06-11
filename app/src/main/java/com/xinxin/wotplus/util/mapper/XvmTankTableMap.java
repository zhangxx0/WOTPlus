package com.xinxin.wotplus.util.mapper;

import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.util.CommonUtil;

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
        List<XvmMainInfo.TanklistEntity> xvmTankTables =xvmAllInfo.getTankTables();
        // tanks.js
        Map tankmap = xvmAllInfo.getTanks();

        tanklist.addAll(xvmTankTables);

        // 单车数据排序
        List<XvmMainInfo.TanklistEntity> sortedTankTables = CommonUtil.tanksort(tanklist,tankmap);

        // 排序没有错误，只是之后table又进行了排序 sortList : [ [16,1],[5,1] ]

        // 这个sortedTankTables可以作为活跃列表的数据源
        // 再做一个排序，按照场次、更新时间两个条件


        List<XvmMainInfo.TanklistEntity> sortedTankTablesByBattlesAndDate = CommonUtil.tanksortByBattles(tanklist);
        List<XvmMainInfo.TanklistEntity> sortedTankTablesByBattlesAndDateFinal = CommonUtil.tanksortByDate(tanklist);

        reXvmAllInfo.setTankTablesForActive(sortedTankTables);
        reXvmAllInfo.setTankTables(sortedTankTablesByBattlesAndDateFinal);


        return reXvmAllInfo;
    }




}


















