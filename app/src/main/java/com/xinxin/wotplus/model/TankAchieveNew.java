package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by xinxin on 2016/6/20.
 */
public class TankAchieveNew implements Serializable {

    List<AchieveNew.AchievementsEntity> rebuildTankAchieveList;

    Map achieveMap;

    List<AchieveNew> achieveNews;

    TankAchieveSummary tankAchieveSummary;




    public TankAchieveSummary getTankAchieveSummary() {
        return tankAchieveSummary;
    }

    public void setTankAchieveSummary(TankAchieveSummary tankAchieveSummary) {
        this.tankAchieveSummary = tankAchieveSummary;
    }

    public List<AchieveNew.AchievementsEntity> getRebuildTankAchieveList() {
        return rebuildTankAchieveList;
    }

    public void setRebuildTankAchieveList(List<AchieveNew.AchievementsEntity> rebuildTankAchieveList) {
        this.rebuildTankAchieveList = rebuildTankAchieveList;
    }

    public Map getAchieveMap() {
        return achieveMap;
    }

    public void setAchieveMap(Map achieveMap) {
        this.achieveMap = achieveMap;
    }

    public List<AchieveNew> getAchieveNews() {
        return achieveNews;
    }

    public void setAchieveNews(List<AchieveNew> achieveNews) {
        this.achieveNews = achieveNews;
    }


}
