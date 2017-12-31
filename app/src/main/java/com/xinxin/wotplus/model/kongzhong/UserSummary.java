package com.xinxin.wotplus.model.kongzhong;

import java.io.Serializable;

/**
 * 用户概要统计信息
 * Created by xinxin on 2017/12/31.
 */

public class UserSummary implements Serializable {


    /**
     * status : ok
     * data : {"global_rating":8637,"damage_dealt":36365259,"shots_count":317050,"frags_max":11,"wins_count":15269,"hits_count":200848,"xp_amount":17826109,"battles_count":27981,"xp_max":4567,"wins_ratio":54.57,"xp_per_battle_average":637,"hits_ratio":63.35,"damage_per_battle_average":1299,"mastery":{"vehicles_count":231,"mastery_count":128}}
     */

    private String status;
    /**
     * global_rating : 8637
     * damage_dealt : 36365259
     * shots_count : 317050
     * frags_max : 11
     * wins_count : 15269
     * hits_count : 200848
     * xp_amount : 17826109
     * battles_count : 27981
     * xp_max : 4567
     * wins_ratio : 54.57
     * xp_per_battle_average : 637
     * hits_ratio : 63.35
     * damage_per_battle_average : 1299
     * mastery : {"vehicles_count":231,"mastery_count":128}
     */

    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int global_rating;
        private int damage_dealt;
        private int shots_count;
        private int frags_max;
        private int wins_count;
        private int hits_count;
        private int xp_amount;
        private int battles_count;
        private int xp_max;
        private double wins_ratio;
        private int xp_per_battle_average;
        private double hits_ratio;
        private int damage_per_battle_average;
        /**
         * vehicles_count : 231
         * mastery_count : 128
         */

        private MasteryBean mastery;

        public int getGlobal_rating() {
            return global_rating;
        }

        public void setGlobal_rating(int global_rating) {
            this.global_rating = global_rating;
        }

        public int getDamage_dealt() {
            return damage_dealt;
        }

        public void setDamage_dealt(int damage_dealt) {
            this.damage_dealt = damage_dealt;
        }

        public int getShots_count() {
            return shots_count;
        }

        public void setShots_count(int shots_count) {
            this.shots_count = shots_count;
        }

        public int getFrags_max() {
            return frags_max;
        }

        public void setFrags_max(int frags_max) {
            this.frags_max = frags_max;
        }

        public int getWins_count() {
            return wins_count;
        }

        public void setWins_count(int wins_count) {
            this.wins_count = wins_count;
        }

        public int getHits_count() {
            return hits_count;
        }

        public void setHits_count(int hits_count) {
            this.hits_count = hits_count;
        }

        public int getXp_amount() {
            return xp_amount;
        }

        public void setXp_amount(int xp_amount) {
            this.xp_amount = xp_amount;
        }

        public int getBattles_count() {
            return battles_count;
        }

        public void setBattles_count(int battles_count) {
            this.battles_count = battles_count;
        }

        public int getXp_max() {
            return xp_max;
        }

        public void setXp_max(int xp_max) {
            this.xp_max = xp_max;
        }

        public double getWins_ratio() {
            return wins_ratio;
        }

        public void setWins_ratio(double wins_ratio) {
            this.wins_ratio = wins_ratio;
        }

        public int getXp_per_battle_average() {
            return xp_per_battle_average;
        }

        public void setXp_per_battle_average(int xp_per_battle_average) {
            this.xp_per_battle_average = xp_per_battle_average;
        }

        public double getHits_ratio() {
            return hits_ratio;
        }

        public void setHits_ratio(double hits_ratio) {
            this.hits_ratio = hits_ratio;
        }

        public int getDamage_per_battle_average() {
            return damage_per_battle_average;
        }

        public void setDamage_per_battle_average(int damage_per_battle_average) {
            this.damage_per_battle_average = damage_per_battle_average;
        }

        public MasteryBean getMastery() {
            return mastery;
        }

        public void setMastery(MasteryBean mastery) {
            this.mastery = mastery;
        }

        public static class MasteryBean {
            private int vehicles_count;
            private int mastery_count;

            public int getVehicles_count() {
                return vehicles_count;
            }

            public void setVehicles_count(int vehicles_count) {
                this.vehicles_count = vehicles_count;
            }

            public int getMastery_count() {
                return mastery_count;
            }

            public void setMastery_count(int mastery_count) {
                this.mastery_count = mastery_count;
            }
        }
    }
}
