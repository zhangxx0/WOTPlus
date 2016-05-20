package com.xinxin.wotplus.network;

import com.xinxin.wotplus.QueryActivity;
import com.xinxin.wotplus.network.api.ClanInfoApi;
import com.xinxin.wotplus.network.api.GradeApi;
import com.xinxin.wotplus.network.api.RecordApi;
import com.xinxin.wotplus.network.api.TankApi;
import com.xinxin.wotplus.network.api.UserInfoApi;
import com.xinxin.wotplus.network.api.UtilApi;
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
    private static UtilApi utilApi;

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    /**
     * 获取玩家信息
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

        if (userInfoApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            userInfoApi = retrofit.create(UserInfoApi.class);

        }
        return userInfoApi;
    }

    /**
     * 获取战绩html页面
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

        if (recordApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    //.addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            recordApi = retrofit.create(RecordApi.class);
        }

        return recordApi;
    }

    /**
     * 获取军团信息
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
        if (clanInfoApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            clanInfoApi = retrofit.create(ClanInfoApi.class);
        }

        return clanInfoApi;
    }

    /**
     * 获取等级信息
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
        if (gradeApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            gradeApi = retrofit.create(GradeApi.class);
        }

        return gradeApi;

    }

    /**
     * 获取坦克信息
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
        if (tankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            tankApi = retrofit.create(TankApi.class);
        }

        return tankApi;

    }

    /**
     * 工具
     * @return
     */
    public static UtilApi getUtilApi() {

        if (utilApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constant.FIR_VERSION_BASE)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            utilApi = retrofit.create(UtilApi.class);
        }

        return utilApi;

    }



}






