package com.xinxin.wotplus.util.mapper;

import com.xinxin.wotplus.model.XvmAllInfo;

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



        return reXvmAllInfo;
    }
}
