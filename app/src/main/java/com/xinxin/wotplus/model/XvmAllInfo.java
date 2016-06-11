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
//    private List<XvmTankTable> tankTables;
    /**
     * 单车数据列表
     *
     * 直接使用 TanklistEntity
     */
    private List<XvmMainInfo.TanklistEntity> tankTables;
    /**
     * 活跃坦克列表数据源
     *
     */
    private List<XvmMainInfo.TanklistEntity> tankTablesForActive;





    public List<XvmMainInfo.TanklistEntity> getTankTables() {
        return tankTables;
    }

    public void setTankTables(List<XvmMainInfo.TanklistEntity> tankTables) {
        this.tankTables = tankTables;
    }

    public List<XvmMainInfo.TanklistEntity> getTankTablesForActive() {
        return tankTablesForActive;
    }

    public void setTankTablesForActive(List<XvmMainInfo.TanklistEntity> tankTablesForActive) {
        this.tankTablesForActive = tankTablesForActive;
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

