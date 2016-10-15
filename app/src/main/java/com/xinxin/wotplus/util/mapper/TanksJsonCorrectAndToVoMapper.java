package com.xinxin.wotplus.util.mapper;

import com.google.gson.Gson;
import com.xinxin.wotplus.model.Tank;
import com.xinxin.wotplus.model.Tanks;
import com.xinxin.wotplus.model.TanksNew;
import com.xinxin.wotplus.model.TanksType;
import com.xinxin.wotplus.model.TypesAndCountryNewVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by xinxin on 2016/10/15.
 * 将获取到的tank列表json串修正为可识别格式，替换相应的属性名，并重组数据到Tanks
 */

public class TanksJsonCorrectAndToVoMapper implements Func1<ResponseBody, Tanks> {
    private static TanksJsonCorrectAndToVoMapper INSTANCE = new TanksJsonCorrectAndToVoMapper();

    public TanksJsonCorrectAndToVoMapper() {
    }

    public static TanksJsonCorrectAndToVoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Tanks call(ResponseBody responseBody) {
        Tanks tanksvo = new Tanks();
        TanksNew tanksNew = new TanksNew();
        if (responseBody != null) {
            try {
                // 将json串中的不固定属性名统一转为tank
                String strRead = responseBody.string().replace("AT-SPG", "tank").replace("mediumTank", "tank").replace("heavyTank", "tank").replace("lightTank", "tank").replace("SPG", "tank");

                // 然后将处理后的json转换为
                Gson gson = new Gson();
                tanksNew = gson.fromJson(strRead, TanksNew.class);

                // 最后组成Tanks，这里只组装里面的list部分，type部分不处理
                List<Tank> lts = new ArrayList<Tank>();

                List<TanksNew.DataBean.ResultsBean.TankBean> tankBeanList = tanksNew.getData().getResults().getTank();
                for (int i = 0; i < tankBeanList.size(); i++) {
                    TanksNew.DataBean.ResultsBean.TankBean tankBean = tankBeanList.get(i);

                    Tank tank = new Tank();
                    tank.setTankCountry(tankBean.getNation());
                    tank.setTankLevel(tankBean.getTier() + "");
                    tank.setTankIcon("http:" + tankBean.getIcon());
                    tank.setTankName(tankBean.getLocalized_name());
                    tank.setTankFightNum(tankBean.getBattles_count() + "");
                    tank.setTankWinRate(tankBean.getWins_count() * 100 / tankBean.getBattles_count() + "%");
                    tank.setTankBadge(tankBean.getMark_of_mastery() + "");
                    tank.setTankId(tankBean.getVehicle_cd() + "");

                    lts.add(tank);
                }

                tanksvo.setLts(lts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tanksvo;
    }
}
