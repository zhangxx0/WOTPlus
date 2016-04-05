package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/4/5.
 * 坦克-概要信息
 */
public class Tank implements Serializable {

    private String tankCountry;
    private String tankLevel;
    private String tankIcon;
    private String tankName;
    private String tankFightNum;
    private String tankWinRate;
    private String tankBadge;

    /**
     * ID用于查询坦克的详细信息
     */
    private String tankId;



    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }

    public String getTankCountry() {
        return tankCountry;
    }

    public void setTankCountry(String tankCountry) {
        this.tankCountry = tankCountry;
    }

    public String getTankLevel() {
        return tankLevel;
    }

    public void setTankLevel(String tankLevel) {
        this.tankLevel = tankLevel;
    }

    public String getTankIcon() {
        return tankIcon;
    }

    public void setTankIcon(String tankIcon) {
        this.tankIcon = tankIcon;
    }

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public String getTankFightNum() {
        return tankFightNum;
    }

    public void setTankFightNum(String tankFightNum) {
        this.tankFightNum = tankFightNum;
    }

    public String getTankWinRate() {
        return tankWinRate;
    }

    public void setTankWinRate(String tankWinRate) {
        this.tankWinRate = tankWinRate;
    }

    public String getTankBadge() {
        return tankBadge;
    }

    public void setTankBadge(String tankBadge) {
        this.tankBadge = tankBadge;
    }
}
