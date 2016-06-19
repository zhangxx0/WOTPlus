package com.xinxin.wotplus.util.mapper;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xinxin.wotplus.MyApplication;
import com.xinxin.wotplus.model.AchieveNew;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.util.PreferenceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by xinxin on 2016/6/19.
 * 将获取到的成就json串转换成Map
 */
public class AchieveJsonToMapMapper implements Func1<ResponseBody, List<AchieveNew>> {

    private static AchieveJsonToMapMapper INSTANCE = new AchieveJsonToMapMapper();

    public AchieveJsonToMapMapper() {
    }

    public static AchieveJsonToMapMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public List<AchieveNew> call(ResponseBody responseBody) {
        Map map = new HashMap();
        Woter woter;
        List<AchieveNew> newAchievements = new ArrayList<AchieveNew>();

        // 从CharedPreference中获取woter
        String woterString = PreferenceUtils.getCustomPrefString(MyApplication.getContext(), "woter", "woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
            if (woter != null) {
                // 获取成就的七个列表信息
                newAchievements = woter.getNewAchievements();
            }
        }


        if (responseBody != null) {
            try {
                String strRead = responseBody.string().replace(" ", "").replace("\"", "");

                String mapString = strRead.substring(strRead.lastIndexOf("{") + 1, strRead.length() - 5);

                String[] achieveArray = mapString.split(",");

                for (int i = 0; i < achieveArray.length; i++) {
                    String[] subArray = achieveArray[i].split(":");
                    map.put(subArray[0], subArray[1]);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Log.d("map", map.toString());

        // 将勋章数量对应到相对应的勋章信息上
        if (newAchievements.size() > 0) {
            // 遍历7个大类
            for (int i = 0; i < newAchievements.size(); i++) {

                List<AchieveNew.AchievementsEntity> achievements = newAchievements.get(i).getAchievements();
                // 遍历每个大类中的所有成就
                for (int j = 0; j < achievements.size(); j++) {

                    int achieveId = achievements.get(j).getId();
                    String achieveName = achievements.get(j).getName();
                    // 遍历map找出相同
                    if (map.containsKey(achieveName)) {
                        // 设置勋章数目
                        newAchievements.get(i).getAchievements().get(j).setNums(map.get(achieveName) + "");
                    }

                }
            }

        }


        return newAchievements;
    }
}
