package com.xinxin.wotplus.network.api;

import com.xinxin.wotplus.model.TypesAndCountryNewVO;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xinxin on 2016/10/15.
 * 类型与国家
 */

public interface TypeCountryApi {
    // http://wotpbe-cnn.worldoftanks.cn/accounts/api/statistics/vehicles/common/?filter%5Baccount_id%5D=1503640699&filter%5Blang%5D=zh-cn
    @GET("vehicles/common/")
    Observable<ResponseBody> getTypeCountryInfo(@Query("filter[account_id]") String accountId, @Query("filter[lang]") String lang);
}
