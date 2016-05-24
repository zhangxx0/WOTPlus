package com.xinxin.wotplus.util.mapper;

import android.util.Log;

import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.util.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.functions.Func1;

/**
 * Created by xinxin on 2016/5/23.
 * 为 XvmAllInfo 添加近日数据 map
 */
public class XvmAllInfoToDayMap implements Func1<XvmAllInfo, XvmAllInfo> {

    private static XvmAllInfoToDayMap INSTANCE = new XvmAllInfoToDayMap();

    public XvmAllInfoToDayMap() {
    }

    public static XvmAllInfoToDayMap getInstance() {
        return INSTANCE;
    }

    @Override
    public XvmAllInfo call(XvmAllInfo xvmAllInfo) {

        XvmAllInfo reXvmAllInfo = new XvmAllInfo();
        reXvmAllInfo = xvmAllInfo;
        Map daymap = new HashMap();

        List<XvmMainInfo.DaylistEntity> daylist = xvmAllInfo.getXvmMainInfo().getDaylist();

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
        Map daymaps = new HashMap();

        for (int i = 0; i < daydate.size(); i++) {
            Log.d("daydate", daydate.get(i).toString());
            String daydates = daydate.get(i).toString();
            XvmMainInfo.DaylistEntity dt = null;

            // 内循环遍历今日数据list
            for (int j = 0; j < daylist.size(); j++) {

                XvmMainInfo.DaylistEntity de = daylist.get(j);
                XvmMainInfo.DaylistEntity.InsertDateEntity dateEntity = de.getInsert_date();
                String formatdate = CommonUtil.formatDate(dateEntity);

                if (formatdate.equals(daydates)) {
                    // 第一条相同的赋值
                    if (dt == null) {
                        dt = de;
                    } else {
                        // 再有相同的累加
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
                        dt.setWins(dt.getWins()+de.getWins());
                        dt.setXp(dt.getXp()+de.getXp());
//                        dt
                    }
                }

            }

            daymaps.put(daydates, dt);
        }

        for (int i = 0; i < daylist.size(); i++) {
            XvmMainInfo.DaylistEntity de = daylist.get(i);
            XvmMainInfo.DaylistEntity.InsertDateEntity dateEntity = de.getInsert_date();
            String formatdate = CommonUtil.formatDate(dateEntity);
            if (!daymap.containsKey(formatdate)) {
                // js中是给map[date]赋一个空值
                // daymap.put(formatdate, "");
            }
            daymap.put(formatdate, de);

            // 与活跃战车列表对应
            // // 应该是点击坦克时展示的该坦克近期战斗数据
            // 重新设计一个类，还是用map？ 未解决
            Map tankdaymap = new HashMap();
//            if (tanksMap.containsKey(de.getId().getVehicleTypeCd()+"")) {
//                xvmMainInfo.getTanklist();
//            }

        }
        System.out.println("近日战绩数量：" + daymap.size());
        System.out.println("近日战绩map：" + daymap);

        reXvmAllInfo.setDaymap(daymap);

        return reXvmAllInfo;
    }
}
