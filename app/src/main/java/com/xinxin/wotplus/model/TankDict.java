package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/5/21.
 */
public class TankDict implements Serializable {


    /**
     * name : Ch01_Type59
     * modelname : Ch01_Type59
     * country : C系
     * encountry : china
     * entype : mediumTank
     * type : 中型坦克
     * level : 8
     * alias : 59式
     * weight : 400
     */

    private String name;
    private String modelname;
    private String country;
    private String encountry;
    private String entype;
    private String type;
    private int level;
    private String alias;
    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEncountry() {
        return encountry;
    }

    public void setEncountry(String encountry) {
        this.encountry = encountry;
    }

    public String getEntype() {
        return entype;
    }

    public void setEntype(String entype) {
        this.entype = entype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
