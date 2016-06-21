package com.xinxin.wotplus.util.mapper;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xinxin.wotplus.model.XvmTankTopSingleVO;
import com.xinxin.wotplus.model.XvmTankTopVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by xinxin on 2016/6/21.
 * 坦克榜单数据转换为相应实体类
 */
public class XvmTankTopToVoMapper implements Func1<ResponseBody,List<XvmTankTopVO>> {

    private static XvmTankTopToVoMapper INSTANCE = new XvmTankTopToVoMapper();

    public XvmTankTopToVoMapper() {
    }

    public static XvmTankTopToVoMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public List<XvmTankTopVO> call(ResponseBody responseBody) {
        List<XvmTankTopVO> topList = new ArrayList<>();

        if (responseBody != null) {
            try {
                String tankTopSourse = responseBody.string();

                String subSourse = tankTopSourse.substring(1, tankTopSourse.length() - 1);

                if (!TextUtils.isEmpty(subSourse)) {
                    String x = "\\},";
                    String[] tankTopArray = subSourse.split(x);

                    // 组装tanktop列表
                    for (int i = 0; i < tankTopArray.length; i++) {

                        Gson gson = new Gson();
                        XvmTankTopVO xvmTankTopVO = new XvmTankTopVO();
                        XvmTankTopSingleVO top = new XvmTankTopSingleVO();

                        // 最后一个的末尾不用补“}”需特殊处理
                        if (i == tankTopArray.length - 1) {
                            String tankTopStr = tankTopArray[i];
                            String tankId = tankTopStr.trim().substring(1, tankTopStr.lastIndexOf("{") - 2);
                            String tankTopInfoStr = tankTopStr.substring(tankTopStr.lastIndexOf("{")).trim();

                            top = gson.fromJson(tankTopInfoStr, XvmTankTopSingleVO.class);

                            xvmTankTopVO.setTankid(Integer.parseInt(tankId));
                            xvmTankTopVO.setSingleVO(top);
                            topList.add(xvmTankTopVO);

                        } else {
                            String tankDictStr = tankTopArray[i] + "}";

                            String tankId = tankDictStr.trim().substring(1, tankDictStr.lastIndexOf("{") - 2);
                            String tankInfoStr = tankDictStr.substring(tankDictStr.lastIndexOf("{")).trim();

                            top = gson.fromJson(tankInfoStr, XvmTankTopSingleVO.class);
                            xvmTankTopVO.setTankid(Integer.parseInt(tankId));
                            xvmTankTopVO.setSingleVO(top);
                            topList.add(xvmTankTopVO);
                        }

                    }

                    // 列表排序
                    // 需要tank.js，在XvmTankTopSortMapper中完成

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return topList;
    }
}
