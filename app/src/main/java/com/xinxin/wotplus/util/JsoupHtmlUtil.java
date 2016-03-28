package com.xinxin.wotplus.util;

import android.util.Log;

import com.xinxin.wotplus.model.Achieve;
import com.xinxin.wotplus.model.Achievements;
import com.xinxin.wotplus.model.Woter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinxin on 2016/3/24.
 * <p/>
 * 处理html页面
 */
public class JsoupHtmlUtil {


    /**
     * 处理战绩页面信息
     *
     * @param doc
     * @return
     */
    public static Woter handleWotPage(Document doc) {
        Woter woter = new Woter();
        Elements elements;
        Element element;

        String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(System.currentTimeMillis()));
        Log.d("time5", date);

        try {

            // 开始解析HTML
            elements = doc.getElementsByTag("title");
            element = elements.get(0);
            Log.d("Element", element.html());

            /**
             * (1)概要信息
             */

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
            woter.setDmgRecNum(elements.get(1).text());

            /**
             * （2）成就信息
             */

            // 提取成就息
            // 总数
            Element total = doc.select(".js-achievements-header").first();
            // totalNum，在下面赋值
            // System.out.println(total.text());

            // 最外层div js-full-achievements
            Element root = doc.select(".js-full-achievements").first();

            // 成就分类数量
            Elements subroots = root.getElementsByTag("h4");

            // （1）七个分类的名称及数量，在下面赋值
//            String subNum1 = subroots.get(0).text();
            // ...

            // （2）七个分类的内容
            Elements subrootContents = root.select(".b-achivements"); // 7个内容

            Achievements achievements = new Achievements();
            for (int i = 0; i < subrootContents.size(); i++) {

                List<Achieve> tempList = new ArrayList<Achieve>();

                Elements contentLis = subrootContents.get(i).select(".b-achivements_item");
                Elements contentDivs = subrootContents.get(i).select(".b-tooltip-main");

                for (int j = 0; j < contentLis.size(); j++) {

                    Achieve achieve = new Achieve();

                    Element contentLi = contentLis.get(j);
                    Element contentDiv = contentDivs.get(j);

                    // （1）from contentLi
                    // ID和图片地址
                    Element imgContent = contentLi.getElementsByTag("img").first();
                    String img = "http:" + imgContent.attr("src"); // //ncw.worldoftanks.cn/static/3.34.7/encyclopedia/tankopedia/achievement/geniusforwarmedal.png
                    String id = imgContent.attr("alt");
                    // 获得数量
                    Element numContent = contentLi.select(".b-achivements_num").first();
                    // 数量存在有或者没有两种情况，需要进行判断
                    String num = "0";
                    if (numContent != null) {
                        num = numContent.text();
                    }

                    // （2）from contentDiv
                    // 成就名称
                    Element nameContent = contentDiv.select(".b-bold").first();
                    String name = nameContent.text();
                    // 成就描述
                    String discription = "";
                    Element contentDis = contentDiv.getElementsByTag("p").last();
                    // 描述（1）
                    discription = contentDis.text();
                    // 附加描述 可能为空
                    Element contentDisAdd = contentDiv.getElementsByTag("li").first();
                    if (contentDisAdd != null) {
                        discription += contentDisAdd.text();
                    }
                    // 另：几个特殊的情况
                    // 巴顿猎手
                    if ("pattonvalley".equals(id)) {
                        Elements contentDiss = contentDiv.getElementsByTag("p");
                        discription = "";
                        for (Element p : contentDiss) {
                            discription += p.text();
                        }
                    }
                    // 猎人
                    if ("beasthunter".equals(id)) {
                        Elements contentDiss = contentDiv.getElementsByTag("p");
                        discription = "";
                        for (Element p : contentDiss) {
                            discription += p.text();
                        }
                    }
                    // 西奈雄狮
                    if ("sinai".equals(id)) {
                        Elements contentDiss = contentDiv.getElementsByTag("p");
                        discription = "";
                        for (Element p : contentDiss) {
                            discription += p.text();
                        }
                    }

                    achieve.setAchivementId(id);
                    achieve.setAchivementImg(img);
                    achieve.setAchivementNum(num);
                    achieve.setAchivementName(name);
                    achieve.setAchivementDes(discription);

                    tempList.add(achieve);
                }

                achievements.setTotalNum(total.text());
                if (i == 0) {
                    achievements.setWarheroList(tempList);
                    achievements.setWarHeroNum(subroots.get(i).text());
                }
                if (i == 1) {
                    achievements.setHonorList(tempList);
                    achievements.setHonorNum(subroots.get(i).text());
                }
                if (i == 2) {
                    achievements.setEpicList(tempList);
                    achievements.setEpicNum(subroots.get(i).text());
                }
                if (i == 3) {
                    achievements.setTeamList(tempList);
                    achievements.setTeamNum(subroots.get(i).text());
                }
                if (i == 4) {
                    achievements.setCommemorateList(tempList);
                    achievements.setCommemorateNum(subroots.get(i).text());
                }
                if (i == 5) {
                    achievements.setStageList(tempList);
                    achievements.setStageNum(subroots.get(i).text());
                }
                if (i == 6) {
                    achievements.setOtherList(tempList);
                    achievements.setOtherNum(subroots.get(i).text());
                }


            }

            woter.setAchievements(achievements);

            System.out.println(woter.toString());
            Log.d("time5", String.valueOf(System.currentTimeMillis()));

        } catch (Exception e) {
            e.printStackTrace();
        }


        return woter;
    }
}
