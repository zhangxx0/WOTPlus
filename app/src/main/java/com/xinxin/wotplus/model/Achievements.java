package com.xinxin.wotplus.model;

import java.util.List;

/**
 * Created by xinxin on 2016/3/27.
 *
 * 成就信息-集合
 */
public class Achievements {

    // 总成就数量 获取/总数
    public String totalNum;

    /**
     * 战斗英雄
     */
    public List<Achieve> warheroList;
    /**
     * 荣誉排行
     */
    public List<Achieve> honorList;
    /**
     * 史诗成就
     */
    public List<Achieve> epicList;

    public List<Achieve> teamList;
    public List<Achieve> commemorateList;
    public List<Achieve> stageList;
    public List<Achieve> otherList;

    // 获取与总数数量  例如 9/14
    public String warHeroNum;
    public String honorNum;
    public String epicNum;
    public String teamNum;
    public String commemorateNum;
    public String stageNum;
    public String otherNum;


    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public List<Achieve> getWarheroList() {
        return warheroList;
    }

    public void setWarheroList(List<Achieve> warheroList) {
        this.warheroList = warheroList;
    }

    public List<Achieve> getHonorList() {
        return honorList;
    }

    public void setHonorList(List<Achieve> honorList) {
        this.honorList = honorList;
    }

    public List<Achieve> getEpicList() {
        return epicList;
    }

    public void setEpicList(List<Achieve> epicList) {
        this.epicList = epicList;
    }

    public List<Achieve> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Achieve> teamList) {
        this.teamList = teamList;
    }

    public List<Achieve> getCommemorateList() {
        return commemorateList;
    }

    public void setCommemorateList(List<Achieve> commemorateList) {
        this.commemorateList = commemorateList;
    }

    public List<Achieve> getStageList() {
        return stageList;
    }

    public void setStageList(List<Achieve> stageList) {
        this.stageList = stageList;
    }

    public List<Achieve> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<Achieve> otherList) {
        this.otherList = otherList;
    }

    public String getWarHeroNum() {
        return warHeroNum;
    }

    public void setWarHeroNum(String warHeroNum) {
        this.warHeroNum = warHeroNum;
    }

    public String getHonorNum() {
        return honorNum;
    }

    public void setHonorNum(String honorNum) {
        this.honorNum = honorNum;
    }

    public String getEpicNum() {
        return epicNum;
    }

    public void setEpicNum(String epicNum) {
        this.epicNum = epicNum;
    }

    public String getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(String teamNum) {
        this.teamNum = teamNum;
    }

    public String getCommemorateNum() {
        return commemorateNum;
    }

    public void setCommemorateNum(String commemorateNum) {
        this.commemorateNum = commemorateNum;
    }

    public String getStageNum() {
        return stageNum;
    }

    public void setStageNum(String stageNum) {
        this.stageNum = stageNum;
    }

    public String getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(String otherNum) {
        this.otherNum = otherNum;
    }
}
