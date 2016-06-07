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

        // 单车数据列表
        List<XvmMainInfo.TanklistEntity> xvmTankTables =xvmAllInfo.getTankTables2();
        // tanks.js
        Map tankmap = xvmAllInfo.getTanks();

        // 单车数据排序
        List<XvmMainInfo.TanklistEntity> sortedTankTables = CommonUtil.tanksort(xvmTankTables,tankmap);

        // 对比了一下，排序前后坦克的ID排列没什么变化啊？

        reXvmAllInfo.setTankTables2(sortedTankTables);

        return reXvmAllInfo;
    }


}


















