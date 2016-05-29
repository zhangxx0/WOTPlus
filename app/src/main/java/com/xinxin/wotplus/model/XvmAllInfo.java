package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by xinxin on 2016/5/22.
 * XVM信息汇总
 */
public class XvmAllInfo implements Serializable {


    private XvmMainInfo xvmMainInfo;
    /**
     * tanks.js 坦克信息
     * tankId:TankInfo
     */
    private Map tanks;
    /**
     * 近日数据map
     * 日期：DaylistEntityForRecent
     */
    private Map daymap;

    /**
     * XVM 主页信息展示
     */
    private XvmMainPageVO xvmMainPageVO;



    public XvmMainPageVO getXvmMainPageVO() {
        return xvmMainPageVO;
    }

    public void setXvmMainPageVO(XvmMainPageVO xvmMainPageVO) {
        this.xvmMainPageVO = xvmMainPageVO;
    }

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

