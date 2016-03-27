package com.xinxin.wotplus.model;

import java.io.Serializable;

/**
 * Created by xinxin on 2016/3/24.
 *
 * 成就信息
 *
 */
public class AchivementInfo implements Serializable {

    private String achivementId;
    private String achivementName;
    private String achivementNum;
    private String achivementDes;

    public String getAchivementId() {
        return achivementId;
    }

    public void setAchivementId(String achivementId) {
        this.achivementId = achivementId;
    }

    public String getAchivementName() {
        return achivementName;
    }

    public void setAchivementName(String achivementName) {
        this.achivementName = achivementName;
    }

    public String getAchivementNum() {
        return achivementNum;
    }

    public void setAchivementNum(String achivementNum) {
        this.achivementNum = achivementNum;
    }

    public String getAchivementDes() {
        return achivementDes;
    }

    public void setAchivementDes(String achivementDes) {
        this.achivementDes = achivementDes;
    }
}
