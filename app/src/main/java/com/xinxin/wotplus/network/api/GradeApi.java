package com.xinxin.wotplus.network.api;

import com.xinxin.wotplus.model.Grade;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xinxin on 2016/5/21.
 * 等级
 */
public interface GradeApi {

    // http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-/account_ratings/

    @Headers(
            "X-Requested-With: XMLHttpRequest"
    )
    @GET("{accountId}-/account_ratings/")
    Observable<Grade> getGradeInfo(@Path("accountId") String accountId);

}
