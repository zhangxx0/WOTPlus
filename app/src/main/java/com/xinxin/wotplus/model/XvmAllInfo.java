package com.xinxin.wotplus.model;

import java.util.Map;

/**
 * Created by xinxin on 2016/5/22.
 * XVM信息汇总
 */
public class XvmAllInfo {

    private XvmMainInfo xvmMainInfo;
    /**
     * tanks.js 坦克信息
     */
    private Map tanks;
    /**
     * 近日数据map
     */
    private Map daymap;

    public Map getDaymap() {
        return daymap;
    }

    public void setDaymap(Map daymap) {
        this.daymap = daymap;
    }


    public XvmMainInfo getXvmMainInfo() {
        return xvmMainInfo;
    }

    public void setXvmMainInfo(XvmMainInfo xvmMainInfo) {
        this.xvmMainInfo = xvmMainInfo;
    }

    public Map getTanks() {
        return tanks;
    }

    public void setTanks(Map tanks) {
        this.tanks = tanks;
    }
}

