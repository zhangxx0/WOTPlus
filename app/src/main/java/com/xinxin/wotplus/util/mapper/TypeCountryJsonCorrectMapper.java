package com.xinxin.wotplus.util.mapper;

import com.google.gson.Gson;
import com.xinxin.wotplus.model.AchieveNew;
import com.xinxin.wotplus.model.TypesAndCountry;
import com.xinxin.wotplus.model.TypesAndCountryNewVO;
import com.xinxin.wotplus.model.Woter;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by xinxin on 2016/10/15.
 * 将获取到的json串修正为可识别格式，并重组数据到TypesAndCountry
 */

public class TypeCountryJsonCorrectMapper implements Func1<ResponseBody, TypesAndCountry> {
    private static TypeCountryJsonCorrectMapper INSTANCE = new TypeCountryJsonCorrectMapper();

    public TypeCountryJsonCorrectMapper() {
    }

    public static TypeCountryJsonCorrectMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public TypesAndCountry call(ResponseBody responseBody) {

        TypesAndCountry typesAndCountry = new TypesAndCountry();
        TypesAndCountryNewVO newVO = new TypesAndCountryNewVO();
        if (responseBody != null) {
            try {
                // 将json串中的“-”去掉
                String strRead = responseBody.string().replace("-", "");

                // 然后将处理后的json转换为TypesAndCountryNewVO
                Gson gson = new Gson();
                newVO = gson.fromJson(strRead, TypesAndCountryNewVO.class);

                // 最后将TypesAndCountryNewVO 转换为 TypesAndCountry
                TypesAndCountryNewVO.DataBean.ResultsBean.VehiclesByTypesBean v = newVO.getData().getResults().getVehicles_by_types();
                int allBattleCount = 0;
                allBattleCount = v.getATSPG().getBattles_count() + v.getHeavyTank().getBattles_count() + v.getLightTank().getBattles_count() + v.getMediumTank().getBattles_count() + v.getSPG().getBattles_count();
                typesAndCountry.setPercentageLt(v.getLightTank().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageMt(v.getMediumTank().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageHt(v.getHeavyTank().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageTd(v.getATSPG().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageSpg(v.getSPG().getBattles_count() * 100 / allBattleCount + "%");

                typesAndCountry.setNumsLt(v.getLightTank().getBattles_count() + "");
                typesAndCountry.setNumsMt(v.getMediumTank().getBattles_count() + "");
                typesAndCountry.setNumsHt(v.getHeavyTank().getBattles_count() + "");
                typesAndCountry.setNumsTd(v.getATSPG().getBattles_count() + "");
                typesAndCountry.setNumsSpg(v.getSPG().getBattles_count() + "");

                TypesAndCountryNewVO.DataBean.ResultsBean.VehiclesByNationsBean n = newVO.getData().getResults().getVehicles_by_nations();
                typesAndCountry.setPercentageUssr(n.getUssr().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageGe(n.getGermany().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageUsa(n.getUsa().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageFr(n.getFrance().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageUk(n.getUk().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageCn(n.getChina().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageJa(n.getJapan().getBattles_count() * 100 / allBattleCount + "%");
                typesAndCountry.setPercentageCz(n.getCzech().getBattles_count() * 100 / allBattleCount + "%");

                typesAndCountry.setNumsUssr(n.getUssr().getBattles_count() + "");
                typesAndCountry.setNumsGe(n.getGermany().getBattles_count() + "");
                typesAndCountry.setNumsUsa(n.getUsa().getBattles_count() + "");
                typesAndCountry.setNumsFr(n.getFrance().getBattles_count() + "");
                typesAndCountry.setNumsUk(n.getUk().getBattles_count() + "");
                typesAndCountry.setNumsCn(n.getChina().getBattles_count() + "");
                typesAndCountry.setNumsJa(n.getJapan().getBattles_count() + "");
                typesAndCountry.setNumsCz(n.getCzech().getBattles_count() + "");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return typesAndCountry;
    }
}
