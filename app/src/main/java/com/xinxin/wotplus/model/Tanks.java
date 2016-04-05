package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinxin on 2016/4/5.
 * 战车
 */
public class Tanks implements Serializable {

    /**
     * 类型信息列表
     */
    private List<TanksType> tanksTypes;

    /**
     * 各类战车列表
     */
    private List<Tank> lts;
    private List<Tank> mts;
    private List<Tank> hts;
    private List<Tank> tds;
    private List<Tank> spgs;

    public List<TanksType> getTanksTypes() {
        return tanksTypes;
    }

    public void setTanksTypes(List<TanksType> tanksTypes) {
        this.tanksTypes = tanksTypes;
    }

    public List<Tank> getLts() {
        return lts;
    }

    public void setLts(List<Tank> lts) {
        this.lts = lts;
    }

    public List<Tank> getMts() {
        return mts;
    }

    public void setMts(List<Tank> mts) {
        this.mts = mts;
    }

    public List<Tank> getHts() {
        return hts;
    }

    public void setHts(List<Tank> hts) {
        this.hts = hts;
    }

    public List<Tank> getTds() {
        return tds;
    }

    public void setTds(List<Tank> tds) {
        this.tds = tds;
    }

    public List<Tank> getSpgs() {
        return spgs;
    }

    public void setSpgs(List<Tank> spgs) {
        this.spgs = spgs;
    }
}
