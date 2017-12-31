package com.xinxin.wotplus.model;

import com.xinxin.wotplus.model.kongzhong.Achievements;
import com.xinxin.wotplus.model.kongzhong.Statistics;
import com.xinxin.wotplus.model.kongzhong.UserSummary;

import java.io.Serializable;

import okhttp3.ResponseBody;

/**
 * Created by xinxin on 2016/3/24.
 * <p/>
 * 坦克世界玩家信息实体类
 */
public class Woter implements Serializable {

    private ResponseBody responseBody;

    private UserSummary userSummary;

    /**
     * 主要信息
     */
    private String woterName;

    // 创建账号时间戳
    private String timeStamp;
    private String lastBattleTime;

    // 主要信息5个
    private String personWin;
    private String personFight;
    private String personRanking;
    private String personExp;
    private String personDmg;

    // 击杀／死亡率
    private String killDeathRate;
    private String killDeathNum;
    // 伤害原因／收到
    private String dmgRecRate;
    private String dmgRecNum;

    /**
     * 军团信息
     */
    private String enterClanFlag;
    private String clanDescription;
    private String clanImgSrc;
    private String clanPosition;
    private String clanDays;

    /**
     * 新版的成就信息
     * 2017年12月31日23:09:38
     */
    private Achievements achievements;

    /**
     * 新版战绩信息
     * 2018年1月1日01:51:01
     */
    private Statistics statistics;

    /**
     * 类型与国家
     */
    private TypesAndCountry typesAndCountry;

    /**
     * 战车
     */
    private Tanks tanks;


    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Achievements getAchievements() {
        return achievements;
    }

    public void setAchievements(Achievements achievements) {
        this.achievements = achievements;
    }

    public Tanks getTanks() {
        return tanks;
    }

    public void setTanks(Tanks tanks) {
        this.tanks = tanks;
    }

    public TypesAndCountry getTypesAndCountry() {
        return typesAndCountry;
    }

    public void setTypesAndCountry(TypesAndCountry typesAndCountry) {
        this.typesAndCountry = typesAndCountry;
    }


    public String getWoterName() {
        return woterName;
    }

    public void setWoterName(String woterName) {
        this.woterName = woterName;
    }

    public String getClanDays() {
        return clanDays;
    }

    public void setClanDays(String clanDays) {
        this.clanDays = clanDays;
    }

    public String getClanPosition() {
        return clanPosition;
    }

    public void setClanPosition(String clanPosition) {
        this.clanPosition = clanPosition;
    }

    public String getClanImgSrc() {
        return clanImgSrc;
    }

    public void setClanImgSrc(String clanImgSrc) {
        this.clanImgSrc = clanImgSrc;
    }

    public String getClanDescription() {
        return clanDescription;
    }

    public void setClanDescription(String clanDescription) {
        this.clanDescription = clanDescription;
    }


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getKillDeathRate() {
        return killDeathRate;
    }

    public void setKillDeathRate(String killDeathRate) {
        this.killDeathRate = killDeathRate;
    }

    public String getKillDeathNum() {
        return killDeathNum;
    }

    public void setKillDeathNum(String killDeathNum) {
        this.killDeathNum = killDeathNum;
    }

    public String getDmgRecRate() {
        return dmgRecRate;
    }

    public void setDmgRecRate(String dmgRecRate) {
        this.dmgRecRate = dmgRecRate;
    }

    public String getDmgRecNum() {
        return dmgRecNum;
    }

    public void setDmgRecNum(String dmgRecNum) {
        this.dmgRecNum = dmgRecNum;
    }

    public String getPersonWin() {
        return personWin;
    }

    public void setPersonWin(String personWin) {
        this.personWin = personWin;
    }

    public String getPersonFight() {
        return personFight;
    }

    public void setPersonFight(String personFight) {
        this.personFight = personFight;
    }

    public String getPersonRanking() {
        return personRanking;
    }

    public void setPersonRanking(String personRanking) {
        this.personRanking = personRanking;
    }

    public String getPersonExp() {
        return personExp;
    }

    public void setPersonExp(String personExp) {
        this.personExp = personExp;
    }

    public String getPersonDmg() {
        return personDmg;
    }

    public void setPersonDmg(String personDmg) {
        this.personDmg = personDmg;
    }

    public String getEnterClanFlag() {
        return enterClanFlag;
    }

    public void setEnterClanFlag(String enterClanFlag) {
        this.enterClanFlag = enterClanFlag;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public UserSummary getUserSummary() {
        return userSummary;
    }

    public void setUserSummary(UserSummary userSummary) {
        this.userSummary = userSummary;
    }

    public String getLastBattleTime() {
        return lastBattleTime;
    }

    public void setLastBattleTime(String lastBattleTime) {
        this.lastBattleTime = lastBattleTime;
    }


}
