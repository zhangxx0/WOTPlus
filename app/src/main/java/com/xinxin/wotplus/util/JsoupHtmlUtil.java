package com.xinxin.wotplus.util;

import com.google.gson.Gson;
import com.xinxin.wotplus.model.ClanInfoUsed;
import com.xinxin.wotplus.model.Tanks;
import com.xinxin.wotplus.model.TypesAndCountry;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.kongzhong.UserData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
             * 账号和军团信息
             */
            // 获取script代码段（使用jsoup）
            element = doc.select(".content-wrapper").first();
            element = element.getElementsByTag("script").first();
            // 获取USER_DATA js代码段（使用正则）
            String str = element.html().replace("\n", "");
            str = str.replace(" ","");
            String pattern = "USER_DATA=\\{\"(.*?)\\}\\}";

            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(str);
            if(m.find())
            {
                String USER_DATA = m.group();
                String USER_DATA_JSON = USER_DATA.replace("USER_DATA=", "");
                UserData userData = new Gson().fromJson(USER_DATA_JSON, UserData.class);

                String nickname = userData.getNickname();
                String reg_timestamp = String.valueOf(userData.getReg_timestamp());
                String lastBattleTime = String.valueOf(userData.getSummary().getLast_battle_at());
                UserData.ClanInfoBean clan_info = userData.getClan_info();

                woter.setWoterName(nickname);
                woter.setTimeStamp(reg_timestamp);
                woter.setLastBattleTime(lastBattleTime);

                woter.setPersonRanking("");
                woter.setPersonWin("");
                woter.setPersonFight("");
                woter.setPersonExp("");
                woter.setPersonDmg("");

                // 击杀／死亡率 伤害原因／收到
//                woter.setKillDeathRate();
//                woter.setDmgRecRate();
//                woter.setKillDeathNum();
//                woter.setDmgRecNum();
            }


            // （2）更改为动态接口获取之后的代码
            // 在页面调用接口，并给下面这个类赋值；
            TypesAndCountry typesAndCountry = new TypesAndCountry();
            woter.setTypesAndCountry(typesAndCountry);

            /**
             * （4）等级信息
             *  等级信息在html中获取不到，因此，在等级页面再做一个请求；
             */
            /**
             * （5）战车信息 NEW
             *  官网修改了战车信息的展示，现在的战车信息不在html页面中了；
             *  因此，相应的请求转移到对应的fragment中去，不再在此处解析
             *  add date 2016年10月15日11:13:29
             */
            Tanks tanksvo = new Tanks();
            woter.setTanks(tanksvo);

        }catch (Exception e) {
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
