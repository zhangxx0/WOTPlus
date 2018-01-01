package com.xinxin.wotplus.util.mapper;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xinxin.wotplus.MyApplication;
import com.xinxin.wotplus.model.AchieveNew;
import com.xinxin.wotplus.model.TankAchieveNew;
import com.xinxin.wotplus.model.TankAchieveSummary;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.dto.AchievementsAndResponsebody;
import com.xinxin.wotplus.model.kongzhong.Achievements;
import com.xinxin.wotplus.util.PreferenceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by xinxin on 2016/6/20.
 */
public class TankJsonToMapMapper implements Func1<AchievementsAndResponsebody, TankAchieveNew> {

    private static TankJsonToMapMapper INSTANCE = new TankJsonToMapMapper();

    public TankJsonToMapMapper() {
    }

    public static TankJsonToMapMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public TankAchieveNew call(AchievementsAndResponsebody achievementsAndResponsebody) {
        TankAchieveNew tankAchieveNew = new TankAchieveNew();

        // （1）成就数量Map
        Map achieveMap = new HashMap();
        // （2）成就信息总结VO
        TankAchieveSummary summary;
        // （3）需要组合成就信息与成就数量

        if (achievementsAndResponsebody.getResponseBody() != null) {
            try {

                // （1）
                String strRead0 = achievementsAndResponsebody.getResponseBody().string().replace(" ", "");
                String strRead = strRead0.replace("\"", "");

                String mapString = strRead.substring(0, strRead.lastIndexOf("{") - 10);
                String mapString2 = mapString.substring(mapString.lastIndexOf("{") + 1);

                String[] achieveArray = mapString2.split(",");

                for (int i = 0; i < achieveArray.length; i++) {
                    String[] subArray = achieveArray[i].split(":");
                    achieveMap.put(subArray[0], subArray[1]);
                }

                tankAchieveNew.setAchieveMap(achieveMap);

                // （2）
                String voString = strRead0.substring(strRead0.lastIndexOf("{") - 10, strRead0.length() - 4);
                String voString2 = "{" + voString;

                Gson gson = new Gson();
                summary = gson.fromJson(voString2, TankAchieveSummary.class);

                tankAchieveNew.setTankAchieveSummary(summary);

                // （3）
                List<Achievements.DataBean.AchDataBean> achDataBeanList = achievementsAndResponsebody.getAchievements().getData().getData();

                // 重组之后的获取成就列表；
                List<List<String>> rebuildTankAchieveList = new ArrayList<>();

                // 根据成就数量map找出战车所获取到的成就
                if (achDataBeanList.size() > 0) {
                    // 遍历7个大类
                    for (int i = 0; i < achDataBeanList.size(); i++) {

                        List<List<String>> achievements = achDataBeanList.get(i).getAchievements();
                        // 遍历每个大类中的所有成就
                        for (int j = 0; j < achievements.size(); j++) {

                            String achieveName = achievements.get(j).get(0);
                            // 遍历map找出存在的成就，并set获取数量
                            if (achieveMap.containsKey(achieveName)) {
                                // 设置勋章数目
                                achievements.get(j).set(10,achieveMap.get(achieveName) + "");
                                if (achievements.get(j).get(10) != null) {
                                    /*String number = achievements.get(j).get(10);
                                    if (number.intValue() == Integer.valueOf(achieveMap.get(achieveName)+"") &&
                                            !"markOfMastery".equals(achievements.get(j).getName())) {
                                        rebuildTankAchieveList.add(achievements.get(j));
                                    }*/
                                    rebuildTankAchieveList.add(achievements.get(j));

                                } else {
                                    rebuildTankAchieveList.add(achievements.get(j));
                                }
                                // 重组之后的勋章数量好像有问题，比成就map还多！
                            }
                        }
                    }

                }
                tankAchieveNew.setRebuildTankAchieveListNew(rebuildTankAchieveList);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return tankAchieveNew;
    }
}
