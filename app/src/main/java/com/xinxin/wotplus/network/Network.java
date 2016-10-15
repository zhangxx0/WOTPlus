package com.xinxin.wotplus.network;

import com.xinxin.wotplus.QueryActivity;
import com.xinxin.wotplus.network.api.AchieveApi;
import com.xinxin.wotplus.network.api.ClanInfoApi;
import com.xinxin.wotplus.network.api.GradeApi;
import com.xinxin.wotplus.network.api.RecordApi;
import com.xinxin.wotplus.network.api.TankApi;
import com.xinxin.wotplus.network.api.TypeCountryApi;
import com.xinxin.wotplus.network.api.UserInfoApi;
import com.xinxin.wotplus.network.api.UtilApi;
import com.xinxin.wotplus.network.api.XvmInfoApi;
import com.xinxin.wotplus.network.api.XvmJsApi;
import com.xinxin.wotplus.util.Constant;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xinxin on 2016/5/17.
 */
public class Network {

    private static UserInfoApi userInfoApi;
    private static RecordApi recordApi;
    private static ClanInfoApi clanInfoApi;
    private static GradeApi gradeApi;
    private static TankApi tankApi;
    private static AchieveApi achieveApi;
    private static TypeCountryApi typeCountryApi;

    private static UtilApi utilApi;

    private static XvmInfoApi xvmInfoApi;
    private static XvmJsApi xvmJsApi;

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    /**
     * 获取玩家信息
     *
     * @param region
     * @return
     */
    public static UserInfoApi getUseInfoApi(String region) {

        // 需根据大区判断baseurl
        String baseUrl;
        if (QueryActivity.REGION_NORTH.equals(region)) {
            baseUrl = Constant.USER_BASE_URL_NORTH;
        } else {
            baseUrl = Constant.USER_BASE_URL_SOUTH;
        }

        // 这个if判断导致不能进行南北区切换请求数据的错误；导致先查询北区再查询南区的情况下，显示的总是北区的userinfo；
//        if (userInfoApi == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        userInfoApi = retrofit.create(UserInfoApi.class);

//        }
        return userInfoApi;
    }

    /**
     * 获取战绩html页面
     *
     * @param region
     * @return
     */
    public static RecordApi getRecordApi(String region) {

        // 需根据大区判断baseurl
        String baseUrl;
        if (QueryActivity.REGION_NORTH.equals(region)) {
            baseUrl = Constant.BASE_URL_NORTH;
        } else {
            baseUrl = Constant.BASE_URL_SOUTH;
        }

//        if (recordApi == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                //.addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        recordApi = retrofit.create(RecordApi.class);
//        }

        return recordApi;
    }

    /**
     * 获取军团信息
     *
     * @param region
     * @return
     */
    public static ClanInfoApi getClanInfoApi(String region) {

        // 需根据大区判断baseurl
        String baseUrl;
        if (QueryActivity.REGION_NORTH.equals(region)) {
            baseUrl = Constant.BASE_URL_NORTH + "/zh-cn/community/";
        } else {
            baseUrl = Constant.BASE_URL_SOUTH + "/zh-cn/community/";
        }
//        if (clanInfoApi == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        clanInfoApi = retrofit.create(ClanInfoApi.class);
//        }

        return clanInfoApi;
    }

    /**
     * 获取等级信息
     *
     * @param region
     * @return
     */
    public static GradeApi getGradeInfo(String region) {

        // 需根据大区判断baseurl
        String baseUrl;
        if (QueryActivity.REGION_NORTH.equals(region)) {
            baseUrl = Constant.GRADE_URL_BASE_NORTH;
        } else {
            baseUrl = Constant.GRADE_URL_BASE_SOUTH;
        }
//        if (gradeApi == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        gradeApi = retrofit.create(GradeApi.class);
//        }

        return gradeApi;

    }

    /**
     * 获取坦克类型和国家信息
     *
     * @param region
     * @return
     */
    public static TypeCountryApi getTypeCountryInfo(String region) {

        // 需根据大区判断baseurl
        String baseUrl;
        if (QueryActivity.REGION_NORTH.equals(region)) {
            // 使用成就的baseUrl，都是通用的
            baseUrl = Constant.ACHIEVE_NUMS_BASE_N;
        } else {
            baseUrl = Constant.ACHIEVE_NUMS_BASE_S;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        typeCountryApi = retrofit.create(TypeCountryApi.class);

        return typeCountryApi;

    }

    /**
     * 获取坦克信息
     *
     * @param region
     * @return
     */
    public static TankApi getTankInfo(String region) {

        // 需根据大区判断baseurl
        String baseUrl;
        if (QueryActivity.REGION_NORTH.equals(region)) {
            baseUrl = Constant.TANK_ACHIEVE_URL_BASE_NORTH;
        } else {
            baseUrl = Constant.TANK_ACHIEVE_URL_BASE_SOUTH;
        }
//        if (tankApi == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        tankApi = retrofit.create(TankApi.class);
//        }

        return tankApi;

    }

    /**
     * 工具
     *
     * @return
     */
    public static UtilApi getUtilApi() {

//        if (utilApi == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.FIR_VERSION_BASE)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        utilApi = retrofit.create(UtilApi.class);
//        }

        return utilApi;

    }

    /**
     * 获取XVM信息
     *
     * @return
     */
    public static XvmInfoApi getXvmInfo() {

//        if (xvmInfoApi == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.XVM_BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        xvmInfoApi = retrofit.create(XvmInfoApi.class);
//        }

        return xvmInfoApi;
    }


    /**
     * 获取js
     *
     * @return
     */
    public static XvmJsApi getXvmjsApi() {

//        if (xvmJsApi == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.XVM_JS_BASE_URL)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        xvmJsApi = retrofit.create(XvmJsApi.class);
//        }

        return xvmJsApi;
    }


    /**
     * 获取成就信息
     *
     * @return
     */
    public static AchieveApi getAchieveApi(String region) {

        // 需根据大区判断baseurl
        String baseUrl;
        if (QueryActivity.REGION_NORTH.equals(region)) {
            baseUrl = Constant.ACHIEVE_NUMS_BASE_N;
        } else {
            baseUrl = Constant.ACHIEVE_NUMS_BASE_S;
        }
//        if (achieveApi == null) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                //.addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        achieveApi = retrofit.create(AchieveApi.class);
//        }

        return achieveApi;
    }
}







