package com.xinxin.wotplus.network;

import com.xinxin.wotplus.QueryActivity;
import com.xinxin.wotplus.network.api.ClanInfoApi;
import com.xinxin.wotplus.network.api.RecordApi;
import com.xinxin.wotplus.network.api.UserInfoApi;
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

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();


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

}







