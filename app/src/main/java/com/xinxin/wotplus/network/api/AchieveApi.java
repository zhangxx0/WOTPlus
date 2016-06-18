package com.xinxin.wotplus.network.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by xinxin on 2016/6/18.
 * 获取成就的数量
 */
public interface AchieveApi {

    // http://wotpbe-cnn.worldoftanks.cn/accounts/api/statistics/achievements/?filter%5Baccount_ids%5D=1509154099
//    @Headers(
//            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
//            "Accept: "
//    )
    @GET()
    Observable<ResponseBody> getAchievesNums();

}
