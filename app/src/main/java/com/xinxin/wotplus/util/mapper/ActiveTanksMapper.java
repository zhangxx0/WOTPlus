package com.xinxin.wotplus.util.mapper;

import com.xinxin.wotplus.model.XvmActiveTanks;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.util.CommonUtil;

import java.util.List;
import java.util.Map;

import rx.functions.Func1;

/**
 * Created by xinxin on 2016/6/22.
 * 活跃坦克列表数据处理
 */
public class ActiveTanksMapper implements Func1<XvmActiveTanks,XvmActiveTanks> {

    private static ActiveTanksMapper INSTANCE = new ActiveTanksMapper();

    public ActiveTanksMapper() {
    }

    public static ActiveTanksMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public XvmActiveTanks call(XvmActiveTanks xvmActiveTanks) {

        List<XvmMainInfo.TanklistEntity> tanklistEntities = xvmActiveTanks.getTanklistEntities();
        Map tankjs = xvmActiveTanks.getTanks();

        // 主要进行一个排序
        List<XvmMainInfo.TanklistEntity> sortedList = CommonUtil.tanksort(tanklistEntities, tankjs);

        xvmActiveTanks.setTanklistEntities(sortedList);

        return xvmActiveTanks;
    }
}
