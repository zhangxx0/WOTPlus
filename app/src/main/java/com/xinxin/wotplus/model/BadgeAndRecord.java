package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/4/3.
 * 徽章与战绩
 */
public class BadgeAndRecord implements Serializable {

    // 特级 图片、数量、描述
    private String classAceImg; // 35*31
    private String classAceNum;
    private String classAceDes;
    // 1级 图片、数量、描述
    private String class1Img;
    private String class1Num;
    private String class1Des;
    // 2级 图片、数量、描述
    private String class2Img;
    private String class2Num;
    private String class2Des;
    // 3级 图片、数量、描述
    private String class3Img;
    private String class3Num;
    private String class3Des;

    // Record
    private String finghtNum;
    private String victoryNum;
    private String failureNum;
    private String survivingNum;
    private String experienceNum;
    private String averageExpNum;
    private String maxExpNum;

    private String destoryNum;
    private String findNum;
    private String hitRate;
    private String killingNum;
    private String averageKillingNum;
    private String occupiedBaseNum;
    private String defendBaseNum;


    public String getFinghtNum() {
        return finghtNum;
    }

    public void setFinghtNum(String finghtNum) {
        this.finghtNum = finghtNum;
    }

    public String getVictoryNum() {
        return victoryNum;
    }

    public void setVictoryNum(String victoryNum) {
        this.victoryNum = victoryNum;
    }

    public String getFailureNum() {
        return failureNum;
    }

    public void setFailureNum(String failureNum) {
        this.failureNum = failureNum;
    }

    public String getSurvivingNum() {
        return survivingNum;
    }

    public void setSurvivingNum(String survivingNum) {
        this.survivingNum = survivingNum;
    }

    public String getExperienceNum() {
        return experienceNum;
    }

    public void setExperienceNum(String experienceNum) {
        this.experienceNum = experienceNum;
    }

    public String getAverageExpNum() {
        return averageExpNum;
    }

    public void setAverageExpNum(String averageExpNum) {
        this.averageExpNum = averageExpNum;
    }

    public String getMaxExpNum() {
        return maxExpNum;
    }

    public void setMaxExpNum(String maxExpNum) {
        this.maxExpNum = maxExpNum;
    }

    public String getDestoryNum() {
        return destoryNum;
    }

    public void setDestoryNum(String destoryNum) {
        this.destoryNum = destoryNum;
    }

    public String getFindNum() {
        return findNum;
    }

    public void setFindNum(String findNum) {
        this.findNum = findNum;
    }

    public String getHitRate() {
        return hitRate;
    }

    public void setHitRate(String hitRate) {
        this.hitRate = hitRate;
    }

    public String getKillingNum() {
        return killingNum;
    }

    public void setKillingNum(String killingNum) {
        this.killingNum = killingNum;
    }

    public String getAverageKillingNum() {
        return averageKillingNum;
    }

    public void setAverageKillingNum(String averageKillingNum) {
        this.averageKillingNum = averageKillingNum;
    }

    public String getOccupiedBaseNum() {
        return occupiedBaseNum;
    }

    public void setOccupiedBaseNum(String occupiedBaseNum) {
        this.occupiedBaseNum = occupiedBaseNum;
    }

    public String getDefendBaseNum() {
        return defendBaseNum;
    }

    public void setDefendBaseNum(String defendBaseNum) {
        this.defendBaseNum = defendBaseNum;
    }

    public String getClassAceImg() {
        return classAceImg;
    }

    public void setClassAceImg(String classAceImg) {
        this.classAceImg = classAceImg;
    }

    public String getClassAceNum() {
        return classAceNum;
    }

    public void setClassAceNum(String classAceNum) {
        this.classAceNum = classAceNum;
    }

    public String getClassAceDes() {
        return classAceDes;
    }

    public void setClassAceDes(String classAceDes) {
        this.classAceDes = classAceDes;
    }

    public String getClass1Img() {
        return class1Img;
    }

    public void setClass1Img(String class1Img) {
        this.class1Img = class1Img;
    }

    public String getClass1Num() {
        return class1Num;
    }

    public void setClass1Num(String class1Num) {
        this.class1Num = class1Num;
    }

    public String getClass1Des() {
        return class1Des;
    }

    public void setClass1Des(String class1Des) {
        this.class1Des = class1Des;
    }

    public String getClass2Img() {
        return class2Img;
    }

    public void setClass2Img(String class2Img) {
        this.class2Img = class2Img;
    }

    public String getClass2Num() {
        return class2Num;
    }

    public void setClass2Num(String class2Num) {
        this.class2Num = class2Num;
    }

    public String getClass2Des() {
        return class2Des;
    }

    public void setClass2Des(String class2Des) {
        this.class2Des = class2Des;
    }

    public String getClass3Img() {
        return class3Img;
    }

    public void setClass3Img(String class3Img) {
        this.class3Img = class3Img;
    }

    public String getClass3Num() {
        return class3Num;
    }

    public void setClass3Num(String class3Num) {
        this.class3Num = class3Num;
    }

    public String getClass3Des() {
        return class3Des;
    }

    public void setClass3Des(String class3Des) {
        this.class3Des = class3Des;
    }
}
