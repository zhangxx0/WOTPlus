package com.xinxin.wotplus.util.mapper;

import com.google.gson.Gson;
import com.xinxin.wotplus.model.TanksType;
import com.xinxin.wotplus.model.TypesAndCountry;
import com.xinxin.wotplus.model.TypesAndCountryNewVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by xinxin on 2016/10/15.
 * 将获取到的json串修正为可识别格式，并重组数据到List<TanksType>
 */

public class TankTypeJsonCorrectAndToVoMapper implements Func1<ResponseBody, List<TanksType>> {
    private static TankTypeJsonCorrectAndToVoMapper INSTANCE = new TankTypeJsonCorrectAndToVoMapper();

    public TankTypeJsonCorrectAndToVoMapper() {
    }

    public static TankTypeJsonCorrectAndToVoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public List<TanksType> call(ResponseBody responseBody) {

        List<TanksType> tanksTypes = new ArrayList<TanksType>();
        TypesAndCountryNewVO newVO = new TypesAndCountryNewVO();
        if (responseBody != null) {
            try {
                // 将json串中的“-”去掉
                String strRead = responseBody.string().replace("-", "");

                // 然后将处理后的json转换为TypesAndCountryNewVO
                Gson gson = new Gson();
                newVO = gson.fromJson(strRead, TypesAndCountryNewVO.class);

                // 最后组成List<TanksType>
                TanksType lt = new TanksType();
                TanksType mt = new TanksType();
                TanksType ht = new TanksType();
                TanksType td = new TanksType();
                TanksType spg = new TanksType();

                TypesAndCountryNewVO.DataBean.ResultsBean.VehiclesByTypesBean v = newVO.getData().getResults().getVehicles_by_types();
                lt.setTanksTypeName(v.getLightTank().getLocalized_name());
                lt.setTanksTypeNum(v.getLightTank().getVehicles_count() + "");
                lt.setTanksTypeFightNum(v.getLightTank().getBattles_count() + "");
                lt.setTanksTypeWinRating(v.getLightTank().getWins_count() * 100 / v.getLightTank().getBattles_count() + "%");
                lt.setTanksTypeBadgeNum(v.getLightTank().getMastery_badges_count() + "");

                mt.setTanksTypeName(v.getMediumTank().getLocalized_name());
                mt.setTanksTypeNum(v.getMediumTank().getVehicles_count() + "");
                mt.setTanksTypeFightNum(v.getMediumTank().getBattles_count() + "");
                mt.setTanksTypeWinRating(v.getMediumTank().getWins_count() * 100 / v.getMediumTank().getBattles_count() + "%");
                mt.setTanksTypeBadgeNum(v.getMediumTank().getMastery_badges_count() + "");

                ht.setTanksTypeName(v.getHeavyTank().getLocalized_name());
                ht.setTanksTypeNum(v.getHeavyTank().getVehicles_count() + "");
                ht.setTanksTypeFightNum(v.getHeavyTank().getBattles_count() + "");
                ht.setTanksTypeWinRating(v.getHeavyTank().getWins_count() * 100 / v.getHeavyTank().getBattles_count() + "%");
                ht.setTanksTypeBadgeNum(v.getHeavyTank().getMastery_badges_count() + "");

                td.setTanksTypeName(v.getATSPG().getLocalized_name());
                td.setTanksTypeNum(v.getATSPG().getVehicles_count() + "");
                td.setTanksTypeFightNum(v.getATSPG().getBattles_count() + "");
                td.setTanksTypeWinRating(v.getATSPG().getWins_count() * 100 / v.getATSPG().getBattles_count() + "%");
                td.setTanksTypeBadgeNum(v.getATSPG().getMastery_badges_count() + "");

                spg.setTanksTypeName(v.getSPG().getLocalized_name());
                spg.setTanksTypeNum(v.getSPG().getVehicles_count() + "");
                spg.setTanksTypeFightNum(v.getSPG().getBattles_count() + "");
                spg.setTanksTypeWinRating(v.getSPG().getWins_count() * 100 / v.getSPG().getBattles_count() + "%");
                spg.setTanksTypeBadgeNum(v.getSPG().getMastery_badges_count() + "");

                tanksTypes.add(lt);
                tanksTypes.add(mt);
                tanksTypes.add(ht);
                tanksTypes.add(td);
                tanksTypes.add(spg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tanksTypes;
    }
}
