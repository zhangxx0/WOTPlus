package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.xinxin.wotplus.MyApplication;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.TypesAndCountry;
import com.xinxin.wotplus.model.TypesAndCountryNewVO;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.AchieveJsonToMapMapper;
import com.xinxin.wotplus.util.mapper.TypeCountryJsonCorrectMapper;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import java.util.ArrayList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/4/4.
 * 类型与国家Fragment
 */
public class TypeCountryFragment extends BaseFragment {

    public static final String LANG = "zh-cn";

    private Woter woter;

    private PieChart type_chart;
    private PieChart country_charts;

    private DeathWheelProgressDialog deathWheelProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_type_country, container, false);

        // 从CharedPreference中获取woter
        final String woterString = PreferenceUtils.getCustomPrefString(getActivity(), "woter", "woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
            Log.d("GsonWoter", woter.toString());
        }

        type_chart = (PieChart) view.findViewById(R.id.type_chart);
        country_charts = (PieChart) view.findViewById(R.id.country_chart);

        // 请求新的API，获取数据，并添加到woter中去
        Observer<TypesAndCountry> typesAndCountryObserver = new Observer<TypesAndCountry>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(getView(), "获取类型与国家信息出错！", Snackbar.LENGTH_LONG).show();
                deathWheelProgressDialog.dismiss();
            }

            @Override
            public void onNext(TypesAndCountry tc) {

                // 处理数据
                woter.setTypesAndCountry(tc);

                PieData mpieData = getTypeData();
                type_chart.setHoleRadius(60f);  //半径
                type_chart.setTransparentCircleRadius(64f);
                type_chart.setCenterText(woter.getPersonFight());
                type_chart.setDrawHoleEnabled(true);
                type_chart.setRotationAngle(90);
                type_chart.setRotationEnabled(true);
                type_chart.setUsePercentValues(false);
                type_chart.setDescription("");
                type_chart.setData(mpieData);
                Legend mLegend = type_chart.getLegend();  //设置比例图
                mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
                // mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
                mLegend.setXEntrySpace(7f);
                mLegend.setYEntrySpace(5f);

                PieData mpieData2 = getCountryData();
                country_charts.setHoleRadius(60f);  //半径
                country_charts.setTransparentCircleRadius(64f);
                country_charts.setCenterText(woter.getPersonFight());
                country_charts.setDrawHoleEnabled(true);
                country_charts.setRotationAngle(90);
                country_charts.setRotationEnabled(true);
                country_charts.setUsePercentValues(false);
                country_charts.setDescription("");
                country_charts.setData(mpieData2);
                Legend mLegend2 = country_charts.getLegend();  //设置比例图
                mLegend2.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
                mLegend2.setXEntrySpace(7f);
                mLegend2.setYEntrySpace(5f);

                type_chart.invalidate();
                country_charts.invalidate();
                deathWheelProgressDialog.dismiss();

            }
        };
        deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(getActivity());
        deathWheelProgressDialog.show();
        String woterId = PreferenceUtils.getCustomPrefString(getActivity(), "woterId", "woterId", "");
        // 区分南北区
        String region = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "region", "");
        subscription = Network.getTypeCountryInfo(region)
                .getTypeCountryInfo(woterId, LANG)
                .map(TypeCountryJsonCorrectMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(typesAndCountryObserver);

        return view;

    }


    /**
     * 获取坦克类型piechart数据
     *
     * @return
     */
    private PieData getTypeData() {
        TypesAndCountry tc = woter.getTypesAndCountry();

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
        xValues.add("LT");
        xValues.add("MT");
        xValues.add("HT");
        xValues.add("TD");
        xValues.add("SPG");

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        /**
         * 使用比值
         */
        // 计算比值
        // 使用百分比
//        float quarterly1 = Float.parseFloat(tc.getPercentageLt().toString().replace("%", ""));
//        float quarterly2 = Float.parseFloat(tc.getPercentageMt().toString().replace("%", ""));
//        float quarterly3 = Float.parseFloat(tc.getPercentageHt().toString().replace("%", ""));
//        float quarterly4 = Float.parseFloat(tc.getPercentageTd().toString().replace("%", ""));
//        float quarterly5 = Float.parseFloat(tc.getPercentageSpg().toString().replace("%", ""));
        // 使用场数
        float quarterly1 = Float.parseFloat(tc.getNumsLt());
        float quarterly2 = Float.parseFloat(tc.getNumsMt());
        float quarterly3 = Float.parseFloat(tc.getNumsHt());
        float quarterly4 = Float.parseFloat(tc.getNumsTd());
        float quarterly5 = Float.parseFloat(tc.getNumsSpg());

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));
        yValues.add(new Entry(quarterly3, 2));
        yValues.add(new Entry(quarterly4, 3));
        yValues.add(new Entry(quarterly5, 4));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "坦克类型"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        //  colors.add(Color.RED);

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = MyApplication.getContext().getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }

    /**
     * 获取国家piechart数据
     *
     * @return
     */
    private PieData getCountryData() {
        TypesAndCountry tc = woter.getTypesAndCountry();

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
        xValues.add("S系");
        xValues.add("D系");
        xValues.add("M系");
        xValues.add("F系");
        xValues.add("Y系");
        xValues.add("C系");
        xValues.add("R系");
        xValues.add("J系");

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        /**
         * 使用比值
         */
        // 计算比值
        // 使用百分比
//        float quarterly1 = Float.parseFloat(tc.getPercentageUssr().toString().replace("%", ""));
//        float quarterly2 = Float.parseFloat(tc.getPercentageGe().toString().replace("%", ""));
//        float quarterly3 = Float.parseFloat(tc.getPercentageUsa().toString().replace("%", ""));
//        float quarterly4 = Float.parseFloat(tc.getPercentageFr().toString().replace("%", ""));
//        float quarterly5 = Float.parseFloat(tc.getPercentageUk().toString().replace("%", ""));
//        float quarterly6 = Float.parseFloat(tc.getPercentageCn().toString().replace("%", ""));
//        float quarterly7 = Float.parseFloat(tc.getPercentageJa().toString().replace("%", ""));
//        float quarterly8 = Float.parseFloat(tc.getPercentageCz().toString().replace("%", ""));
        // 使用场数比
        float quarterly1 = Float.parseFloat(tc.getNumsUssr().toString());
        float quarterly2 = Float.parseFloat(tc.getNumsGe().toString());
        float quarterly3 = Float.parseFloat(tc.getNumsUsa().toString());
        float quarterly4 = Float.parseFloat(tc.getNumsFr().toString());
        float quarterly5 = Float.parseFloat(tc.getNumsUk().toString());
        float quarterly6 = Float.parseFloat(tc.getNumsCn().toString());
        float quarterly7 = Float.parseFloat(tc.getNumsJa().toString());
        float quarterly8 = Float.parseFloat(tc.getNumsCz().toString());

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));
        yValues.add(new Entry(quarterly3, 2));
        yValues.add(new Entry(quarterly4, 3));
        yValues.add(new Entry(quarterly5, 4));
        yValues.add(new Entry(quarterly6, 5));
        yValues.add(new Entry(quarterly7, 6));
        yValues.add(new Entry(quarterly8, 7));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "国家"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(ColorTemplate.getHoloBlue());

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

//        colors.add(Color.RED);

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = MyApplication.getContext().getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }
}
