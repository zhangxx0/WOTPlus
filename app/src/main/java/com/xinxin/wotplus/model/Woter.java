package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/3/24.
 * <p/>
 * 坦克世界玩家信息实体类
 */
public class Woter implements Serializable {

    // 主要信息

    /**
     * account_wins : 54.42161269608293
     * account_id : 1509154099
     * account_battles : 21802
     * account_profile : /zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/
     * clan_url : http://north.warsaga.cn/clans/2083/?utm_campaign=wot-portal&utm_medium=link
     * account_name : 康恩饭_
     * account_exp : 13014264
     * clan_tag : 小伙伴
     */

//    private String account_id;
//    private String account_name;
//    private String account_battles;
//    private String account_exp;
//    private String account_profile;
//    private String account_wins;
//    private String clan_tag;
//    private String clan_url;

    /**
     * 主要信息
     */
    private String woterName;

    // 创建账号时间戳
    private String timeStamp;

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
     * 成就信息
     */
    private Achievements achievements;


    public String getWoterName() {
        return woterName;
    }

    public void setWoterName(String woterName) {
        this.woterName = woterName;
    }

    public Achievements getAchievements() {
        return achievements;
    }

    public void setAchievements(Achievements achievements) {
        this.achievements = achievements;
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

}
