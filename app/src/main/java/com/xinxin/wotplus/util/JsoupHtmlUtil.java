package com.xinxin.wotplus.util;

import android.util.Log;

import com.xinxin.wotplus.model.Woter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by xinxin on 2016/3/24.
 * <p/>
 * 处理html页面
 */
public class JsoupHtmlUtil {




    /**
     * 处理战绩页面信息
     * @param doc
     * @return
     */
    public static Woter handleWotPage(Document doc) {
        Woter woter = new Woter();
        Elements elements;
        Element element;

        try {

            // 开始解析HTML
            elements = doc.getElementsByTag("title");
            element = elements.get(0);
            Log.d("Element", element.html());

            // 创建时间戳
            element = doc.select(".js-date-format").first();
            woter.setTimeStamp(element.attr("data-timestamp"));

            // 主要信息栏
            elements = doc.select(".t-personal-data_value");
            woter.setPersonRanking(elements.get(0).text());
            woter.setPersonWin(elements.get(1).text());
            woter.setPersonFight(elements.get(2).text());
            woter.setPersonExp(elements.get(3).text());
            woter.setPersonDmg(elements.get(4).text());

            // 击杀／死亡率 伤害原因／收到
            elements = doc.select(".b-speedometer-weight");
            woter.setKillDeathRate(elements.get(0).text());
            woter.setDmgRecRate(elements.get(1).text());

            elements = doc.select(".b-speedometer-ratio");
            woter.setKillDeathNum(elements.get(0).text());
            woter.setDmgRecNum(elements.get(0).text());

            //

            System.out.println(woter.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return woter;
    }
}
