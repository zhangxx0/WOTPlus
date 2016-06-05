package com.xinxin.wotplus.util.mapper;

import com.xinxin.wotplus.model.DaylistEntityForRecent;
import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.util.CommonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rx.functions.Func1;

/**
 * Created by xinxin on 2016/5/29.
 */
public class XvmThirtyInfoToDayMap implements Func1<XvmAllInfo, XvmAllInfo> {

    private static XvmThirtyInfoToDayMap INSTANCE = new XvmThirtyInfoToDayMap();

    public XvmThirtyInfoToDayMap() {
    }

    public static XvmThirtyInfoToDayMap getInstance() {
        return INSTANCE;
    }


    @Override
    public XvmAllInfo call(XvmAllInfo xvmAllInfo) {

        XvmAllInfo reXvmAllInfo = new XvmAllInfo();
        List<XvmMainInfo.DaylistEntity> daylist = xvmAllInfo.getXvmMainInfo().getDaylist();
        Map tankmap = xvmAllInfo.getTanks();

        // 先取出去重的时间（日期）list,
        List daydate = new ArrayList();

        List daydatetemp = new ArrayList();
        for (int i = 0; i < daylist.size(); i++) {
            XvmMainInfo.DaylistEntity de = daylist.get(i);
            XvmMainInfo.DaylistEntity.InsertDateEntity dateEntity = de.getInsert_date();
            String formatdate = CommonUtil.formatDate(dateEntity);
            daydatetemp.add(formatdate);
        }
        Iterator<String> it = daydatetemp.iterator();
        while (it.hasNext()) {
            String a = it.next();
            if (daydate.contains(a)) {
                it.remove();
            } else {
                daydate.add(a);
            }
        }

        // 遍历去重之后的日期
        Map daymaps = new TreeMap<String, DaylistEntityForRecent>(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                int flag = 0;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d1 = sdf.parse(lhs);
                    Date d2 = sdf.parse(rhs);
                    if (d1.before(d2)) {
                        flag = 1;
                    } else if (d1.equals(d2)) {
                        flag = 0;
                    } else {
                        flag = -1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return flag;
            }
        });
        for (int i = 0; i < daydate.size(); i++) {
            //Log.d("daydate", daydate.get(i).toString());
            String daydates = daydate.get(i).toString();

            // 使用的一个新的类，因为要加一个新的属性weight，而原先的类不能破坏
            DaylistEntityForRecent dt = new DaylistEntityForRecent();

            // 内循环遍历今日数据list
            for (int j = 0; j < daylist.size(); j++) {

                XvmMainInfo.DaylistEntity de = daylist.get(j);
                XvmMainInfo.DaylistEntity.InsertDateEntity dateEntity = de.getInsert_date();
                String formatdate = CommonUtil.formatDate(dateEntity);

                if (formatdate.equals(daydates)) {
                    dt.setAssist(dt.getAssist() + de.getAssist());
                    dt.setBattles(dt.getBattles() + de.getBattles());
                    dt.setCapture(dt.getCapture() + de.getCapture());
                    dt.setDamage(dt.getDamage() + de.getDamage());
                    dt.setDaypower(dt.getDaypower() + de.getDaypower());
                    dt.setDefense(dt.getDefense() + de.getDefense());
                    dt.setDraws(dt.getDraws() + de.getDraws());
                    dt.setFrags(dt.getFrags() + de.getFrags());
                    dt.setMark1(dt.getMark1() + de.getMark1());
                    dt.setMark2(dt.getMark2() + de.getMark2());
                    dt.setMark3(dt.getMark3() + de.getMark3());
                    dt.setMark4(dt.getMark4() + de.getMark4());
                    dt.setSpotted(dt.getSpotted() + de.getSpotted());
                    dt.setTeambattles(dt.getTeambattles() + de.getTeambattles());
                    dt.setTeamwins(dt.getTeamwins() + de.getTeamwins());
                    dt.setWinpower(dt.getWinpower() + de.getWinpower());
                    dt.setWins(dt.getWins() + de.getWins());
                    dt.setXp(dt.getXp() + de.getXp());

                    TankInfo tank = (TankInfo) tankmap.get(de.getId().getVehicleTypeCd() + "");
                    dt.setWeight(CommonUtil.addDouble(dt.getWeight(), CommonUtil.getTankWeight(tank) * de.getBattles()));
                }

            }

            daymaps.put(daydates, dt);

        }

        reXvmAllInfo.setDaymap(daymaps);
        System.out.println("30日战绩map：" + daymaps);

        return reXvmAllInfo;
    }
}
