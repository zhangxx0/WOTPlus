package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinxin on 2016/4/4.
 * 等级
 */
public class Grade implements Serializable {

    /**
     * user_rating : [{"survived_ratio":37.76,"wins_count":11929,"damage_dealt_rank_delta":137,"xp_amount":13077139,"frags_count":25006,"frags_count_rank_delta":53,"global_rating":8445,"wins_ratio_rank":17066,"battles_count":21910,"hits_ratio":63.42,"xp_max_rank_delta":-16,"damage_dealt_rank":53090,"battles_count_daily":15.77,"global_rating_rank":7519,"xp_max":4567,"damage_avg":1190.82,"hits_ratio_rank_delta":276,"wins_ratio_rank_delta":208,"wins_ratio":54.45,"account_id":1509154099,"frags_avg":1.14,"survived_ratio_rank_delta":84,"battles_count_rank":72626,"hits_ratio_rank":242430,"xp_avg_rank_delta":-24,"battles_count_rank_delta":98,"xp_max_rank":24135,"global_rating_rank_delta":44,"survived_ratio_rank":18374,"xp_avg":596.86,"damage_dealt":26090795,"frags_count_rank":33428,"xp_avg_rank":78353}]
     * status : ok
     * is_banned : false
     * rating_date : ["2016-04-03",1459641600]
     * authorized_user_id :
     */

    private String status;
    private boolean is_banned;
    private String authorized_user_id;
    /**
     * survived_ratio : 37.76
     * wins_count : 11929
     * damage_dealt_rank_delta : 137
     * xp_amount : 13077139
     * frags_count : 25006
     * frags_count_rank_delta : 53
     * global_rating : 8445
     * wins_ratio_rank : 17066
     * battles_count : 21910
     * hits_ratio : 63.42
     * xp_max_rank_delta : -16
     * damage_dealt_rank : 53090
     * battles_count_daily : 15.77
     * global_rating_rank : 7519
     * xp_max : 4567
     * damage_avg : 1190.82
     * hits_ratio_rank_delta : 276
     * wins_ratio_rank_delta : 208
     * wins_ratio : 54.45
     * account_id : 1509154099
     * frags_avg : 1.14
     * survived_ratio_rank_delta : 84
     * battles_count_rank : 72626
     * hits_ratio_rank : 242430
     * xp_avg_rank_delta : -24
     * battles_count_rank_delta : 98
     * xp_max_rank : 24135
     * global_rating_rank_delta : 44
     * survived_ratio_rank : 18374
     * xp_avg : 596.86
     * damage_dealt : 26090795
     * frags_count_rank : 33428
     * xp_avg_rank : 78353
     */

    private List<UserRatingEntity> user_rating;
    private List<String> rating_date;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIs_banned(boolean is_banned) {
        this.is_banned = is_banned;
    }

    public void setAuthorized_user_id(String authorized_user_id) {
        this.authorized_user_id = authorized_user_id;
    }

    public void setUser_rating(List<UserRatingEntity> user_rating) {
        this.user_rating = user_rating;
    }

    public void setRating_date(List<String> rating_date) {
        this.rating_date = rating_date;
    }

    public String getStatus() {
        return status;
    }

    public boolean isIs_banned() {
        return is_banned;
    }

    public String getAuthorized_user_id() {
        return authorized_user_id;
    }

    public List<UserRatingEntity> getUser_rating() {
        return user_rating;
    }

    public List<String> getRating_date() {
        return rating_date;
    }

    public static class UserRatingEntity {
        private double survived_ratio;
        private int wins_count;
        private int damage_dealt_rank_delta;
        private int xp_amount;
        private int frags_count;
        private int frags_count_rank_delta;
        private int global_rating;
        private int wins_ratio_rank;
        private int battles_count;
        private double hits_ratio;
        private int xp_max_rank_delta;
        private int damage_dealt_rank;
        private double battles_count_daily;
        private int global_rating_rank;
        private int xp_max;
        private double damage_avg;
        private int hits_ratio_rank_delta;
        private int wins_ratio_rank_delta;
        private double wins_ratio;
        private int account_id;
        private double frags_avg;
        private int survived_ratio_rank_delta;
        private int battles_count_rank;
        private int hits_ratio_rank;
        private int xp_avg_rank_delta;
        private int battles_count_rank_delta;
        private int xp_max_rank;
        private int global_rating_rank_delta;
        private int survived_ratio_rank;
        private double xp_avg;
        private int damage_dealt;
        private int frags_count_rank;
        private int xp_avg_rank;

        public void setSurvived_ratio(double survived_ratio) {
            this.survived_ratio = survived_ratio;
        }

        public void setWins_count(int wins_count) {
            this.wins_count = wins_count;
        }

        public void setDamage_dealt_rank_delta(int damage_dealt_rank_delta) {
            this.damage_dealt_rank_delta = damage_dealt_rank_delta;
        }

        public void setXp_amount(int xp_amount) {
            this.xp_amount = xp_amount;
        }

        public void setFrags_count(int frags_count) {
            this.frags_count = frags_count;
        }

        public void setFrags_count_rank_delta(int frags_count_rank_delta) {
            this.frags_count_rank_delta = frags_count_rank_delta;
        }

        public void setGlobal_rating(int global_rating) {
            this.global_rating = global_rating;
        }

        public void setWins_ratio_rank(int wins_ratio_rank) {
            this.wins_ratio_rank = wins_ratio_rank;
        }

        public void setBattles_count(int battles_count) {
            this.battles_count = battles_count;
        }

        public void setHits_ratio(double hits_ratio) {
            this.hits_ratio = hits_ratio;
        }

        public void setXp_max_rank_delta(int xp_max_rank_delta) {
            this.xp_max_rank_delta = xp_max_rank_delta;
        }

        public void setDamage_dealt_rank(int damage_dealt_rank) {
            this.damage_dealt_rank = damage_dealt_rank;
        }

        public void setBattles_count_daily(double battles_count_daily) {
            this.battles_count_daily = battles_count_daily;
        }

        public void setGlobal_rating_rank(int global_rating_rank) {
            this.global_rating_rank = global_rating_rank;
        }

        public void setXp_max(int xp_max) {
            this.xp_max = xp_max;
        }

        public void setDamage_avg(double damage_avg) {
            this.damage_avg = damage_avg;
        }

        public void setHits_ratio_rank_delta(int hits_ratio_rank_delta) {
            this.hits_ratio_rank_delta = hits_ratio_rank_delta;
        }

        public void setWins_ratio_rank_delta(int wins_ratio_rank_delta) {
            this.wins_ratio_rank_delta = wins_ratio_rank_delta;
        }

        public void setWins_ratio(double wins_ratio) {
            this.wins_ratio = wins_ratio;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public void setFrags_avg(double frags_avg) {
            this.frags_avg = frags_avg;
        }

        public void setSurvived_ratio_rank_delta(int survived_ratio_rank_delta) {
            this.survived_ratio_rank_delta = survived_ratio_rank_delta;
        }

        public void setBattles_count_rank(int battles_count_rank) {
            this.battles_count_rank = battles_count_rank;
        }

        public void setHits_ratio_rank(int hits_ratio_rank) {
            this.hits_ratio_rank = hits_ratio_rank;
        }

        public void setXp_avg_rank_delta(int xp_avg_rank_delta) {
            this.xp_avg_rank_delta = xp_avg_rank_delta;
        }

        public void setBattles_count_rank_delta(int battles_count_rank_delta) {
            this.battles_count_rank_delta = battles_count_rank_delta;
        }

        public void setXp_max_rank(int xp_max_rank) {
            this.xp_max_rank = xp_max_rank;
        }

        public void setGlobal_rating_rank_delta(int global_rating_rank_delta) {
            this.global_rating_rank_delta = global_rating_rank_delta;
        }

        public void setSurvived_ratio_rank(int survived_ratio_rank) {
            this.survived_ratio_rank = survived_ratio_rank;
        }

        public void setXp_avg(double xp_avg) {
            this.xp_avg = xp_avg;
        }

        public void setDamage_dealt(int damage_dealt) {
            this.damage_dealt = damage_dealt;
        }

        public void setFrags_count_rank(int frags_count_rank) {
            this.frags_count_rank = frags_count_rank;
        }

        public void setXp_avg_rank(int xp_avg_rank) {
            this.xp_avg_rank = xp_avg_rank;
        }

        public double getSurvived_ratio() {
            return survived_ratio;
        }

        public int getWins_count() {
            return wins_count;
        }

        public int getDamage_dealt_rank_delta() {
            return damage_dealt_rank_delta;
        }

        public int getXp_amount() {
            return xp_amount;
        }

        public int getFrags_count() {
            return frags_count;
        }

        public int getFrags_count_rank_delta() {
            return frags_count_rank_delta;
        }

        public int getGlobal_rating() {
            return global_rating;
        }

        public int getWins_ratio_rank() {
            return wins_ratio_rank;
        }

        public int getBattles_count() {
            return battles_count;
        }

        public double getHits_ratio() {
            return hits_ratio;
        }

        public int getXp_max_rank_delta() {
            return xp_max_rank_delta;
        }

        public int getDamage_dealt_rank() {
            return damage_dealt_rank;
        }

        public double getBattles_count_daily() {
            return battles_count_daily;
        }

        public int getGlobal_rating_rank() {
            return global_rating_rank;
        }

        public int getXp_max() {
            return xp_max;
        }

        public double getDamage_avg() {
            return damage_avg;
        }

        public int getHits_ratio_rank_delta() {
            return hits_ratio_rank_delta;
        }

        public int getWins_ratio_rank_delta() {
            return wins_ratio_rank_delta;
        }

        public double getWins_ratio() {
            return wins_ratio;
        }

        public int getAccount_id() {
            return account_id;
        }

        public double getFrags_avg() {
            return frags_avg;
        }

        public int getSurvived_ratio_rank_delta() {
            return survived_ratio_rank_delta;
        }

        public int getBattles_count_rank() {
            return battles_count_rank;
        }

        public int getHits_ratio_rank() {
            return hits_ratio_rank;
        }

        public int getXp_avg_rank_delta() {
            return xp_avg_rank_delta;
        }

        public int getBattles_count_rank_delta() {
            return battles_count_rank_delta;
        }

        public int getXp_max_rank() {
            return xp_max_rank;
        }

        public int getGlobal_rating_rank_delta() {
            return global_rating_rank_delta;
        }

        public int getSurvived_ratio_rank() {
            return survived_ratio_rank;
        }

        public double getXp_avg() {
            return xp_avg;
        }

        public int getDamage_dealt() {
            return damage_dealt;
        }

        public int getFrags_count_rank() {
            return frags_count_rank;
        }

        public int getXp_avg_rank() {
            return xp_avg_rank;
        }
    }
}
