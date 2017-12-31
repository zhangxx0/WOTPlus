package com.xinxin.wotplus.model.kongzhong;

import java.io.Serializable;

/**
 * 用户信息（包括军团简要信息）
 * @Description 此信息包含在战绩主页面html中的script中
 * Created by xinxin on 2017/12/31.
 */

public class UserData implements Serializable {

    /**
     * color : #fc0505
     * days_in_clan : 1709
     * id : 2083
     * name : 【小伙伴】--我们是兄弟--『那些年我们一起玩过的坦克』
     * role : recruit
     * tag : 小伙伴
     */

    private ClanInfoBean clan_info;
    /**
     * clan_info : {"color":"#fc0505","days_in_clan":1709,"id":2083,"name":"【小伙伴】--我们是兄弟--『那些年我们一起玩过的坦克』","role":"recruit","tag":"小伙伴"}
     * nickname : 康恩饭_
     * reg_timestamp : 1.339655648E9
     * spa_id : 1509154099
     * summary : {"is_valid":true,"last_battle_at":1514628867}
     */

    private String nickname;
    private long reg_timestamp;
    private int spa_id;
    /**
     * is_valid : true
     * last_battle_at : 1514628867
     */

    private SummaryBean summary;

    public ClanInfoBean getClan_info() {
        return clan_info;
    }

    public void setClan_info(ClanInfoBean clan_info) {
        this.clan_info = clan_info;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getReg_timestamp() {
        return reg_timestamp;
    }

    public void setReg_timestamp(long reg_timestamp) {
        this.reg_timestamp = reg_timestamp;
    }

    public int getSpa_id() {
        return spa_id;
    }

    public void setSpa_id(int spa_id) {
        this.spa_id = spa_id;
    }

    public SummaryBean getSummary() {
        return summary;
    }

    public void setSummary(SummaryBean summary) {
        this.summary = summary;
    }

    public static class ClanInfoBean {
        private String color;
        private int days_in_clan;
        private int id;
        private String name;
        private String role;
        private String tag;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getDays_in_clan() {
            return days_in_clan;
        }

        public void setDays_in_clan(int days_in_clan) {
            this.days_in_clan = days_in_clan;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    public static class SummaryBean {
        private boolean is_valid;
        private int last_battle_at;

        public boolean isIs_valid() {
            return is_valid;
        }

        public void setIs_valid(boolean is_valid) {
            this.is_valid = is_valid;
        }

        public int getLast_battle_at() {
            return last_battle_at;
        }

        public void setLast_battle_at(int last_battle_at) {
            this.last_battle_at = last_battle_at;
        }
    }
}
