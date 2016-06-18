package com.xinxin.wotplus.util;

/**
 * Created by xinxin on 2016/3/31.
 * 常量
 */
public class Constant {

    /**
     * XVM用户信息(已廢棄)
     * 目标样例：
     * http://182.18.61.50/getUserJson?name=%E5%BA%B7%E6%81%A9%E9%A5%AD_&area=north
     */
    public static String XVM_USER_JSON_BASE_URL = "http://182.18.61.50/getUserJson?name=";
    public static String XVM_USER_JSON_URL = "";
    /**
     * 官网获取用户ID信息等
     * 目标样例：
     * http://scw.worldoftanks.cn/zh-cn/community/accounts/search/?name=%E6%8A%98%E8%85%BE5%E5%8F%B7&name_gt=
     */
    public static String USER_JSON_URL = "";
    public static String USER_JSON_BASE_URL_NORTH = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/search/?name=";
    public static String USER_JSON_BASE_URL_SOUTH = "http://scw.worldoftanks.cn/zh-cn/community/accounts/search/?name=";
    public static String USER_BASE_URL_NORTH = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/";
    public static String USER_BASE_URL_SOUTH = "http://scw.worldoftanks.cn/zh-cn/community/accounts/";

    /**
     * 获取战绩页面html
     * 目标样例：
     * 北区：
     * http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/
     * 南区：
     * http://scw.worldoftanks.cn/zh-cn/community/accounts/1800661743-%E6%8A%98%E8%85%BE5%E5%8F%B7/
     */
    public static String WOTER_URL = "";
    public static final String WOTER_BASE_URL_NORTH = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/";
    public static final String WOTER_BASE_URL_SOUTH = "http://scw.worldoftanks.cn/zh-cn/community/accounts/";
    public static final String BASE_URL_NORTH = "http://ncw.worldoftanks.cn";
    public static final String BASE_URL_SOUTH = "http://scw.worldoftanks.cn";

    /**
     * 军团信息URL
     * 目标样例：http://ncw.worldoftanks.cn/zh-cn/community/clans/show_clan_block/?spa_id=1509154099&time_token=1459046879981
     */
    public static final String CLAN_URL_BASE_NORTH = "http://ncw.worldoftanks.cn/zh-cn/community/clans/show_clan_block/?spa_id=";
    public static final String CLAN_URL_BASE_SOUTH = "http://scw.worldoftanks.cn/zh-cn/community/clans/show_clan_block/?spa_id=";

    /**
     * 等级
     * 目标样例：
     * public static final String GRADE_URL = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-/account_ratings/?timerange=28&group=all";
     * http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-/account_ratings/
     */
    public static final String GRADE_URL_BASE_NORTH = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/";
    public static final String GRADE_URL_BASE_SOUTH = "http://scw.worldoftanks.cn/zh-cn/community/accounts/";

    /**
     * 战车战绩
     * 目标样例：
     * http://ncw.worldoftanks.cn/zh-cn/community/accounts/1503597733/vehicle_details/?vehicle_cd=3889
     * 1503597733/vehicle_details/?vehicle_cd=3649
     */
    public static final String TANK_ACHIEVE_URL_BASE_NORTH = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/";
    public static final String TANK_ACHIEVE_URL_BASE_SOUTH = "http://scw.worldoftanks.cn/zh-cn/community/accounts/";

    /**
     * XVM
     */
    public static final String XVM_BASE_URL = "http://182.18.61.50/Data/action/WotAction/";
    public static final String XVM_JS_BASE_URL = "http://182.18.61.50/Data/";

    /**
     * FIR_API_TOKEN
     * http://api.fir.im/apps/latest/xxx?api_token=xxx #使用 `id` 请求
     */
    public static final String FIR_API_TOKEN = "a7b07d1c2f864e6b7147f17f2850e0f8";
    public static final String FIR_APP_ID = "5729fa65f2fc420dd6000018";
    public static final String FIR_VERSION_BASE = "http://api.fir.im/apps/latest/";

    /**
     * 成就数量
     * http://wotpbe-cnn.worldoftanks.cn/accounts/api/statistics/achievements/?filter%5Baccount_ids%5D=1509154099
     */
    public static final String ACHIEVE_NUMS_BASE ="http://wotpbe-cnn.worldoftanks.cn/accounts/api/statistics/";

    /**
     * 全屏扩散起始位置
     */
    public static final String START_LOCATION = "start_location";

}
