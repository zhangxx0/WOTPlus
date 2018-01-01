package com.xinxin.wotplus.model.dto;

import com.xinxin.wotplus.model.kongzhong.Achievements;

import java.io.Serializable;

import okhttp3.ResponseBody;

/**
 * Created by xinxin on 2018/1/1.
 */

public class AchievementsAndResponsebody implements Serializable {

    private Achievements achievements;
    private ResponseBody responseBody;

    public Achievements getAchievements() {
        return achievements;
    }

    public void setAchievements(Achievements achievements) {
        this.achievements = achievements;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }
}
