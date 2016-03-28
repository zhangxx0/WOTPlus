package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.xinxin.wotplus.MyApplication;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.Woter;

import java.util.ArrayList;

/**
 * Created by xinxin on 2016/3/24.
 * 主页的数据适配器
 */
public class WoterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private LayoutInflater layoutInflater;
    private Woter mWoter;

    private final int TYPE_ONE = 0;
    private final int TYPE_TWO = 1;
    private final int TYPE_THR = 2;
    private final int TYPE_FOR = 3;

    public WoterAdapter(Context context, Woter woter) {
        this.mContext = context;
        this.mWoter = woter;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_ONE) {
            return TYPE_ONE;
        }
        if (position == TYPE_TWO) {
            return TYPE_TWO;
        }
        if (position == TYPE_THR) {
            return TYPE_THR;
        }
        if (position == TYPE_FOR) {
            return TYPE_FOR;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        // 可以在这个地方判断军团信息是否存在，控制返回的视图数量；

        return 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = layoutInflater.inflate(R.layout.item_main_top_0, parent, false);
            return new AccountViewHolder(view);
        }
        if (viewType == TYPE_TWO) {
            View view = layoutInflater.inflate(R.layout.item_main_top, parent, false);
            return new MainInfoViewHolder(view);
        }
        if (viewType == TYPE_THR) {
            View view = layoutInflater.inflate(R.layout.item_main_charts, parent, false);
            return new ChartsViewHolder(view);
        }
        if (viewType == TYPE_FOR) {
            View view = layoutInflater.inflate(R.layout.item_main_clan, parent, false);
            return new ClanViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AccountViewHolder) {
        }

        if (holder instanceof MainInfoViewHolder) {

        }

        if (holder instanceof ChartsViewHolder) {
            try {

                PieData mpieData = getKillDieData(4, 100);
                ((ChartsViewHolder) holder).killdiechart.setHoleRadius(60f);  //半径
                ((ChartsViewHolder) holder).killdiechart.setTransparentCircleRadius(64f);
                ((ChartsViewHolder) holder).killdiechart.setCenterText(mWoter.getKillDeathRate());
                ((ChartsViewHolder) holder).killdiechart.setDrawHoleEnabled(true);
                ((ChartsViewHolder) holder).killdiechart.setRotationAngle(90);
                ((ChartsViewHolder) holder).killdiechart.setRotationEnabled(true);
                ((ChartsViewHolder) holder).killdiechart.setUsePercentValues(false);
                ((ChartsViewHolder) holder).killdiechart.setDescription(""); // 设置为空
                ((ChartsViewHolder) holder).killdiechart.setData(mpieData);
                Legend mLegend = ((ChartsViewHolder) holder).killdiechart.getLegend();  //设置比例图
                mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);  //最右边显示
                // mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
                mLegend.setXEntrySpace(7f);
                mLegend.setYEntrySpace(5f);

                PieData mpieData2 = getHurtRecData(4, 100);
                ((ChartsViewHolder) holder).hurtinjuredchart.setHoleRadius(60f);  //半径
                ((ChartsViewHolder) holder).hurtinjuredchart.setTransparentCircleRadius(64f);
                ((ChartsViewHolder) holder).hurtinjuredchart.setCenterText(mWoter.getDmgRecRate());
                ((ChartsViewHolder) holder).hurtinjuredchart.setDrawHoleEnabled(true);
                ((ChartsViewHolder) holder).hurtinjuredchart.setRotationAngle(90);
                ((ChartsViewHolder) holder).hurtinjuredchart.setRotationEnabled(true);
                ((ChartsViewHolder) holder).hurtinjuredchart.setUsePercentValues(false);
                ((ChartsViewHolder) holder).hurtinjuredchart.setDescription(""); // 设置为空
                ((ChartsViewHolder) holder).hurtinjuredchart.setData(mpieData2);
                Legend mLegend2 = ((ChartsViewHolder) holder).hurtinjuredchart.getLegend();  //设置比例图
                mLegend2.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);  //最右边显示
                mLegend2.setXEntrySpace(7f);
                mLegend2.setYEntrySpace(5f);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (holder instanceof ClanViewHolder) {

        }

    }

    class AccountViewHolder extends RecyclerView.ViewHolder {


        public AccountViewHolder(View itemView) {
            super(itemView);

        }
    }

    class MainInfoViewHolder extends RecyclerView.ViewHolder {

        public MainInfoViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ChartsViewHolder extends RecyclerView.ViewHolder {
        private PieChart killdiechart;
        private PieChart hurtinjuredchart;

        public ChartsViewHolder(View itemView) {
            super(itemView);
            killdiechart = (PieChart) itemView.findViewById(R.id.killdiechart);
            hurtinjuredchart = (PieChart) itemView.findViewById(R.id.hurtinjuredchart);
        }
    }

    class ClanViewHolder extends RecyclerView.ViewHolder {


        public ClanViewHolder(View itemView) {
            super(itemView);


        }
    }


    /**
     * 获取击杀死亡率数据
     * @param count
     * @param range
     * @return
     */
    private PieData getKillDieData(int count, float range) {

        String[] killdieNums = mWoter.getKillDeathNum().split("/");

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
        xValues.add("击杀");
        xValues.add("死亡");

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        /**
         * 使用比值
         */
        // 计算比值 24,936 13,595
        float quarterly1 = Float.parseFloat(killdieNums[0].toString().replace(",", ""));
        float quarterly2 = Float.parseFloat(killdieNums[1].toString().replace(",", ""));

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "击杀／死亡率"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = MyApplication.getContext().getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }

    /**
     * 获取伤害收到数据
     * @param i
     * @param i1
     * @return
     */
    private PieData getHurtRecData(int i, int i1) {

        String[] hurtRecNums = mWoter.getDmgRecNum().split("/");

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
        xValues.add("伤害");
        xValues.add("收到");

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        float quarterly1 = Float.parseFloat(hurtRecNums[0].toString().replace(",", ""));
        float quarterly2 = Float.parseFloat(hurtRecNums[1].toString().replace(",", ""));

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "伤害原因／收到"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = MyApplication.getContext().getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }

}
