package com.xinxin.wotplus.network.api;

import com.xinxin.wotplus.model.kongzhong.Achievements;
import com.xinxin.wotplus.model.kongzhong.UserSummary;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 战绩与坦克分布信息
     */
    @GET("statistics/")
    Observable<ResponseBody> getStatistics(@Query("spa_id") String spaId, @Query("battle_type") String battleType);

    /**
     * 战车列表信息
     */
    @POST("vehicles/list/")
    Observable<ResponseBody> getTankList();

    /**
     * 战车成就信息
     */
    @GET("vehicles/achievements/")
    Observable<ResponseBody> getTankAchievements(@Query("spa_id") String spaId, @Query("vehicle_cd")String vehicleCd, @Query("language") String language);


}
