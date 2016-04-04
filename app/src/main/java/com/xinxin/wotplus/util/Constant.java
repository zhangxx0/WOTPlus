package com.xinxin.wotplus.util;

/**
 * Created by xinxin on 2016/3/31.
 * 常量
 */
public class Constant {

    /**
     * Base
     */
    public static final String BASE_URL = "http://ncw.worldoftanks.cn";

    /**
     * XVM用户信息
     * 目标样例：
     * http://182.18.61.50/getUserJson?name=%E5%BA%B7%E6%81%A9%E9%A5%AD_&area=north
     */
    public static String XVM_USER_JSON_BASE_URL = "http://182.18.61.50/getUserJson?name=";
    public static String XVM_USER_JSON_URL = "";

    /**
     * 获取战绩页面html
     * 目标样例：
     * http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/
     */
    public static String WOTER_URL = "";
    public static final String WOTER_BASE_URL = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/";

    public static final String WOTER_TARGET ="http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/";



    // 获取包含指向战绩页面链接的json；抓包返回json，请求和浏览器中报404错误；原因未知；
    public static final String QUERY_BY_NAME = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/search/?name=%E5%BA%B7%E6%81%A9%E9%A5%AD_&name_gt=/";


    public static final String USER_URL = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/";
    // 军团信息URL
    public static final String CLAN_URL = "http://ncw.worldoftanks.cn/zh-cn/community/clans/show_clan_block/?spa_id=1509154099&time_token=1459046879981";

    /**
     * 统计
     * 目标样例：
     * public static final String STATISTICS_URL = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-/account_ratings/?timerange=28&group=all";
     */
    public static final String STATISTICS_URL_BASE = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-/account_ratings/";

}
