package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/5/25.
 */
public class DaylistEntityForRecent implements Serializable {

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

    private double winpower;
    private double daypower;
    private int capture;

    private int draws;
    private int battles;
    private int wins;

    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

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

}
