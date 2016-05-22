package com.xinxin.wotplus.util.mapper;

import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.util.CommonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.functions.Func1;

/**
 * Created by xinxin on 2016/5/23.
 */
public class XvmAllInfoToDayMap implements Func1<XvmAllInfo,Map> {

    private static XvmAllInfoToDayMap INSTANCE = new XvmAllInfoToDayMap();

    public XvmAllInfoToDayMap() {
    }

    public static XvmAllInfoToDayMap getInstance() {
        return INSTANCE;
    }

    @Override
    public Map call(XvmAllInfo xvmAllInfo) {
        Map daymap = new HashMap();

        List<XvmMainInfo.DaylistEntity> daylist = xvmAllInfo.getXvmMainInfo().getDaylist();

        for (int i = 0; i < daylist.size(); i++) {
            XvmMainInfo.DaylistEntity daylistEntity = daylist.get(i);
            XvmMainInfo.DaylistEntity.InsertDateEntity dateEntity = daylistEntity.getInsert_date();
            String formatdate = CommonUtil.formatDate(dateEntity);
            if (!daymap.containsKey(formatdate)) {
                // js中是给map[date]赋一个空值
                // daymap.put(formatdate, "");
            }
            daymap.put(formatdate, daylistEntity);

            // 与活跃战车列表对应
            // // 应该是点击坦克时展示的该坦克近期战斗数据
            // 重新设计一个类，还是用map？ 未解决
            Map tankdaymap = new HashMap();
//            if (tanksMap.containsKey(daylistEntity.getId().getVehicleTypeCd()+"")) {
//                xvmMainInfo.getTanklist();
//            }

        }
        System.out.println("近日战绩数量：" + daymap.size());
        System.out.println("近日战绩map："+daymap);


        return daymap;
    }
}
