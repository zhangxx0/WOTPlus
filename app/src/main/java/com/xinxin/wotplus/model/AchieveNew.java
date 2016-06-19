package com.xinxin.wotplus.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xinxin on 2016/6/11.
 * 新的成就json转换类
 */
public class AchieveNew {


    /**
     * mark : 战斗勋章
     */

    private LocalizationEntity localization;
    /**
     * achievements : [{"block":"achievements7x7","icons":{"100x100":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/100x100/1156.png","120x120":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/120x120/1156.png","160x160":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/160x160/1156.png","180x180":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/180x180/1156.png","200x200":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/200x200/1156.png","32x32":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/32x32/1156.png","60x60":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/60x60/1156.png","67x71":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1156.png","80x80":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/80x80/1156.png","default":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1156.png"},"id":36,"localization":{"condition":"\u2022 仅累计战斗胜利的次数。\n\u2022 新获得的勋章将会累计。\n\u2022 只能在7V7模式中获得。","description":"进行获得经验排名第一的战斗100场。","hero_info":"","mark":"战争机器","mark_common":""},"mode":"7x7","name":"geniusForWarMedal","number":null,"section":"battle","tags":[],"type":"repeatable","weight":1}]
     * localization : {"mark":"战斗勋章"}
     * name : battle
     * order : 0
     */

    private String name;
    private int order;
    /**
     * block : achievements7x7
     * icons : {"100x100":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/100x100/1156.png","120x120":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/120x120/1156.png","160x160":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/160x160/1156.png","180x180":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/180x180/1156.png","200x200":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/200x200/1156.png","32x32":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/32x32/1156.png","60x60":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/60x60/1156.png","67x71":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1156.png","80x80":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/80x80/1156.png","default":"//ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1156.png"}
     * id : 36
     * localization : {"condition":"\u2022 仅累计战斗胜利的次数。\n\u2022 新获得的勋章将会累计。\n\u2022 只能在7V7模式中获得。","description":"进行获得经验排名第一的战斗100场。","hero_info":"","mark":"战争机器","mark_common":""}
     * mode : 7x7
     * name : geniusForWarMedal
     * number : null
     * section : battle
     * tags : []
     * type : repeatable
     * weight : 1.0
     */

    private List<AchievementsEntity> achievements;

    public LocalizationEntity getLocalization() {
        return localization;
    }

    public void setLocalization(LocalizationEntity localization) {
        this.localization = localization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<AchievementsEntity> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<AchievementsEntity> achievements) {
        this.achievements = achievements;
    }

    public static class LocalizationEntity {
        private String mark;

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }
    }

    public static class AchievementsEntity {

        /**
         * 手动添加，勋章数量
         * 2016年6月19日21:57:02
         */
        private String nums;

        private String block;
        /**
         * 100x100 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/100x100/1156.png
         * 120x120 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/120x120/1156.png
         * 160x160 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/160x160/1156.png
         * 180x180 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/180x180/1156.png
         * 200x200 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/200x200/1156.png
         * 32x32 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/32x32/1156.png
         * 60x60 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/60x60/1156.png
         * 67x71 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1156.png
         * 80x80 : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/80x80/1156.png
         * default : //ncw.worldoftanks.cn//wotpcnn/wot/current/rare_achievement/67x71/1156.png
         */

        private IconsEntity icons;
        private int id;
        /**
         * condition : • 仅累计战斗胜利的次数。
         • 新获得的勋章将会累计。
         • 只能在7V7模式中获得。
         * description : 进行获得经验排名第一的战斗100场。
         * hero_info :
         * mark : 战争机器
         * mark_common :
         */

        private LocalizationEntity localization;
        private String mode;
        private String name;
        private Object number;
        private String section;
        private String type;
        private double weight;
        private List<?> tags;

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getBlock() {
            return block;
        }

        public void setBlock(String block) {
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

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }

        public static class IconsEntity {
            @SerializedName("100x100")
            private String value100x100;
            @SerializedName("120x120")
            private String value120x120;
            @SerializedName("160x160")
            private String value160x160;
            @SerializedName("180x180")
            private String value180x180;
            @SerializedName("200x200")
            private String value200x200;
            @SerializedName("32x32")
            private String value32x32;
            @SerializedName("60x60")
            private String value60x60;
            @SerializedName("67x71")
            private String value67x71;
            @SerializedName("80x80")
            private String value80x80;
            @SerializedName("default")
            private String defaultX;
            private String big;

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }

            public String getValue100x100() {
                return value100x100;
            }

            public void setValue100x100(String value100x100) {
                this.value100x100 = value100x100;
            }

            public String getValue120x120() {
                return value120x120;
            }

            public void setValue120x120(String value120x120) {
                this.value120x120 = value120x120;
            }

            public String getValue160x160() {
                return value160x160;
            }

            public void setValue160x160(String value160x160) {
                this.value160x160 = value160x160;
            }

            public String getValue180x180() {
                return value180x180;
            }

            public void setValue180x180(String value180x180) {
                this.value180x180 = value180x180;
            }

            public String getValue200x200() {
                return value200x200;
            }

            public void setValue200x200(String value200x200) {
                this.value200x200 = value200x200;
            }

            public String getValue32x32() {
                return value32x32;
            }

            public void setValue32x32(String value32x32) {
                this.value32x32 = value32x32;
            }

            public String getValue60x60() {
                return value60x60;
            }

            public void setValue60x60(String value60x60) {
                this.value60x60 = value60x60;
            }

            public String getValue67x71() {
                return value67x71;
            }

            public void setValue67x71(String value67x71) {
                this.value67x71 = value67x71;
            }

            public String getValue80x80() {
                return value80x80;
            }

            public void setValue80x80(String value80x80) {
                this.value80x80 = value80x80;
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
            private String hero_info;
            private String mark;
            private String mark_common;

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

            public String getHero_info() {
                return hero_info;
            }

            public void setHero_info(String hero_info) {
                this.hero_info = hero_info;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public String getMark_common() {
                return mark_common;
            }

            public void setMark_common(String mark_common) {
                this.mark_common = mark_common;
            }
        }
    }
}
