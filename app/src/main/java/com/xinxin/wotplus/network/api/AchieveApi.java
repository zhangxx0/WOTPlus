package com.xinxin.wotplus.network.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xinxin on 2016/6/18.
 * 获取成就的数量
 */
public interface AchieveApi {

    // http://wotpbe-cnn.worldoftanks.cn/accounts/api/statistics/achievements/?filter%5Baccount_ids%5D=1509154099
    @GET("achievements/")
    // @GET("?filter[account_ids]={accountId}")
    Observable<ResponseBody> getAchievesNums(@Query("filter[account_ids]") String accountId);

    @GET("vehicles/")
    Observable<ResponseBody> getTankAchieve(@Query("filter[account_ids]") String accountId, @Query("filter[vehicle_cds]") String vehicleCds);

}
