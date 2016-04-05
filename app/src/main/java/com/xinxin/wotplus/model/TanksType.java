package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/4/5.
 * 战车类型
 */
public class TanksType implements Serializable {

    /**
     * 类型名称：轻型坦克
     * 类型数量：50
     * 类型场次：4,618
     * 类型胜率：55%
     * 类型徽章：50
     */
    private String tanksTypeName;
    private String tanksTypeNum;
    private String tanksTypeFightNum;
    private String tanksTypeWinRating;
    private String tanksTypeBadgeNum;

    public String getTanksTypeName() {
        return tanksTypeName;
    }

    public void setTanksTypeName(String tanksTypeName) {
        this.tanksTypeName = tanksTypeName;
    }

    public String getTanksTypeNum() {
        return tanksTypeNum;
    }

    public void setTanksTypeNum(String tanksTypeNum) {
        this.tanksTypeNum = tanksTypeNum;
    }

    public String getTanksTypeFightNum() {
        return tanksTypeFightNum;
    }

    public void setTanksTypeFightNum(String tanksTypeFightNum) {
        this.tanksTypeFightNum = tanksTypeFightNum;
    }

    public String getTanksTypeWinRating() {
        return tanksTypeWinRating;
    }

    public void setTanksTypeWinRating(String tanksTypeWinRating) {
        this.tanksTypeWinRating = tanksTypeWinRating;
    }

    public String getTanksTypeBadgeNum() {
        return tanksTypeBadgeNum;
    }

    public void setTanksTypeBadgeNum(String tanksTypeBadgeNum) {
        this.tanksTypeBadgeNum = tanksTypeBadgeNum;
    }
}
