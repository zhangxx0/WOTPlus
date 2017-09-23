package com.xinxin.wotplus.network.api;

import com.xinxin.wotplus.model.XvmMainInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xinxin on 2016/5/22.
 * XVM 信息
 */
public interface XvmInfoApi {

    /**
     * xvm 主信息
     * http://182.18.61.50/Data/action/WotAction/getIndex?name=___%E7%BA%A2%E6%97%97___&zone=north
     * http://rank.kongzhong.com/Data/action/WotAction/getIndex?name=___%E7%BA%A2%E6%97%97___&zone=north
     */
    @GET("getIndex")
    Observable<XvmMainInfo> getXvmMainInfo(@Query("name") String name, @Query("zone") String zone);

    /**
     * xvm 30日信息
     * http://182.18.61.50/Data/action/WotAction/getDay?aid=1510511742
     * http://rank.kongzhong.com/Data/action/WotAction/getDay?aid=1510511742
     */
    @GET("getDay")
    Observable<List<XvmMainInfo.DaylistEntity>> getXvmThirtyDay(@Query("aid") String aid);

    /**
     * xvm 单车数据
     * http://182.18.61.50/Data/action/WotAction/getTank?aid=1510511742
     * http://rank.kongzhong.com/Data/action/WotAction/getTank?aid=1510511742
     */
    @GET("getTank")
    Observable<List<XvmMainInfo.TanklistEntity>> getXvmTankTable(@Query("aid") String aid);
}
