package com.xinxin.wotplus.util.mapper;

import com.xinxin.wotplus.model.XvmTankTopAll;
import com.xinxin.wotplus.model.XvmTankTopVO;
import com.xinxin.wotplus.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.functions.Func1;

/**
 * Created by xinxin on 2016/6/21.
 */
public class XvmTankTopSortMapper implements Func1<XvmTankTopAll, XvmTankTopAll> {

    private static XvmTankTopSortMapper INSTANCE = new XvmTankTopSortMapper();

    public XvmTankTopSortMapper() {
    }

    public static XvmTankTopSortMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public XvmTankTopAll call(XvmTankTopAll xvmTankTopAll) {

        // 坦克榜单排序
        Map tankjsmap = xvmTankTopAll.getTankjsmap();
        List<XvmTankTopVO> tankTopVOs = xvmTankTopAll.getTankTopVOs();

        List<XvmTankTopVO> sortedTankTopVOs = new ArrayList<>();

        sortedTankTopVOs = CommonUtil.tankTopSortBy(tankTopVOs, tankjsmap);

        xvmTankTopAll.setTankTopVOs(sortedTankTopVOs);

        return xvmTankTopAll;
    }
}
