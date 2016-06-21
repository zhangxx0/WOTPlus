package com.xinxin.wotplus.network.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by xinxin on 2016/5/22.
 *
 */
public interface XvmJsApi {

    /**
     * 获取 tanks.js
     * http://182.18.61.50/Data/tanks.js
     */
    @GET("tanks.js")
    Observable<ResponseBody> getTanksjs();

    @GET("tankdata/data.json")
    Observable<ResponseBody> getTankTop();
}
