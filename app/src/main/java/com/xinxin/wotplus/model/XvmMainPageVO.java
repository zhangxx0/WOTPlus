package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/5/28.
 * XVM 主页信息展示使用
 */
public class XvmMainPageVO implements Serializable {

    private String lable;


    private int battals;
    private float winrate;
    private float activepower;
    private float level;
    private float activecount;

    public int getBattals() {
        return battals;
    }

    public void setBattals(int battals) {
        this.battals = battals;
    }

    public float getWinrate() {
        return winrate;
    }

    public void setWinrate(float winrate) {
        this.winrate = winrate;
    }

    public float getActivepower() {
        return activepower;
    }

    public void setActivepower(float activepower) {
        this.activepower = activepower;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public float getActivecount() {
        return activecount;
    }

    public void setActivecount(float activecount) {
        this.activecount = activecount;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
