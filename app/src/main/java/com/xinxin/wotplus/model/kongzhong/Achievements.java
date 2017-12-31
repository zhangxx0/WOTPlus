package com.xinxin.wotplus.model.kongzhong;

import java.io.Serializable;
import java.util.List;

/**
 * 成就信息
 * Created by xinxin on 2017/12/31.
 */

public class Achievements implements Serializable {
    private String status;
    private DataBean data;

    public static class DataBean {

        private List<AchDataBean> data;
        private List<String> parameters;

        public List<AchDataBean> getData() {
            return data;
        }

        public void setData(List<AchDataBean> data) {
            this.data = data;
        }

        public List<String> getParameters() {
            return parameters;
        }

        public void setParameters(List<String> parameters) {
            this.parameters = parameters;
        }

        public static class AchDataBean {
            private String name;
            private String mark;
            private int earned_achievements;
            private int available_achievements;
            private List<List<String>> achievements;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public int getEarned_achievements() {
                return earned_achievements;
            }

            public void setEarned_achievements(int earned_achievements) {
                this.earned_achievements = earned_achievements;
            }

            public int getAvailable_achievements() {
                return available_achievements;
            }

            public void setAvailable_achievements(int available_achievements) {
                this.available_achievements = available_achievements;
            }

            public List<List<String>> getAchievements() {
                return achievements;
            }

            public void setAchievements(List<List<String>> achievements) {
                this.achievements = achievements;
            }
        }
    }

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
}
