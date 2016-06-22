package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by xinxin on 2016/6/22.
 * 活跃坦克VO
 */
public class XvmActiveTanks implements Serializable {

    List<XvmMainInfo.TanklistEntity> tanklistEntities;

    /**
     * tanks.js 坦克信息
     * tankId:TankInfo
     */
    private Map tanks;


    public List<XvmMainInfo.TanklistEntity> getTanklistEntities() {
        return tanklistEntities;
    }

    public void setTanklistEntities(List<XvmMainInfo.TanklistEntity> tanklistEntities) {
        this.tanklistEntities = tanklistEntities;
    }

    public Map getTanks() {
        return tanks;
    }

    public void setTanks(Map tanks) {
        this.tanks = tanks;
    }
}
