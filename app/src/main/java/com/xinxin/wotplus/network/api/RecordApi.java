package com.xinxin.wotplus.network.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xinxin on 2016/5/17.
 */
public interface RecordApi {

    // 获取战绩html源码-根据account_id和account_name
    // http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/
    @GET("{accountId}-{accountName}/")
    Observable<String> getHtml(@Path("accountId") String accountId, @Path("accountName") String accountName);

    //
    // http://ncw.worldoftanks.cn       /zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/
    @GET("{lasturl}")
    Observable<ResponseBody> getHtml2(@Path("lasturl") String lasturl);
}
