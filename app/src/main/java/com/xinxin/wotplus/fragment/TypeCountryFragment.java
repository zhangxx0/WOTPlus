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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.xinxin.wotplus.MyApplication;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.TypesAndCountry;
import com.xinxin.wotplus.model.TypesAndCountryNewVO;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.kongzhong.Statistics;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.AchieveJsonToMapMapper;
import com.xinxin.wotplus.util.mapper.StatisticsJsonToStatisticsMapper;
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

    private Statistics statistics;

    private BarChart level_chart;
    private BarChart type_chart;
    private BarChart country_charts;

    private DeathWheelProgressDialog deathWheelProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_type_country, container, false);
        deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(getActivity());
        deathWheelProgressDialog.show();

        // 从接口获取数据
        Observer<Statistics> observer = new Observer<Statistics>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(getView(), "获取战绩信息错误！", Snackbar.LENGTH_LONG).show();
                deathWheelProgressDialog.dismiss();
            }

            @Override
            public void onNext(Statistics s) {
                statistics = s;
                setLevelChartData();
                setCountryChartData();
                setTypeChartData();
                deathWheelProgressDialog.dismiss();
            }
        };

        String woterId = PreferenceUtils.getCustomPrefString(getActivity(), "woterId", "woterId", "");
        String region = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "region", "");
        Network.getKongzhongNewApi(region)
                .getStatistics(woterId, MainFragment.BATTLE_TYPE)
                .map(StatisticsJsonToStatisticsMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        level_chart = (BarChart) view.findViewById(R.id.level_chart);
        type_chart = (BarChart) view.findViewById(R.id.type_chart);
        country_charts = (BarChart) view.findViewById(R.id.country_chart);

        level_chart.invalidate();
        type_chart.invalidate();
        country_charts.invalidate();
        deathWheelProgressDialog.dismiss();

        return view;

    }

    private void setLevelChartData() {

        level_chart.setDescription(null);

        XAxis xAxis = level_chart.getXAxis();//获取x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        xAxis.setDrawGridLines(false);//不绘制格网线
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        xAxis.setLabelCount(10);
        xAxis.setValueFormatter(new IAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) value+1);
            }
        });

        //y轴设置
        YAxis leftAxis = level_chart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = level_chart.getAxisRight();
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawAxisLine(false);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        Statistics.DataBean.TiersBean tiers = statistics.getData().getTiers();
        yVals1.add(new BarEntry(0, tiers.getTier1().getBattles_count()));
        yVals1.add(new BarEntry(1, tiers.getTier2().getBattles_count()));
        yVals1.add(new BarEntry(2, tiers.getTier3().getBattles_count()));
        yVals1.add(new BarEntry(3, tiers.getTier4().getBattles_count()));
        yVals1.add(new BarEntry(4, tiers.getTier5().getBattles_count()));
        yVals1.add(new BarEntry(5, tiers.getTier6().getBattles_count()));
        yVals1.add(new BarEntry(6, tiers.getTier7().getBattles_count()));
        yVals1.add(new BarEntry(7, tiers.getTier8().getBattles_count()));
        yVals1.add(new BarEntry(8, tiers.getTier9().getBattles_count()));
        yVals1.add(new BarEntry(9, tiers.getTier10().getBattles_count()));

        BarDataSet set1;

        if (level_chart.getData() != null &&
                level_chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) level_chart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            level_chart.getData().notifyDataChanged();
            level_chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "按等级");

            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.COLORFUL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            // 这特么是个啥呢？ 2018年1月1日16:55:15
//            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            level_chart.setData(data);
        }
    }

    private void setCountryChartData() {

        country_charts.setDescription(null);

        XAxis xAxis = country_charts.getXAxis();//获取x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        xAxis.setDrawGridLines(false);//不绘制格网线
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        xAxis.setLabelCount(10);
        xAxis.setValueFormatter(new IAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String nation = "系";
                switch ((int) value ) {
                    case 0:
                        return "C系";
                    case 1:
                        return "S系";
                    case 2:
                        return "D系";
                    case 3:
                        return "M系";
                    case 4:
                        return "F系";
                    case 5:
                        return "Y系";
                    case 6:
                        return "R系";
                    case 7:
                        return "J系";
                    case 8:
                        return "V系";
                }
                return nation;
            }
        });

        //y轴设置
        YAxis leftAxis = country_charts.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = country_charts.getAxisRight();
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawAxisLine(false);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        Statistics.DataBean.NationsBean nations = statistics.getData().getNations();
        yVals1.add(new BarEntry(0, nations.getChina().getBattles_count()));
        yVals1.add(new BarEntry(1, nations.getUssr().getBattles_count()));
        yVals1.add(new BarEntry(2, nations.getGermany().getBattles_count()));
        yVals1.add(new BarEntry(3, nations.getUsa().getBattles_count()));
        yVals1.add(new BarEntry(4, nations.getFrance().getBattles_count()));
        yVals1.add(new BarEntry(5, nations.getUk().getBattles_count()));
        yVals1.add(new BarEntry(6, nations.getJapan().getBattles_count()));
        yVals1.add(new BarEntry(7, nations.getCzech().getBattles_count()));
        yVals1.add(new BarEntry(8, nations.getSweden().getBattles_count()));

        BarDataSet set1;

        if (country_charts.getData() != null &&
                country_charts.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) country_charts.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            country_charts.getData().notifyDataChanged();
            country_charts.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "按系别");

            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            // 这特么是个啥呢？ 2018年1月1日16:55:15
//            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            country_charts.setData(data);
        }
    }

    private void setTypeChartData() {

        type_chart.setDescription(null);

        XAxis xAxis = type_chart.getXAxis();//获取x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        xAxis.setDrawGridLines(false);//不绘制格网线
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        xAxis.setLabelCount(10);
        xAxis.setValueFormatter(new IAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String nation = "";
                switch ((int) value ) {
                    case 0:
                        return "LT";
                    case 1:
                        return "MT";
                    case 2:
                        return "HT";
                    case 3:
                        return "TD";
                    case 4:
                        return "SPG";
                }
                return nation;
            }
        });

        //y轴设置
        YAxis leftAxis = type_chart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = type_chart.getAxisRight();
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawAxisLine(false);

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        Statistics.DataBean.TypesBean types = statistics.getData().getTypes();
        yVals1.add(new BarEntry(0, types.getLightTank().getBattles_count()));
        yVals1.add(new BarEntry(1, types.getMediumTank().getBattles_count()));
        yVals1.add(new BarEntry(2, types.getHeavyTank().getBattles_count()));
        yVals1.add(new BarEntry(3, types.getATSPG().getBattles_count()));
        yVals1.add(new BarEntry(4, types.getSPG().getBattles_count()));

        BarDataSet set1;

        if (type_chart.getData() != null &&
                type_chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) type_chart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            type_chart.getData().notifyDataChanged();
            type_chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "按类型");

            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.JOYFUL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            // 这特么是个啥呢？ 2018年1月1日16:55:15
//            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            type_chart.setData(data);
        }
    }

}
