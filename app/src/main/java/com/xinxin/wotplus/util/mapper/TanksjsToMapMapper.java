package com.xinxin.wotplus.util.mapper;

import com.google.gson.Gson;
import com.xinxin.wotplus.model.TankInfo;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by xinxin on 2016/5/22.
 * tanks.js -> map
 */
public class TanksjsToMapMapper implements Func1<ResponseBody, Map> {

    private static TanksjsToMapMapper INSTANCE = new TanksjsToMapMapper();

    public TanksjsToMapMapper() {
    }

    public static TanksjsToMapMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Map call(ResponseBody responseBody) {
        Map map = new HashMap();

        if (responseBody != null) {
            String tankssourse = "";
            try {
                // responseBody.string()输出结果：System.out: };
                // 可能是由于换行符的原因，不能直接用下面的.string方法；不是这个原因，因此仍然使用string()方法；
//                byte[] byBuffer = responseBody.bytes();
//                String strRead = new String(byBuffer);

                String strRead = responseBody.string();
                String realSourse = strRead.substring(14, strRead.length() - 2);

                // 还真不是换行符的问题(之前只使用一个},后来加上两个\\)，，，escaping `}` not needed in Java
                // http://stackoverflow.com/questions/13508992/android-syntax-error-in-regexp-pattern
                String x = "\\},";
                String[] stringArray = realSourse.replaceAll("\r|\n|\t", "").split(x);

                for (int i = 0; i < stringArray.length; i++) {

                    Gson gson = new Gson();
                    TankInfo tankInfo = new TankInfo();

                    // 最后一个的末尾不用补“}”需特殊处理
                    if (i == stringArray.length - 1) {
                        String tankDictStr = stringArray[i];
                        String tankId = tankDictStr.trim().substring(1, tankDictStr.lastIndexOf("{") - 2);
                        String tankInfoStr = tankDictStr.substring(tankDictStr.lastIndexOf("{")).trim();

                        tankInfo = gson.fromJson(tankInfoStr, TankInfo.class);
                        map.put(tankId, tankInfo);

                    } else {
                        String tankDictStr = stringArray[i] + "}";

                        String tankId = tankDictStr.trim().substring(1, tankDictStr.lastIndexOf("{") - 2);
                        String tankInfoStr = tankDictStr.substring(tankDictStr.lastIndexOf("{")).trim();

                        tankInfo = gson.fromJson(tankInfoStr, TankInfo.class);
                        map.put(tankId, tankInfo);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
