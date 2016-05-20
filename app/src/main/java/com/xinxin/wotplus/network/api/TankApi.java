package com.xinxin.wotplus.network.api;

import com.xinxin.wotplus.model.AchieveTank;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xinxin on 2016/5/21.
 * 坦克战绩
 */
public interface TankApi {

    // http://ncw.worldoftanks.cn/zh-cn/community/accounts/1503597733/vehicle_details/?vehicle_cd=3889

    @Headers(
            "X-Requested-With: XMLHttpRequest"
    )
    @GET("{accountId}/vehicle_details/")
    Observable<AchieveTank> getTankAchieve(@Path("accountId") String accountId, @Query("vehicle_cd") String tankId);
}
