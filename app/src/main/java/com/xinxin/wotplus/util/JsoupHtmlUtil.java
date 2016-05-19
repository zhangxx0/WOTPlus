package com.xinxin.wotplus.util;

import com.xinxin.wotplus.QueryActivity;
import com.xinxin.wotplus.model.Achieve;
import com.xinxin.wotplus.model.Achievements;
import com.xinxin.wotplus.model.BadgeAndRecord;
import com.xinxin.wotplus.model.ClanInfoUsed;
import com.xinxin.wotplus.model.Tank;
import com.xinxin.wotplus.model.Tanks;
import com.xinxin.wotplus.model.TanksType;
import com.xinxin.wotplus.model.TypesAndCountry;
import com.xinxin.wotplus.model.Woter;

import org.jsoup.Jsoup;
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
    public static Woter handleWotPage(Document doc, String region) {
        Woter woter = new Woter();
        Elements elements;
        Element element;

        try {

            /**
             * (1)概要信息
             */
            element = doc.getElementById("js-profile-name");
            woter.setWoterName(element.text());


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
                    String img;
                    Element imgContent = contentLi.getElementsByTag("img").first();
                    if (QueryActivity.REGION_NORTH.equals(region)) {
                        img = "http:" + imgContent.attr("src"); // //ncw.worldoftanks.cn/static/3.34.7/encyclopedia/tankopedia/achievement/geniusforwarmedal.png
                    } else {
                        img = "http://scw.worldoftanks.cn" + imgContent.attr("src"); // /static/3.35.7/encyclopedia/tankopedia/achievement/victorymarch.png
                    }

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

            /**
             * （3）统计信息
             */
            BadgeAndRecord badgeAndRecord = new BadgeAndRecord();
            // 徽章信息
            Element badge = doc.select(".b-result-classes").first();
            Elements badgeImgs = badge.getElementsByTag("img");
            // -徽章图片
            if (QueryActivity.REGION_NORTH.equals(region)) {
                badgeAndRecord.setClassAceImg("http:" + badgeImgs.get(0).attr("src"));
                badgeAndRecord.setClass1Img("http:" + badgeImgs.get(1).attr("src"));
                badgeAndRecord.setClass2Img("http:" + badgeImgs.get(2).attr("src"));
                badgeAndRecord.setClass3Img("http:" + badgeImgs.get(3).attr("src"));
            } else {
                badgeAndRecord.setClassAceImg("http://scw.worldoftanks.cn" + badgeImgs.get(0).attr("src"));
                badgeAndRecord.setClass1Img("http://scw.worldoftanks.cn" + badgeImgs.get(1).attr("src"));
                badgeAndRecord.setClass2Img("http://scw.worldoftanks.cn" + badgeImgs.get(2).attr("src"));
                badgeAndRecord.setClass3Img("http://scw.worldoftanks.cn" + badgeImgs.get(3).attr("src"));
            }

            // -徽章数量
            Elements badgeNums = badge.select(".t-dotted_value__middle");
            badgeAndRecord.setClassAceNum(badgeNums.get(0).text());
            badgeAndRecord.setClass1Num(badgeNums.get(1).text());
            badgeAndRecord.setClass2Num(badgeNums.get(2).text());
            badgeAndRecord.setClass3Num(badgeNums.get(3).text());
            // -徽章描述
            Elements badgeDes = badge.select(".b-tooltip-main");
            badgeAndRecord.setClassAceDes(badgeDes.get(0).text());
            badgeAndRecord.setClass1Des(badgeDes.get(1).text());
            badgeAndRecord.setClass2Des(badgeDes.get(2).text());
            badgeAndRecord.setClass3Des(badgeDes.get(3).text());

            // 全部战绩
            Element record = doc.select(".b-result").first();
            Elements records = record.select(".t-dotted_value");
            badgeAndRecord.setFinghtNum(records.get(0).text());
            badgeAndRecord.setVictoryNum(records.get(1).text());
            badgeAndRecord.setFailureNum(records.get(2).text());
            badgeAndRecord.setSurvivingNum(records.get(3).text());
            badgeAndRecord.setExperienceNum(records.get(4).text());
            badgeAndRecord.setAverageExpNum(records.get(5).text());
            badgeAndRecord.setMaxExpNum(records.get(6).text());

            // 战斗统计
            Element count = doc.select(".b-result").last();
            Elements counts = count.select(".t-dotted_value");
            badgeAndRecord.setDestoryNum(counts.get(0).text());
            badgeAndRecord.setFindNum(counts.get(1).text());
            badgeAndRecord.setHitRate(counts.get(2).text());
            badgeAndRecord.setKillingNum(counts.get(3).text());
            badgeAndRecord.setAverageKillingNum(counts.get(4).text());
            badgeAndRecord.setOccupiedBaseNum(counts.get(5).text());
            badgeAndRecord.setDefendBaseNum(counts.get(6).text());

            woter.setBadgeAndRecord(badgeAndRecord);

            // 坦克类型和国家
            TypesAndCountry typesAndCountry = new TypesAndCountry();
            // 类型
            Element types = doc.select(".b-diagram-block").first();
            // 国家
            Element countrys = doc.select(".b-diagram-block").last();

            Elements percentageTypes = types.select(".js-value");
            typesAndCountry.setPercentageLt(percentageTypes.get(0).text());
            typesAndCountry.setPercentageMt(percentageTypes.get(1).text());
            typesAndCountry.setPercentageHt(percentageTypes.get(2).text());
            typesAndCountry.setPercentageTd(percentageTypes.get(3).text());
            typesAndCountry.setPercentageSpg(percentageTypes.get(4).text());

            Elements numsTypes = types.select(".js-results");
            typesAndCountry.setNumsLt(numsTypes.get(0).text());
            typesAndCountry.setNumsMt(numsTypes.get(1).text());
            typesAndCountry.setNumsHt(numsTypes.get(2).text());
            typesAndCountry.setNumsTd(numsTypes.get(3).text());
            typesAndCountry.setNumsSpg(numsTypes.get(4).text());

            Elements percentageCountrys = countrys.select(".js-value");
            typesAndCountry.setPercentageUssr(percentageCountrys.get(0).text());
            typesAndCountry.setPercentageGe(percentageCountrys.get(1).text());
            typesAndCountry.setPercentageUsa(percentageCountrys.get(2).text());
            typesAndCountry.setPercentageFr(percentageCountrys.get(3).text());
            typesAndCountry.setPercentageUk(percentageCountrys.get(4).text());
            typesAndCountry.setPercentageCn(percentageCountrys.get(5).text());
            typesAndCountry.setPercentageJa(percentageCountrys.get(6).text());
            typesAndCountry.setPercentageCz(percentageCountrys.get(7).text());

            Elements numsCountrys = countrys.select(".js-results");
            typesAndCountry.setNumsUssr(numsCountrys.get(0).text());
            typesAndCountry.setNumsGe(numsCountrys.get(1).text());
            typesAndCountry.setNumsUsa(numsCountrys.get(2).text());
            typesAndCountry.setNumsFr(numsCountrys.get(3).text());
            typesAndCountry.setNumsUk(numsCountrys.get(4).text());
            typesAndCountry.setNumsCn(numsCountrys.get(5).text());
            typesAndCountry.setNumsJa(numsCountrys.get(6).text());
            typesAndCountry.setNumsCz(numsCountrys.get(7).text());

            woter.setTypesAndCountry(typesAndCountry);

            /**
             * （4）等级信息
             *  等级信息在html中获取不到，因此，在等级页面再做一个请求；
             */

            /**
             * （5）战车信息
             *  add date 2016年4月5日23:34:00
             */
            Tanks tanksvo = new Tanks();
            List<TanksType> tanksTypes = new ArrayList<TanksType>();
            List<Tank> lts = new ArrayList<Tank>();
            List<Tank> mts = new ArrayList<Tank>();
            List<Tank> hts = new ArrayList<Tank>();
            List<Tank> tds = new ArrayList<Tank>();
            List<Tank> spgs = new ArrayList<Tank>();

            // （1）战车类型信息 第一层list
            Elements tanktypes = doc.select(".tablesorter-no-sort");

            for (Element tankLt : tanktypes) {
                TanksType tanksType = new TanksType();
                Element tanksTypeName = tankLt.select(".b-tankstype-text").first();
                Element tanksTypeNum = tankLt.select(".b-armory-col").first();
                Element tanksTypeFightNum = tankLt.select(".t-profile_right").first();
                Element tanksTypeWinRating = tankLt.select(".t-profile_center").first();
                Element tanksTypeBadgeNum = tankLt.select(".t-profile_center").last();

                tanksType.setTanksTypeName(tanksTypeName.text().substring(0, tanksTypeName.text().length() - 3));
                tanksType.setTanksTypeNum(tanksTypeNum.text());
                tanksType.setTanksTypeFightNum(tanksTypeFightNum.text());
                tanksType.setTanksTypeWinRating(tanksTypeWinRating.text());
                tanksType.setTanksTypeBadgeNum(tanksTypeBadgeNum.text());
                // 添加到tanksTypes
                tanksTypes.add(tanksType);
            }

            // (2) 各类型战车列表
            Elements tanks = doc.select(".sortable");

            for (int i = 0; i < tanks.size(); i++) {

                // 坦克基本信息
                Elements tankBaseInfos = tanks.get(i).select(".t-profile_tankstype");
                // 坦克ID信息，用于下一步请求
                Elements tankIdInfos = tanks.get(i).select(".t-profile_slidedown");

                // 循环提取tank信息
                for (int j = 0; j < tankBaseInfos.size(); j++) {
                    Tank tank = new Tank();
                    // 基本信息
                    // -国家
                    Element tankCountryIcon = tankBaseInfos.get(j).select(".b-armory-wrapper").first();
                    // b-armory-wrapper b-armory-wrapper__china
                    // 去掉前面的class，只留后面的这个
                    tank.setTankCountry(tankCountryIcon.className().substring(17));
                    // -级别
                    Element tankLevel = tankBaseInfos.get(j).select(".b-armory-level").first();
                    tank.setTankLevel(tankLevel.text());
                    // -图标
                    Element tankImg = tankBaseInfos.get(j).getElementsByTag("img").first();
                    if (QueryActivity.REGION_NORTH.equals(region)) {
                        tank.setTankIcon("http:" + tankImg.attr("src"));
                    } else {
                        tank.setTankIcon("http://scw.worldoftanks.cn" + tankImg.attr("src"));
                    }
                    // -名称
                    Element tankName = tankBaseInfos.get(j).select(".b-name-vehicle").first();
                    tank.setTankName(tankName.text());
                    // -战斗
                    Element tankFightNum = tankBaseInfos.get(j).select(".t-profile_right").first();
                    tank.setTankFightNum(tankFightNum.text());
                    // -胜率
                    Element tankWinRate = tankBaseInfos.get(j).select(".t-profile_center").first();
                    tank.setTankWinRate(tankWinRate.text());
                    // -徽章
                    Element tankBadge = tankBaseInfos.get(j).getElementsByTag("img").last();
                    if (QueryActivity.REGION_NORTH.equals(region)) {
                        tank.setTankBadge("http:" + tankBadge.attr("src"));
                    } else {
                        tank.setTankBadge("http://scw.worldoftanks.cn" + tankBadge.attr("src"));
                    }

                    // ID信息
                    Element tankIdInfo = tankIdInfos.get(j);
                    tank.setTankId(tankIdInfo.attributes().get("data-vehicle-cd"));

                    switch (i) {
                        // 根据i add到不同的list的中去
                        case 0:
                            lts.add(tank);
                            break;
                        case 1:
                            mts.add(tank);
                            break;
                        case 2:
                            hts.add(tank);
                            break;
                        case 3:
                            tds.add(tank);
                            break;
                        case 4:
                            spgs.add(tank);
                            break;
                    }

                }

            }

            tanksvo.setTanksTypes(tanksTypes);
            tanksvo.setLts(lts);
            tanksvo.setMts(mts);
            tanksvo.setHts(hts);
            tanksvo.setTds(tds);
            tanksvo.setSpgs(spgs);

            woter.setTanks(tanksvo);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return woter;
    }


    /**
     * 处理军团信息
     *
     * @return
     */
    public static ClanInfoUsed handleClaninfo(String s) {

        Document doc = Jsoup.parse(s);
        Element link = doc.select("img").first();

        Element clanPosition = doc.select(".number").first();
        Element clanDays = doc.select(".number").last();

        ClanInfoUsed c = new ClanInfoUsed();
        c.setClanDescription(link.attr("alt"));
        c.setClanImgSrc(link.attr("src"));
        c.setClanPosition(clanPosition.text());
        c.setClanDays(clanDays.text());

        return c;
    }
}
