package com.xinxin.wotplus.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xinxin on 2016/5/21.
 * XVM主页信息实体类
 */
public class XvmMainInfo implements Serializable {


    /**
     * updatetime : {"nanos":0,"time":1460987535000,"minutes":52,"seconds":15,"hours":21,"month":3,"year":116,"timezoneOffset":-480,"day":1,"date":18}
     * name : ___红旗___
     * aid : 1510511742
     * battles : 47203
     * wins : 33300
     */

    private PlayerEntity player;
    /**
     * mark4 : 12
     * mark3 : 64
     * teambattles : 575
     * spotted : 1076
     * mark2 : 157
     * mark1 : 181
     * teamwins : 459
     * movingpower : 1652.5610847348191
     * frags : 1229
     * gunmark : 3
     * totalpower : 1023190.8885244972
     * state : 0
     * xp : 467569
     * id : {"vehicleTypeCd":2417,"accountDbId":1510511742,"battleTypeId":0}
     * capture : 202
     * draws : 7
     * maxdamage : 8356
     * defense : 355
     * maxassist : 4593
     * updateTime : {"nanos":0,"time":1463500800000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":116,"day":3,"date":18}
     * damage : 2312237
     * assist : 536695
     * maxxp : 1369
     * winpower : 200798.33450082407
     * activeTime : {"nanos":0,"time":1466092800000,"minutes":0,"seconds":0,"hours":0,"month":5,"timezoneOffset":-480,"year":116,"day":5,"date":17}
     * maxfrags : 8
     * battles : 614
     * wins : 481
     */

    private List<TanklistEntity> tanklist;
    /**
     * mark4 : 1
     * defense : 29
     * mark3 : 5
     * teambattles : 18
     * spotted : 38
     * mark2 : 6
     * mark1 : 5
     * teamwins : 18
     * frags : 44
     * damage : 82428
     * assist : 23860
     * xp : 16951
     * id : {"vehicleTypeCd":2417,"accountDbId":1510511742,"battleTypeId":0}
     * winpower : 6541.630191070003
     * daypower : 36504.90648877829
     * capture : 2
     * insert_date : {"nanos":0,"time":1463155200000,"minutes":0,"seconds":0,"hours":0,"month":4,"timezoneOffset":-480,"year":116,"day":6,"date":14}
     * draws : 0
     * battles : 18
     * wins : 18
     */

    private List<DaylistEntity> daylist;

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public List<TanklistEntity> getTanklist() {
        return tanklist;
    }

    public void setTanklist(List<TanklistEntity> tanklist) {
        this.tanklist = tanklist;
    }

    public List<DaylistEntity> getDaylist() {
        return daylist;
    }

    public void setDaylist(List<DaylistEntity> daylist) {
        this.daylist = daylist;
    }

    public static class PlayerEntity {
        /**
         * nanos : 0
         * time : 1460987535000
         * minutes : 52
         * seconds : 15
         * hours : 21
         * month : 3
         * year : 116
         * timezoneOffset : -480
         * day : 1
         * date : 18
         */

        private UpdatetimeEntity updatetime;
        private String name;
        private int aid;
        private int battles;
        private int wins;

        public UpdatetimeEntity getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(UpdatetimeEntity updatetime) {
            this.updatetime = updatetime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
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

        public static class UpdatetimeEntity {
            private int nanos;
            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int year;
            private int timezoneOffset;
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

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
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

    public static class TanklistEntity {
        private int mark4;
        private int mark3;
        private int teambattles;
        private int spotted;
        private int mark2;
        private int mark1;
        private int teamwins;
        private double movingpower;
        private int frags;
        private int gunmark;
        private double totalpower;
        private int state;
        private int xp;
        /**
         * vehicleTypeCd : 2417
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
         * time : 1463500800000
         * minutes : 0
         * seconds : 0
         * hours : 0
         * month : 4
         * timezoneOffset : -480
         * year : 116
         * day : 3
         * date : 18
         */

        private UpdateTimeEntity updateTime;
        private int damage;
        private int assist;
        private int maxxp;
        private double winpower;
        /**
         * nanos : 0
         * time : 1466092800000
         * minutes : 0
         * seconds : 0
         * hours : 0
         * month : 5
         * timezoneOffset : -480
         * year : 116
         * day : 5
         * date : 17
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

        public double getMovingpower() {
            return movingpower;
        }

        public void setMovingpower(double movingpower) {
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

        public double getTotalpower() {
            return totalpower;
        }

        public void setTotalpower(double totalpower) {
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

        public double getWinpower() {
            return winpower;
        }

        public void setWinpower(double winpower) {
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

    public static class DaylistEntity {
        private int mark4;
        private int defense;
        private int mark3;
        private int teambattles;
        private int spotted;
        private int mark2;
        private int mark1;
        private int teamwins;
        private int frags;
        private int damage;
        private int assist;
        private int xp;
        /**
         * vehicleTypeCd : 2417
         * accountDbId : 1510511742
         * battleTypeId : 0
         */

        private IdEntity id;
        private double winpower;
        private double daypower;
        private int capture;
        /**
         * nanos : 0
         * time : 1463155200000
         * minutes : 0
         * seconds : 0
         * hours : 0
         * month : 4
         * timezoneOffset : -480
         * year : 116
         * day : 6
         * date : 14
         */

        private InsertDateEntity insert_date;
        private int draws;
        private int battles;
        private int wins;

        public int getMark4() {
            return mark4;
        }

        public void setMark4(int mark4) {
            this.mark4 = mark4;
        }

        public int getDefense() {
            return defense;
        }

        public void setDefense(int defense) {
            this.defense = defense;
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

        public int getFrags() {
            return frags;
        }

        public void setFrags(int frags) {
            this.frags = frags;
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

        public double getWinpower() {
            return winpower;
        }

        public void setWinpower(double winpower) {
            this.winpower = winpower;
        }

        public double getDaypower() {
            return daypower;
        }

        public void setDaypower(double daypower) {
            this.daypower = daypower;
        }

        public int getCapture() {
            return capture;
        }

        public void setCapture(int capture) {
            this.capture = capture;
        }

        public InsertDateEntity getInsert_date() {
            return insert_date;
        }

        public void setInsert_date(InsertDateEntity insert_date) {
            this.insert_date = insert_date;
        }

        public int getDraws() {
            return draws;
        }

        public void setDraws(int draws) {
            this.draws = draws;
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

        public static class InsertDateEntity {
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
}
