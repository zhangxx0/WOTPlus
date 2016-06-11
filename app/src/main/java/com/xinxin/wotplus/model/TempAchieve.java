package com.xinxin.wotplus.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xinxin on 2016/6/11.
 */
public class TempAchieve {

    /**
     * block : null
     * icons : {"100x100":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/100x100/1210.png","180x180":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/180x180/1210.png","32x32":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/32x32/1210.png","67x71":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1210.png","default":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1210.png"}
     * id : 435
     * localization : {"condition":"","description":"","history_info":"","mark":""}
     * mode : null
     * name : 1210
     * number : null
     * section : action
     * tags : ["backyard"]
     * type : null
     * weight : -1.0
     */

    private Object block;
    /**
     * 100x100 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/100x100/1210.png
     * 180x180 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/180x180/1210.png
     * 32x32 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/32x32/1210.png
     * 67x71 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1210.png
     * default : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1210.png
     */

    private IconsEntity icons;
    private int id;
    /**
     * condition :
     * description :
     * history_info :
     * mark :
     */

    private LocalizationEntity localization;
    private Object mode;
    private String name;
    private Object number;
    private String section;
    private Object type;
    private double weight;
    private List<String> tags;

    public Object getBlock() {
        return block;
    }

    public void setBlock(Object block) {
        this.block = block;
    }

    public IconsEntity getIcons() {
        return icons;
    }

    public void setIcons(IconsEntity icons) {
        this.icons = icons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalizationEntity getLocalization() {
        return localization;
    }

    public void setLocalization(LocalizationEntity localization) {
        this.localization = localization;
    }

    public Object getMode() {
        return mode;
    }

    public void setMode(Object mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNumber() {
        return number;
    }

    public void setNumber(Object number) {
        this.number = number;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static class IconsEntity {
        @SerializedName("100x100")
        private String value100x100;
        @SerializedName("180x180")
        private String value180x180;
        @SerializedName("32x32")
        private String value32x32;
        @SerializedName("67x71")
        private String value67x71;
        @SerializedName("default")
        private String defaultX;

        public String getValue100x100() {
            return value100x100;
        }

        public void setValue100x100(String value100x100) {
            this.value100x100 = value100x100;
        }

        public String getValue180x180() {
            return value180x180;
        }

        public void setValue180x180(String value180x180) {
            this.value180x180 = value180x180;
        }

        public String getValue32x32() {
            return value32x32;
        }

        public void setValue32x32(String value32x32) {
            this.value32x32 = value32x32;
        }

        public String getValue67x71() {
            return value67x71;
        }

        public void setValue67x71(String value67x71) {
            this.value67x71 = value67x71;
        }

        public String getDefaultX() {
            return defaultX;
        }

        public void setDefaultX(String defaultX) {
            this.defaultX = defaultX;
        }
    }

    public static class LocalizationEntity {
        private String condition;
        private String description;
        private String history_info;
        private String mark;

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHistory_info() {
            return history_info;
        }

        public void setHistory_info(String history_info) {
            this.history_info = history_info;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }
    }
}
