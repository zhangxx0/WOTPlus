package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinxin on 2016/3/31.
 * XVM用户信息实体类
 */
public class XvmUserInfo implements Serializable {

    /**
     * color : cc44ff
     * clanname : 小伙伴
     * clanid : 2083
     * levelname : 中校
     * level : 8
     * aid : 1509154099
     * username : 康恩饭_
     * battles : 21845
     * wins : 11891
     * totalrating : 151
     * oldrating : 150
     * htrating : 149
     * mtrating : 157
     * ltrating : 147
     * tdrating : 150
     * spgrating : 109
     * companybattles : 54
     * companywins : 24
     * clanbattles : 1
     * clanwins : 0
     * teambattles : 0
     * teamwins : 0
     * holdbattles : 0
     * holdwins : 0
     */

    private PlayerEntity player;
    /**
     * aid : 1509154099
     * vid : 9489
     * vname : E-100
     * vlevel : 10
     * vtype : heavyTank
     * country : germany
     * seasonbattles : 4
     * battles : 453
     * wins : 242
     * rating : 140.78416
     * maxrating : 150.78037
     * avgpower : 125.536
     * total : 148760
     * effrate : 124
     * seasonState : 1
     * top : 0
     * toppercent : 83.15
     * change : 24352
     * frags : 472
     * damageDeal : 1079297
     * attackpower : 46088.68128
     * damageAssist : 262406
     * spotted : 448
     * capturePoints : 660
     * droppedCapturePoints : 16
     * credit : 12265393
     * xp : 234107
     * potential_damage_dealt : 1723850
     * potential_damage_received : 1816355
     * damage_received : 969691
     * ma : 2932
     * mastermark : 4
     * gunmark : 1
     * updateDate : 2016-02-17
     */

    private List<RatinglistEntity> ratinglist;

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public void setRatinglist(List<RatinglistEntity> ratinglist) {
        this.ratinglist = ratinglist;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public List<RatinglistEntity> getRatinglist() {
        return ratinglist;
    }

    public static class PlayerEntity {
        private String color;
        private String clanname;
        private String clanid;
        private String levelname;
        private String level;
        private String aid;
        private String username;
        private String battles;
        private String wins;
        private String totalrating;
        private String oldrating;
        private String htrating;
        private String mtrating;
        private String ltrating;
        private String tdrating;
        private String spgrating;
        private String companybattles;
        private String companywins;
        private String clanbattles;
        private String clanwins;
        private String teambattles;
        private String teamwins;
        private String holdbattles;
        private String holdwins;

        public void setColor(String color) {
            this.color = color;
        }

        public void setClanname(String clanname) {
            this.clanname = clanname;
        }

        public void setClanid(String clanid) {
            this.clanid = clanid;
        }

        public void setLevelname(String levelname) {
            this.levelname = levelname;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setBattles(String battles) {
            this.battles = battles;
        }

        public void setWins(String wins) {
            this.wins = wins;
        }

        public void setTotalrating(String totalrating) {
            this.totalrating = totalrating;
        }

        public void setOldrating(String oldrating) {
            this.oldrating = oldrating;
        }

        public void setHtrating(String htrating) {
            this.htrating = htrating;
        }

        public void setMtrating(String mtrating) {
            this.mtrating = mtrating;
        }

        public void setLtrating(String ltrating) {
            this.ltrating = ltrating;
        }

        public void setTdrating(String tdrating) {
            this.tdrating = tdrating;
        }

        public void setSpgrating(String spgrating) {
            this.spgrating = spgrating;
        }

        public void setCompanybattles(String companybattles) {
            this.companybattles = companybattles;
        }

        public void setCompanywins(String companywins) {
            this.companywins = companywins;
        }

        public void setClanbattles(String clanbattles) {
            this.clanbattles = clanbattles;
        }

        public void setClanwins(String clanwins) {
            this.clanwins = clanwins;
        }

        public void setTeambattles(String teambattles) {
            this.teambattles = teambattles;
        }

        public void setTeamwins(String teamwins) {
            this.teamwins = teamwins;
        }

        public void setHoldbattles(String holdbattles) {
            this.holdbattles = holdbattles;
        }

        public void setHoldwins(String holdwins) {
            this.holdwins = holdwins;
        }

        public String getColor() {
            return color;
        }

        public String getClanname() {
            return clanname;
        }

        public String getClanid() {
            return clanid;
        }

        public String getLevelname() {
            return levelname;
        }

        public String getLevel() {
            return level;
        }

        public String getAid() {
            return aid;
        }

        public String getUsername() {
            return username;
        }

        public String getBattles() {
            return battles;
        }

        public String getWins() {
            return wins;
        }

        public String getTotalrating() {
            return totalrating;
        }

        public String getOldrating() {
            return oldrating;
        }

        public String getHtrating() {
            return htrating;
        }

        public String getMtrating() {
            return mtrating;
        }

        public String getLtrating() {
            return ltrating;
        }

        public String getTdrating() {
            return tdrating;
        }

        public String getSpgrating() {
            return spgrating;
        }

        public String getCompanybattles() {
            return companybattles;
        }

        public String getCompanywins() {
            return companywins;
        }

        public String getClanbattles() {
            return clanbattles;
        }

        public String getClanwins() {
            return clanwins;
        }

        public String getTeambattles() {
            return teambattles;
        }

        public String getTeamwins() {
            return teamwins;
        }

        public String getHoldbattles() {
            return holdbattles;
        }

        public String getHoldwins() {
            return holdwins;
        }
    }

    public static class RatinglistEntity {
        private int aid;
        private int vid;
        private String vname;
        private int vlevel;
        private String vtype;
        private String country;
        private int seasonbattles;
        private int battles;
        private int wins;
        private double rating;
        private double maxrating;
        private String avgpower;
        private String total;
        private String effrate;
        private int seasonState;
        private String top;
        private double toppercent;
        private String change;
        private int frags;
        private int damageDeal;
        private double attackpower;
        private int damageAssist;
        private int spotted;
        private int capturePoints;
        private int droppedCapturePoints;
        private int credit;
        private int xp;
        private int potential_damage_dealt;
        private int potential_damage_received;
        private int damage_received;
        private int ma;
        private int mastermark;
        private int gunmark;
        private String updateDate;

        public void setAid(int aid) {
            this.aid = aid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }

        public void setVlevel(int vlevel) {
            this.vlevel = vlevel;
        }

        public void setVtype(String vtype) {
            this.vtype = vtype;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setSeasonbattles(int seasonbattles) {
            this.seasonbattles = seasonbattles;
        }

        public void setBattles(int battles) {
            this.battles = battles;
        }

        public void setWins(int wins) {
            this.wins = wins;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public void setMaxrating(double maxrating) {
            this.maxrating = maxrating;
        }

        public void setAvgpower(String avgpower) {
            this.avgpower = avgpower;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public void setEffrate(String effrate) {
            this.effrate = effrate;
        }

        public void setSeasonState(int seasonState) {
            this.seasonState = seasonState;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public void setToppercent(double toppercent) {
            this.toppercent = toppercent;
        }

        public void setChange(String change) {
            this.change = change;
        }

        public void setFrags(int frags) {
            this.frags = frags;
        }

        public void setDamageDeal(int damageDeal) {
            this.damageDeal = damageDeal;
        }

        public void setAttackpower(double attackpower) {
            this.attackpower = attackpower;
        }

        public void setDamageAssist(int damageAssist) {
            this.damageAssist = damageAssist;
        }

        public void setSpotted(int spotted) {
            this.spotted = spotted;
        }

        public void setCapturePoints(int capturePoints) {
            this.capturePoints = capturePoints;
        }

        public void setDroppedCapturePoints(int droppedCapturePoints) {
            this.droppedCapturePoints = droppedCapturePoints;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public void setXp(int xp) {
            this.xp = xp;
        }

        public void setPotential_damage_dealt(int potential_damage_dealt) {
            this.potential_damage_dealt = potential_damage_dealt;
        }

        public void setPotential_damage_received(int potential_damage_received) {
            this.potential_damage_received = potential_damage_received;
        }

        public void setDamage_received(int damage_received) {
            this.damage_received = damage_received;
        }

        public void setMa(int ma) {
            this.ma = ma;
        }

        public void setMastermark(int mastermark) {
            this.mastermark = mastermark;
        }

        public void setGunmark(int gunmark) {
            this.gunmark = gunmark;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public int getAid() {
            return aid;
        }

        public int getVid() {
            return vid;
        }

        public String getVname() {
            return vname;
        }

        public int getVlevel() {
            return vlevel;
        }

        public String getVtype() {
            return vtype;
        }

        public String getCountry() {
            return country;
        }

        public int getSeasonbattles() {
            return seasonbattles;
        }

        public int getBattles() {
            return battles;
        }

        public int getWins() {
            return wins;
        }

        public double getRating() {
            return rating;
        }

        public double getMaxrating() {
            return maxrating;
        }

        public String getAvgpower() {
            return avgpower;
        }

        public String getTotal() {
            return total;
        }

        public String getEffrate() {
            return effrate;
        }

        public int getSeasonState() {
            return seasonState;
        }

        public String getTop() {
            return top;
        }

        public double getToppercent() {
            return toppercent;
        }

        public String getChange() {
            return change;
        }

        public int getFrags() {
            return frags;
        }

        public int getDamageDeal() {
            return damageDeal;
        }

        public double getAttackpower() {
            return attackpower;
        }

        public int getDamageAssist() {
            return damageAssist;
        }

        public int getSpotted() {
            return spotted;
        }

        public int getCapturePoints() {
            return capturePoints;
        }

        public int getDroppedCapturePoints() {
            return droppedCapturePoints;
        }

        public int getCredit() {
            return credit;
        }

        public int getXp() {
            return xp;
        }

        public int getPotential_damage_dealt() {
            return potential_damage_dealt;
        }

        public int getPotential_damage_received() {
            return potential_damage_received;
        }

        public int getDamage_received() {
            return damage_received;
        }

        public int getMa() {
            return ma;
        }

        public int getMastermark() {
            return mastermark;
        }

        public int getGunmark() {
            return gunmark;
        }

        public String getUpdateDate() {
            return updateDate;
        }
    }
}
