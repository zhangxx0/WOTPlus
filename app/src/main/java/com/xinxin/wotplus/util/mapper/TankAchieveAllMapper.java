package com.xinxin.wotplus.util.mapper;

import com.xinxin.wotplus.model.AchieveNew;
import com.xinxin.wotplus.model.TankAchieveNew;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.functions.Func1;

/**
 * Created by xinxin on 2016/6/20.
 * 坦克成就信息整合
 * abandon
 */
public class TankAchieveAllMapper implements Func1<TankAchieveNew, TankAchieveNew> {
    private static TankAchieveAllMapper INSTANCE = new TankAchieveAllMapper();

    public TankAchieveAllMapper() {
    }

    public static TankAchieveAllMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public TankAchieveNew call(TankAchieveNew tankAchieveNew) {

        // （3）需要组合成就信息与成就数量

        // 成就信息源
        List<AchieveNew> achieveNews = tankAchieveNew.getAchieveNews();
        // 成就数量信息map
        Map achievemap = tankAchieveNew.getAchieveMap();

        // 重组之后的获取成就列表；
        List<AchieveNew.AchievementsEntity> rebuildTankAchieveList = new ArrayList<>();

        // 根据成就数量map找出战车所获取到的成就
        if (achieveNews.size() > 0) {
            // 遍历7个大类
            for (int i = 0; i < achieveNews.size(); i++) {

                List<AchieveNew.AchievementsEntity> achievements = achieveNews.get(i).getAchievements();
                // 遍历每个大类中的所有成就
                for (int j = 0; j < achievements.size(); j++) {

                    String achieveName = achievements.get(j).getName();
                    // 遍历map找出存在的成就，并set获取数量
                    if (achievemap.containsKey(achieveName)) {
                        // 设置勋章数目
                        achievements.get(j).setNums(achievemap.get(achieveName) + "");
                        rebuildTankAchieveList.add(achievements.get(j));
                        // 重组之后的勋章数量好像有问题，比成就map还多！
                    }
                }
            }

        }
        tankAchieveNew.setRebuildTankAchieveList(rebuildTankAchieveList);

        return tankAchieveNew;
    }
}
