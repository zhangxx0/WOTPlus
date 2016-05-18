package com.xinxin.wotplus.network.api;

import com.xinxin.wotplus.model.KongzhongUserInfo;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xinxin on 2016/5/17.
 */
public interface UserInfoApi {

    // 获取玩家信息-根据昵称和大区
    // http://scw.worldoftanks.cn/zh-cn/community/accounts/search/?name=%E6%8A%98%E8%85%BE5%E5%8F%B7&name_gt=
    @Headers(
            "X-Requested-With: XMLHttpRequest"
    )
    @GET("search/")
    Observable<KongzhongUserInfo> getUserInfo(@Query("name") String name, @Query("name_gt") String name_gt);


}
