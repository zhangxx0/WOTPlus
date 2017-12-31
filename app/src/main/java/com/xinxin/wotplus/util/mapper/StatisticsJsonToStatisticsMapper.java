package com.xinxin.wotplus.util.mapper;

import com.google.gson.Gson;
import com.xinxin.wotplus.model.kongzhong.Statistics;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * 将获取到的成就json处理之后封装成Statistics实体
 * Created by xinxin on 2018/1/1.
 */

public class StatisticsJsonToStatisticsMapper implements Func1<ResponseBody, Statistics> {

    private static StatisticsJsonToStatisticsMapper INSTANCE = new StatisticsJsonToStatisticsMapper();

    public StatisticsJsonToStatisticsMapper() {
    }

    public static StatisticsJsonToStatisticsMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Statistics call(ResponseBody responseBody) {
        Statistics statistics = new Statistics();

        // 处理json字符串
        try {
            String originJson = responseBody.string();

            originJson = originJson.replace("\n","");
            originJson = originJson.replace(" ","");

            originJson = originJson.replace("\"1\":{\"b","\"tier1\":{\"b");
            originJson = originJson.replace("\"2\":{\"b","\"tier2\":{\"b");
            originJson = originJson.replace("\"3\":{\"b","\"tier3\":{\"b");
            originJson = originJson.replace("\"4\":{\"b","\"tier4\":{\"b");
            originJson = originJson.replace("\"5\":{\"b","\"tier5\":{\"b");
            originJson = originJson.replace("\"6\":{\"b","\"tier6\":{\"b");
            originJson = originJson.replace("\"7\":{\"b","\"tier7\":{\"b");
            originJson = originJson.replace("\"8\":{\"b","\"tier8\":{\"b");
            originJson = originJson.replace("\"9\":{\"b","\"tier9\":{\"b");
            originJson = originJson.replace("\"10\":{\"b","\"tier10\":{\"b");

            originJson = originJson.replace("\"1\"","\"master1\"");
            originJson = originJson.replace("\"2\"","\"master2\"");
            originJson = originJson.replace("\"3\"","\"master3\"");
            originJson = originJson.replace("\"4\"","\"master4\"");

            originJson = originJson.replace("AT-SPG","ATSPG");

            System.out.println(originJson);

            Gson gson = new Gson();
            statistics = gson.fromJson(originJson, Statistics.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return statistics;
    }
}
