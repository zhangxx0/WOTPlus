package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinxin on 2016/4/9.
 * 坦克成就专用类，比成就类少好多属性
 */
public class AchieveTank implements Serializable{


    /**
     * status : ok
     * achievements : [{"count":11,"type":"series","name":"armorPiercer","weight":1014},{"count":17,"type":"series","name":"titleSniper","weight":1015},{"count":2,"type":"repeatable","name":"sniper","weight":205}]
     * damage_per_battle : 1,255
     * xp_amount : 11,115
     * frags_per_battle : 0.73
     * damage_dealt : 13,813
     * damage_dealt_received_ratio : 1.65
     * frags_count : 8
     * xp_max : 1,825
     * hits_percent : 72.34
     * battles_count : 11
     * frags_killed_ratio : 1.14
     */

    private String status;
    private String damage_per_battle;
    private String xp_amount;
    private String frags_per_battle;
    private String damage_dealt;
    private String damage_dealt_received_ratio;
    private String frags_count;
    private String xp_max;
    private String hits_percent;
    private String battles_count;
    private String frags_killed_ratio;
    /**
     * count : 11
     * type : series
     * name : armorPiercer
     * weight : 1014.0
     */

    private List<AchievementsEntity> achievements;

    public static class AchievementsEntity {
        private int count;
        private String type;
        private String name;
        private double weight;

        public void setCount(int count) {
            this.count = count;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public int getCount() {
            return count;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public double getWeight() {
            return weight;
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDamage_per_battle(String damage_per_battle) {
        this.damage_per_battle = damage_per_battle;
    }

    public void setXp_amount(String xp_amount) {
        this.xp_amount = xp_amount;
    }

    public void setFrags_per_battle(String frags_per_battle) {
        this.frags_per_battle = frags_per_battle;
    }

    public void setDamage_dealt(String damage_dealt) {
        this.damage_dealt = damage_dealt;
    }

    public void setDamage_dealt_received_ratio(String damage_dealt_received_ratio) {
        this.damage_dealt_received_ratio = damage_dealt_received_ratio;
    }

    public void setFrags_count(String frags_count) {
        this.frags_count = frags_count;
    }

    public void setXp_max(String xp_max) {
        this.xp_max = xp_max;
    }

    public void setHits_percent(String hits_percent) {
        this.hits_percent = hits_percent;
    }

    public void setBattles_count(String battles_count) {
        this.battles_count = battles_count;
    }

    public void setFrags_killed_ratio(String frags_killed_ratio) {
        this.frags_killed_ratio = frags_killed_ratio;
    }

    public void setAchievements(List<AchievementsEntity> achievements) {
        this.achievements = achievements;
    }

    public String getStatus() {
        return status;
    }

    public String getDamage_per_battle() {
        return damage_per_battle;
    }

    public String getXp_amount() {
        return xp_amount;
    }

    public String getFrags_per_battle() {
        return frags_per_battle;
    }

    public String getDamage_dealt() {
        return damage_dealt;
    }

    public String getDamage_dealt_received_ratio() {
        return damage_dealt_received_ratio;
    }

    public String getFrags_count() {
        return frags_count;
    }

    public String getXp_max() {
        return xp_max;
    }

    public String getHits_percent() {
        return hits_percent;
    }

    public String getBattles_count() {
        return battles_count;
    }

    public String getFrags_killed_ratio() {
        return frags_killed_ratio;
    }

    public List<AchievementsEntity> getAchievements() {
        return achievements;
    }


}
