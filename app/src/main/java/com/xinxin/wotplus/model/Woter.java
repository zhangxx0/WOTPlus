package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/3/24.
 *
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

    private String account_id;
    private String account_name;
    private String account_battles;
    private String account_exp;
    private String account_profile;
    private String account_wins;
    private String clan_tag;
    private String clan_url;

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

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_battles() {
        return account_battles;
    }

    public void setAccount_battles(String account_battles) {
        this.account_battles = account_battles;
    }

    public String getAccount_exp() {
        return account_exp;
    }

    public void setAccount_exp(String account_exp) {
        this.account_exp = account_exp;
    }

    public String getAccount_profile() {
        return account_profile;
    }

    public void setAccount_profile(String account_profile) {
        this.account_profile = account_profile;
    }

    public String getAccount_wins() {
        return account_wins;
    }

    public void setAccount_wins(String account_wins) {
        this.account_wins = account_wins;
    }

    public String getClan_tag() {
        return clan_tag;
    }

    public void setClan_tag(String clan_tag) {
        this.clan_tag = clan_tag;
    }

    public String getClan_url() {
        return clan_url;
    }

    public void setClan_url(String clan_url) {
        this.clan_url = clan_url;
    }
}
