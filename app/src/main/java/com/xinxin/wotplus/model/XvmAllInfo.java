package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.List;
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

    /**
     * 单车数据列表
     */
    private List<XvmTankTable> tankTables;
    /**
     * 单车数据列表2
     * 直接使用 TanklistEntity
     */
    private List<XvmMainInfo.TanklistEntity> tankTables2;


    public List<XvmMainInfo.TanklistEntity> getTankTables2() {
        return tankTables2;
    }

    public void setTankTables2(List<XvmMainInfo.TanklistEntity> tankTables2) {
        this.tankTables2 = tankTables2;
    }

    public List<XvmTankTable> getTankTables() {
        return tankTables;
    }

    public void setTankTables(List<XvmTankTable> tankTables) {
        this.tankTables = tankTables;
    }


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

