package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/5/29.
 * 30日数据VO
 */
public class XvmThirtyDayVo implements Serializable {

    public class Id {
        private int vehicleTypeCd;

        private int accountDbId;

        private int battleTypeId;

        public void setVehicleTypeCd(int vehicleTypeCd){
            this.vehicleTypeCd = vehicleTypeCd;
        }
        public int getVehicleTypeCd(){
            return this.vehicleTypeCd;
        }
        public void setAccountDbId(int accountDbId){
            this.accountDbId = accountDbId;
        }
        public int getAccountDbId(){
            return this.accountDbId;
        }
        public void setBattleTypeId(int battleTypeId){
            this.battleTypeId = battleTypeId;
        }
        public int getBattleTypeId(){
            return this.battleTypeId;
        }

    }

    public class Insert_date {
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

        public void setNanos(int nanos){
            this.nanos = nanos;
        }
        public int getNanos(){
            return this.nanos;
        }
        public void setTime(long time){
            this.time = time;
        }
        public long getTime(){
            return this.time;
        }
        public void setMinutes(int minutes){
            this.minutes = minutes;
        }
        public int getMinutes(){
            return this.minutes;
        }
        public void setSeconds(int seconds){
            this.seconds = seconds;
        }
        public int getSeconds(){
            return this.seconds;
        }
        public void setHours(int hours){
            this.hours = hours;
        }
        public int getHours(){
            return this.hours;
        }
        public void setMonth(int month){
            this.month = month;
        }
        public int getMonth(){
            return this.month;
        }
        public void setTimezoneOffset(int timezoneOffset){
            this.timezoneOffset = timezoneOffset;
        }
        public int getTimezoneOffset(){
            return this.timezoneOffset;
        }
        public void setYear(int year){
            this.year = year;
        }
        public int getYear(){
            return this.year;
        }
        public void setDay(int day){
            this.day = day;
        }
        public int getDay(){
            return this.day;
        }
        public void setDate(int date){
            this.date = date;
        }
        public int getDate(){
            return this.date;
        }

    }


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

    private Id id;

    private double winpower;

    private double daypower;

    private int capture;

    private Insert_date insert_date;

    private int draws;

    private int battles;

    private int wins;

    public void setMark4(int mark4){
        this.mark4 = mark4;
    }
    public int getMark4(){
        return this.mark4;
    }
    public void setDefense(int defense){
        this.defense = defense;
    }
    public int getDefense(){
        return this.defense;
    }
    public void setMark3(int mark3){
        this.mark3 = mark3;
    }
    public int getMark3(){
        return this.mark3;
    }
    public void setTeambattles(int teambattles){
        this.teambattles = teambattles;
    }
    public int getTeambattles(){
        return this.teambattles;
    }
    public void setSpotted(int spotted){
        this.spotted = spotted;
    }
    public int getSpotted(){
        return this.spotted;
    }
    public void setMark2(int mark2){
        this.mark2 = mark2;
    }
    public int getMark2(){
        return this.mark2;
    }
    public void setMark1(int mark1){
        this.mark1 = mark1;
    }
    public int getMark1(){
        return this.mark1;
    }
    public void setTeamwins(int teamwins){
        this.teamwins = teamwins;
    }
    public int getTeamwins(){
        return this.teamwins;
    }
    public void setFrags(int frags){
        this.frags = frags;
    }
    public int getFrags(){
        return this.frags;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
    public int getDamage(){
        return this.damage;
    }
    public void setAssist(int assist){
        this.assist = assist;
    }
    public int getAssist(){
        return this.assist;
    }
    public void setXp(int xp){
        this.xp = xp;
    }
    public int getXp(){
        return this.xp;
    }
    public void setId(Id id){
        this.id = id;
    }
    public Id getId(){
        return this.id;
    }
    public void setWinpower(double winpower){
        this.winpower = winpower;
    }
    public double getWinpower(){
        return this.winpower;
    }
    public void setDaypower(double daypower){
        this.daypower = daypower;
    }
    public double getDaypower(){
        return this.daypower;
    }
    public void setCapture(int capture){
        this.capture = capture;
    }
    public int getCapture(){
        return this.capture;
    }
    public void setInsert_date(Insert_date insert_date){
        this.insert_date = insert_date;
    }
    public Insert_date getInsert_date(){
        return this.insert_date;
    }
    public void setDraws(int draws){
        this.draws = draws;
    }
    public int getDraws(){
        return this.draws;
    }
    public void setBattles(int battles){
        this.battles = battles;
    }
    public int getBattles(){
        return this.battles;
    }
    public void setWins(int wins){
        this.wins = wins;
    }
    public int getWins(){
        return this.wins;
    }

}
