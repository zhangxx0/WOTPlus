package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/6/5.
 * 单车数据类
 */
public class XvmTankTable implements Serializable {


    /**
     * mark4 : 11
     * mark3 : 26
     * teambattles : 172
     * spotted : 555
     * mark2 : 53
     * mark1 : 68
     * teamwins : 124
     * movingpower : 641
     * frags : 438
     * gunmark : 2
     * totalpower : 183204
     * state : 0
     * xp : 219313
     * id : {"vehicleTypeCd":49,"accountDbId":1510511742,"battleTypeId":0}
     * capture : 609
     * draws : 2
     * maxdamage : 4986
     * defense : 374
     * maxassist : 3641
     * updateTime : {"nanos":0,"time":1415721600000,"minutes":0,"seconds":0,"hours":0,"month":10,"timezoneOffset":-480,"year":114,"day":3,"date":12}
     * damage : 550504
     * assist : 204991
     * maxxp : 1658
     * winpower : 30543
     * activeTime : {"nanos":0,"time":1417968000000,"minutes":0,"seconds":0,"hours":0,"month":11,"timezoneOffset":-480,"year":114,"day":1,"date":8}
     * maxfrags : 5
     * battles : 290
     * wins : 193
     */

    private int mark4;
    private int mark3;
    private int teambattles;
    private int spotted;
    private int mark2;
    private int mark1;
    private int teamwins;
    private int movingpower;
    private int frags;
    private int gunmark;
    private int totalpower;
    private int state;
    private int xp;
    /**
     * vehicleTypeCd : 49
     * accountDbId : 1510511742
     * battleTypeId : 0
     */

    private IdEntity id;
    private int capture;
    private int draws;
    private int maxdamage;
    private int defense;
    private int maxassist;
    /**
     * nanos : 0
     * time : 1415721600000
     * minutes : 0
     * seconds : 0
     * hours : 0
     * month : 10
     * timezoneOffset : -480
     * year : 114
     * day : 3
     * date : 12
     */

    private UpdateTimeEntity updateTime;
    private int damage;
    private int assist;
    private int maxxp;
    private int winpower;
    /**
     * nanos : 0
     * time : 1417968000000
     * minutes : 0
     * seconds : 0
     * hours : 0
     * month : 11
     * timezoneOffset : -480
     * year : 114
     * day : 1
     * date : 8
     */

    private ActiveTimeEntity activeTime;
    private int maxfrags;
    private int battles;
    private int wins;

    public int getMark4() {
        return mark4;
    }

    public void setMark4(int mark4) {
        this.mark4 = mark4;
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    public int getTeambattles() {
        return teambattles;
    }

    public void setTeambattles(int teambattles) {
        this.teambattles = teambattles;
    }

    public int getSpotted() {
        return spotted;
    }

    public void setSpotted(int spotted) {
        this.spotted = spotted;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getTeamwins() {
        return teamwins;
    }

    public void setTeamwins(int teamwins) {
        this.teamwins = teamwins;
    }

    public int getMovingpower() {
        return movingpower;
    }

    public void setMovingpower(int movingpower) {
        this.movingpower = movingpower;
    }

    public int getFrags() {
        return frags;
    }

    public void setFrags(int frags) {
        this.frags = frags;
    }

    public int getGunmark() {
        return gunmark;
    }

    public void setGunmark(int gunmark) {
        this.gunmark = gunmark;
    }

    public int getTotalpower() {
        return totalpower;
    }

    public void setTotalpower(int totalpower) {
        this.totalpower = totalpower;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public IdEntity getId() {
        return id;
    }

    public void setId(IdEntity id) {
        this.id = id;
    }

    public int getCapture() {
        return capture;
    }

    public void setCapture(int capture) {
        this.capture = capture;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getMaxdamage() {
        return maxdamage;
    }

    public void setMaxdamage(int maxdamage) {
        this.maxdamage = maxdamage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMaxassist() {
        return maxassist;
    }

    public void setMaxassist(int maxassist) {
        this.maxassist = maxassist;
    }

    public UpdateTimeEntity getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(UpdateTimeEntity updateTime) {
        this.updateTime = updateTime;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public int getMaxxp() {
        return maxxp;
    }

    public void setMaxxp(int maxxp) {
        this.maxxp = maxxp;
    }

    public int getWinpower() {
        return winpower;
    }

    public void setWinpower(int winpower) {
        this.winpower = winpower;
    }

    public ActiveTimeEntity getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(ActiveTimeEntity activeTime) {
        this.activeTime = activeTime;
    }

    public int getMaxfrags() {
        return maxfrags;
    }

    public void setMaxfrags(int maxfrags) {
        this.maxfrags = maxfrags;
    }

    public int getBattles() {
        return battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public static class IdEntity {
        private int vehicleTypeCd;
        private int accountDbId;
        private int battleTypeId;

        public int getVehicleTypeCd() {
            return vehicleTypeCd;
        }

        public void setVehicleTypeCd(int vehicleTypeCd) {
            this.vehicleTypeCd = vehicleTypeCd;
        }

        public int getAccountDbId() {
            return accountDbId;
        }

        public void setAccountDbId(int accountDbId) {
            this.accountDbId = accountDbId;
        }

        public int getBattleTypeId() {
            return battleTypeId;
        }

        public void setBattleTypeId(int battleTypeId) {
            this.battleTypeId = battleTypeId;
        }
    }

    public static class UpdateTimeEntity {
        private int nanos;
        private long time;
        private int minutes;
        private int seconds;
        private int hours;
        private int month;
        private int timezoneOffset;
        private int year;
        private int day;
        private int date;

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }
    }

    public static class ActiveTimeEntity {
        private int nanos;
        private long time;
        private int minutes;
        private int seconds;
        private int hours;
        private int month;
        private int timezoneOffset;
        private int year;
        private int day;
        private int date;

        public int getNanos() {
            return nanos;
        }

        public void setNanos(int nanos) {
            this.nanos = nanos;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setTimezoneOffset(int timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }
    }
}
