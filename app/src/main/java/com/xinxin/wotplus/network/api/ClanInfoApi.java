package com.xinxin.wotplus.network.api;

import com.xinxin.wotplus.model.ClanInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xinxin on 2016/5/20.
 * 获取军团信息
 */
public interface ClanInfoApi {

    // http://ncw.worldoftanks.cn/zh-cn/community/    clans/show_clan_block/  ?spa_id=1509154099&time_token=1459046879981

    @GET("clans/show_clan_block/")
    Observable<ClanInfo> getClanInfo(@Query("spa_id") String spa_id,@Query("time_token") String time_token);
}
