package com.xinxin.wotplus.network.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xinxin on 2016/10/15.
 * 获取各类坦克列表
 */

public interface TanksApi {
    // http://wotpbe-cnn.worldoftanks.cn/accounts/api/statistics/vehicles/detailed/?filter%5Baccount_id%5D=1509154099&filter%5Blang%5D=zh-cn&filter%5Bvehicle_type%5D=AT-SPG
    @GET("vehicles/detailed/")
    Observable<ResponseBody> getTanksNewInfo(@Query("filter[account_id]") String accountId, @Query("filter[lang]") String lang, @Query("filter[vehicle_type]") String vehicle_type);
}
