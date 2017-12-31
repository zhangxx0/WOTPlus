package com.xinxin.wotplus.network.api;

import com.xinxin.wotplus.model.kongzhong.Achievements;
import com.xinxin.wotplus.model.kongzhong.UserSummary;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 新的空中网战绩信息API
 * Created by xinxin on 2017/12/31.
 */

public interface KongzhongNewApi {

    /**
     * 概要统计信息
     * https://ncw.worldoftanks.cn/wotup/profile/summary/?spa_id=1509154099&battle_type=random
     */
    @GET("summary/")
    Observable<UserSummary> getUserSummary(@Query("spa_id") String spaId, @Query("battle_type") String battleType);

    /**
     * 全部成就信息
     */
    @GET("achievements/full/")
    Observable<Achievements> getFullAchievements(@Query("spa_id") String spaId, @Query("language") String language);

    /**
     * 精简成就信息
     */
    @GET("achievements/short/")
    Observable<Achievements> getShortAchievements(@Query("spa_id") String spaId, @Query("language") String language);


}
