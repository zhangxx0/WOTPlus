package com.xinxin.wotplus.model;

/**
 * Created by xinxin on 2016/5/22.
 * 英雄榜记录
 */
public class XvmHero {


    /**
     * rn : 5
     * value : 1710
     * aid : 1510511742
     * date : 2016-05-13
     * type : receive
     * vid : 15425
     */

    private int rn;
    private int value;
    private int aid;
    private String date;
    private String type;
    private int vid;

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }
}
