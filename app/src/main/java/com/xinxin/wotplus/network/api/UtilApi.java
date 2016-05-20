package com.xinxin.wotplus.network.api;

import com.xinxin.wotplus.model.VersionVo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xinxin on 2016/5/21.
 *
 */
public interface UtilApi {

    // http://api.fir.im/apps/latest/xxx?api_token=xxx #使用 `id` 请求

    /**
     * 版本更新检查
     * @param appId
     * @param api_token
     * @return
     */
    @GET("{appId}")
    Observable<VersionVo> checkVersion(@Path("appId") String appId, @Query("api_token") String api_token);
}
