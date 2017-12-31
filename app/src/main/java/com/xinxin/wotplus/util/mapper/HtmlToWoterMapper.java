package com.xinxin.wotplus.util.mapper;

import com.xinxin.wotplus.MyApplication;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.util.JsoupHtmlUtil;
import com.xinxin.wotplus.util.PreferenceUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by xinxin on 2016/5/19.
 * html转换为Woter
 */
public class HtmlToWoterMapper implements Func1<Woter, Woter> {

    private static String region = "";
    private Woter woter;

    private static HtmlToWoterMapper INSTANCE = new HtmlToWoterMapper();

    private HtmlToWoterMapper() {

    }

    public static HtmlToWoterMapper getInstance() {
        region = PreferenceUtils.getCustomPrefString(MyApplication.getContext(), "queryinfo", "region", "");
        return INSTANCE;
    }

    @Override
    public Woter call(Woter woterFrom) {
        // 将ResponseBody中的html String转换为Woter实体类
        try {
            if (woterFrom != null) {
                ResponseBody responseBody = woterFrom.getResponseBody();
                if (responseBody != null) {

                    Document doc = Jsoup.parse(responseBody.string());
                    woter = JsoupHtmlUtil.handleWotPage(doc, region);
                    woter.setUserSummary(woterFrom.getUserSummary());

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return woter;
    }
}
