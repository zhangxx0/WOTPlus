package com.xinxin.wotplus.network.api;

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




}
